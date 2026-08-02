package th.camt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "accessories")
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String accessoryType;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airsoft_gun_id")
    @JsonIgnore
    private AirsoftGun airsoftGun;

    public Accessory() {
    }

    public Accessory(String name, String accessoryType, Double price) {
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

    public AirsoftGun getAirsoftGun() {
        return airsoftGun;
    }

    public void setAirsoftGun(AirsoftGun airsoftGun) {
        this.airsoftGun = airsoftGun;
    }
}
