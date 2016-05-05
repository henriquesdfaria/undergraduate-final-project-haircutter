package br.com.haircutter.admin.facade.json;

public class ProfessionalServiceJson {


    private Long id;

    private Long establishmentEmployeeId;

    private Long establishmentServiceId;

    public ProfessionalServiceJson() {

    }

    public ProfessionalServiceJson(Long id, Long establishmentEmployeeId, Long establishmentServiceId) {
        this.id = id;
        this.establishmentEmployeeId = establishmentEmployeeId;
        this.establishmentServiceId = establishmentServiceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstablishmentEmployeeId() {
        return establishmentEmployeeId;
    }

    public void setEstablishmentEmployeeId(Long establishmentEmployeeId) {
        this.establishmentEmployeeId = establishmentEmployeeId;
    }

    public Long getEstablishmentServiceId() {
        return establishmentServiceId;
    }

    public void setEstablishmentServiceId(Long establishmentServiceId) {
        this.establishmentServiceId = establishmentServiceId;
    }
}
