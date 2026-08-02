package th.camt.dto;

public class SerialPlateDTO {
    private Long id;
    private String serialNumber;
    private String engravingText;

    public SerialPlateDTO() {
    }

    public SerialPlateDTO(Long id, String serialNumber, String engravingText) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.engravingText = engravingText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEngravingText() {
        return engravingText;
    }

    public void setEngravingText(String engravingText) {
        this.engravingText = engravingText;
    }
}
