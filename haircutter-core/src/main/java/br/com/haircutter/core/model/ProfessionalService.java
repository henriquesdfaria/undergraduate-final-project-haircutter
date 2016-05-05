package br.com.haircutter.core.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "establishment_professional_service")
public class ProfessionalService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Transient
	private EstablishmentEmployee establishmentEmployee;

	@Transient
	private EstablishmentService establishmentService;

	@Column(name = "establishment_employee_id")
	private Long establishmentEmployeeId;
	
	@Column(name = "establishment_service_id")
	private Long establishmentServiceId;

	public ProfessionalService() {
	}

	public ProfessionalService(EstablishmentEmployee establishmentEmployee, EstablishmentService establishmentService, Long establishmentEmployeeId, Long establishmentServiceId) {
		this.establishmentEmployee = establishmentEmployee;
		this.establishmentService = establishmentService;
		this.establishmentEmployeeId = establishmentEmployeeId;
		this.establishmentServiceId = establishmentServiceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstablishmentEmployee getEstablishmentEmployee() {
		return establishmentEmployee;
	}

	public void setEstablishmentEmployee(EstablishmentEmployee establishmentEmployee) {
		this.establishmentEmployee = establishmentEmployee;
	}

	public EstablishmentService getEstablishmentService() {
		return establishmentService;
	}

	public void setEstablishmentService(EstablishmentService establishmentService) {
		this.establishmentService = establishmentService;
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
