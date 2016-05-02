package br.com.haircutter.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "establishment_professional_service")
public class ProfessionalService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "establishment_employee_id")
	private Long establishmentEmployeeId;
	
	@Column(name = "establishment_service_id")
	private Long establishmentServiceId;

	public ProfessionalService() {
		super();
	}

	public ProfessionalService(Long id, Long establishmentEmployeeId, Long establishmentServiceId,
			Integer duaration) {
		super();
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
