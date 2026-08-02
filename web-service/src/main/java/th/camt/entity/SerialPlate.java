package th.camt.entity;

import javax.persistence.*;

@Entity
@Table(name = "serial_plates")
public class SerialPlate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;
    private String engravingText;

    public SerialPlate() {
    }

    public SerialPlate(String serialNumber, String engravingText) {
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
