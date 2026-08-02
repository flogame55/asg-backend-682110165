package th.camt.dto;

import java.util.ArrayList;
import java.util.List;

public class AirsoftGunDTO {
    private Long id;
    private String modelName;
    private Integer fps;
    private Double price;
    private String powerType;

    private SupplierDTO supplier;
    private SerialPlateDTO serialPlate;
    private List<AccessoryDTO> accessories = new ArrayList<>();

    public AirsoftGunDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getFps() {
        return fps;
    }

    public void setFps(Integer fps) {
        this.fps = fps;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public SerialPlateDTO getSerialPlate() {
        return serialPlate;
    }

    public void setSerialPlate(SerialPlateDTO serialPlate) {
        this.serialPlate = serialPlate;
    }

    public List<AccessoryDTO> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<AccessoryDTO> accessories) {
        this.accessories = accessories;
    }
}
