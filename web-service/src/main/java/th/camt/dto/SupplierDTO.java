package th.camt.dto;

public class SupplierDTO {
    private Long id;
    private String companyName;
    private String country;

    public SupplierDTO() {
    }

    public SupplierDTO(Long id, String companyName, String country) {
        this.id = id;
        this.companyName = companyName;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
