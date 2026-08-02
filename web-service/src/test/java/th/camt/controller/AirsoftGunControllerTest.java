package th.camt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import th.camt.App;
import th.camt.dto.AccessoryDTO;
import th.camt.dto.AirsoftGunDTO;
import th.camt.dto.SerialPlateDTO;
import th.camt.dto.SupplierDTO;
import th.camt.repository.AirsoftGunRepository;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class AirsoftGunControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AirsoftGunRepository airsoftGunRepository;

    @BeforeEach
    public void setup() {
        airsoftGunRepository.deleteAll();
    }

    // 1. Test Create Method
    @Test
    public void testCreateAirsoftGun() throws Exception {
        AirsoftGunDTO dto = new AirsoftGunDTO();
        dto.setModelName("M4A1 Carbine");
        dto.setFps(400);
        dto.setPrice(8500.0);
        dto.setPowerType("AEG");

        SupplierDTO supplier = new SupplierDTO(null, "Tokyo Marui", "Japan");
        dto.setSupplier(supplier);

        SerialPlateDTO plate = new SerialPlateDTO(null, "TM-M4-001", "Property of US Army");
        dto.setSerialPlate(plate);

        AccessoryDTO accessory = new AccessoryDTO(null, "Acog Scope 4x", "Optics", 2500.0);
        dto.setAccessories(Collections.singletonList(accessory));

        mockMvc.perform(post("/api/guns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.modelName", is("M4A1 Carbine")))
                .andExpect(jsonPath("$.supplier.companyName", is("Tokyo Marui")))
                .andExpect(jsonPath("$.serialPlate.serialNumber", is("TM-M4-001")))
                .andExpect(jsonPath("$.accessories[0].name", is("Acog Scope 4x")));
    }

    // 2. Test List Method
    @Test
    public void testListAirsoftGuns() throws Exception {
        testCreateAirsoftGun(); // Seed 1 gun

        mockMvc.perform(get("/api/guns"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].modelName", is("M4A1 Carbine")));
    }

    // 3. Test Update Method (Patch)
    @Test
    public void testUpdateAirsoftGunPatch() throws Exception {
        // First create
        AirsoftGunDTO dto = new AirsoftGunDTO();
        dto.setModelName("Glock 19 Gen4");
        dto.setFps(310);
        dto.setPrice(4200.0);
        dto.setPowerType("GBB");

        String responseJson = mockMvc.perform(post("/api/guns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        AirsoftGunDTO created = objectMapper.readValue(responseJson, AirsoftGunDTO.class);

        // Update patch
        AirsoftGunDTO patchDto = new AirsoftGunDTO();
        patchDto.setPrice(4500.0);
        patchDto.setFps(330);

        mockMvc.perform(patch("/api/guns/" + created.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(4500.0)))
                .andExpect(jsonPath("$.fps", is(330)))
                .andExpect(jsonPath("$.modelName", is("Glock 19 Gen4"))); // should remain unchanged
    }

    // 4. Test Delete Method
    @Test
    public void testDeleteAirsoftGun() throws Exception {
        // Create gun
        AirsoftGunDTO dto = new AirsoftGunDTO();
        dto.setModelName("AK-47 Tactical");
        dto.setFps(420);
        dto.setPrice(6500.0);
        dto.setPowerType("AEG");

        String responseJson = mockMvc.perform(post("/api/guns")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        AirsoftGunDTO created = objectMapper.readValue(responseJson, AirsoftGunDTO.class);

        // Delete gun
        mockMvc.perform(delete("/api/guns/" + created.getId()))
                .andExpect(status().isNoContent());

        // Verify it is deleted
        mockMvc.perform(get("/api/guns/" + created.getId()))
                .andExpect(status().isNotFound());
    }
}
