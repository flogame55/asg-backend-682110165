package th.camt.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airsoft_guns")
public class AirsoftGun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;
    private Integer fps;
    private Double price;
    private String powerType;

    // 1. Many-to-One: Many AirsoftGuns belong to One Supplier
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // 2. One-to-One: One AirsoftGun has One SerialPlate
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "serial_plate_id", referencedColumnName = "id")
    private SerialPlate serialPlate;

    // 3. One-to-Many: One AirsoftGun has Many Accessories
    @OneToMany(mappedBy = "airsoftGun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accessory> accessories = new ArrayList<>();

    public AirsoftGun() {
    }

    public AirsoftGun(String modelName, Integer fps, Double price, String powerType) {
        this.modelName = modelName;
        this.fps = fps;
        this.price = price;
        this.powerType = powerType;
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
        accessory.setAirsoftGun(this);
    }

    public void removeAccessory(Accessory accessory) {
        accessories.remove(accessory);
        accessory.setAirsoftGun(null);
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public SerialPlate getSerialPlate() {
        return serialPlate;
    }

    public void setSerialPlate(SerialPlate serialPlate) {
        this.serialPlate = serialPlate;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
        if (accessories != null) {
            for (Accessory acc : accessories) {
                acc.setAirsoftGun(this);
            }
        }
    }
}
