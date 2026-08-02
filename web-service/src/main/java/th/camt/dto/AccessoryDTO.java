package th.camt.dto;

public class AccessoryDTO {
    private Long id;
    private String name;
    private String accessoryType;
    private Double price;

    public AccessoryDTO() {
    }

    public AccessoryDTO(Long id, String name, String accessoryType, Double price) {
        this.id = id;
        this.name = name;
        this.accessoryType = accessoryType;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(String accessoryType) {
        this.accessoryType = accessoryType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
