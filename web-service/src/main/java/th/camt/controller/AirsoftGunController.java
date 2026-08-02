package th.camt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.camt.dto.AccessoryDTO;
import th.camt.dto.AirsoftGunDTO;
import th.camt.dto.SerialPlateDTO;
import th.camt.dto.SupplierDTO;
import th.camt.entity.Accessory;
import th.camt.entity.AirsoftGun;
import th.camt.entity.SerialPlate;
import th.camt.entity.Supplier;
import th.camt.repository.AirsoftGunRepository;
import th.camt.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guns")
public class AirsoftGunController {

    @Autowired
    private AirsoftGunRepository airsoftGunRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    // Helper: Convert Entity to DTO
    private AirsoftGunDTO convertToDTO(AirsoftGun gun) {
        AirsoftGunDTO dto = new AirsoftGunDTO();
        dto.setId(gun.getId());
        dto.setModelName(gun.getModelName());
        dto.setFps(gun.getFps());
        dto.setPrice(gun.getPrice());
        dto.setPowerType(gun.getPowerType());

        if (gun.getSupplier() != null) {
            dto.setSupplier(new SupplierDTO(
                    gun.getSupplier().getId(),
                    gun.getSupplier().getCompanyName(),
                    gun.getSupplier().getCountry()
            ));
        }

        if (gun.getSerialPlate() != null) {
            dto.setSerialPlate(new SerialPlateDTO(
                    gun.getSerialPlate().getId(),
                    gun.getSerialPlate().getSerialNumber(),
                    gun.getSerialPlate().getEngravingText()
            ));
        }

        if (gun.getAccessories() != null) {
            dto.setAccessories(gun.getAccessories().stream()
                    .map(acc -> new AccessoryDTO(acc.getId(), acc.getName(), acc.getAccessoryType(), acc.getPrice()))
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    // 1. LIST: GET /api/guns
    @GetMapping
    public List<AirsoftGunDTO> getAllGuns() {
        return airsoftGunRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 1.1 GET by ID: GET /api/guns/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AirsoftGunDTO> getGunById(@PathVariable Long id) {
        Optional<AirsoftGun> optionalGun = airsoftGunRepository.findById(id);
        return optionalGun.map(gun -> ResponseEntity.ok(convertToDTO(gun)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 2. CREATE: POST /api/guns
    @PostMapping
    public ResponseEntity<AirsoftGunDTO> createGun(@RequestBody AirsoftGunDTO dto) {
        AirsoftGun gun = new AirsoftGun();
        gun.setModelName(dto.getModelName());
        gun.setFps(dto.getFps());
        gun.setPrice(dto.getPrice());
        gun.setPowerType(dto.getPowerType());

        if (dto.getSupplier() != null) {
            if (dto.getSupplier().getId() != null) {
                supplierRepository.findById(dto.getSupplier().getId())
                        .ifPresent(gun::setSupplier);
            } else {
                Supplier supplier = new Supplier(dto.getSupplier().getCompanyName(), dto.getSupplier().getCountry());
                gun.setSupplier(supplier);
            }
        }

        if (dto.getSerialPlate() != null) {
            SerialPlate plate = new SerialPlate(dto.getSerialPlate().getSerialNumber(), dto.getSerialPlate().getEngravingText());
            gun.setSerialPlate(plate);
        }

        if (dto.getAccessories() != null) {
            for (AccessoryDTO accDto : dto.getAccessories()) {
                Accessory accessory = new Accessory(accDto.getName(), accDto.getAccessoryType(), accDto.getPrice());
                gun.addAccessory(accessory);
            }
        }

        AirsoftGun savedGun = airsoftGunRepository.save(gun);
        return new ResponseEntity<>(convertToDTO(savedGun), HttpStatus.CREATED);
    }

    // 3. UPDATE: PATCH /api/guns/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<AirsoftGunDTO> updateGunPatch(@PathVariable Long id, @RequestBody AirsoftGunDTO dto) {
        Optional<AirsoftGun> optionalGun = airsoftGunRepository.findById(id);
        if (!optionalGun.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        AirsoftGun gun = optionalGun.get();
        if (dto.getModelName() != null) {
            gun.setModelName(dto.getModelName());
        }
        if (dto.getFps() != null) {
            gun.setFps(dto.getFps());
        }
        if (dto.getPrice() != null) {
            gun.setPrice(dto.getPrice());
        }
        if (dto.getPowerType() != null) {
            gun.setPowerType(dto.getPowerType());
        }

        AirsoftGun updatedGun = airsoftGunRepository.save(gun);
        return ResponseEntity.ok(convertToDTO(updatedGun));
    }

    // 4. DELETE: DELETE /api/guns/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGun(@PathVariable Long id) {
        if (!airsoftGunRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        airsoftGunRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
