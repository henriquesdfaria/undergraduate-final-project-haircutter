package br.com.haircutter.core.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.haircutter.core.model.Establishment;

@Transactional
public interface EstablishmentsQueryRepository {
	
	@Query("SELECT e.* "
		  +"FROM establishment e "
		  +"WHERE e.name LIKE %:establishmentName%")
	List<Establishment> findEstablishmentByName(@Param("establishmentName")  String establishmentName);
	
	@Query("SELECT e.* " 
		  +"FROM establishment e "
		  +"INNER JOIN establishmentEmployee ee "
		  +"ON e.cnpj = ee.establishmentCnpj "
		  +"INNER JOIN user u "
		  +"ON ee.username = u.username "
		  +"WHERE u.role = 'ROLE_PROFESSIONAL' "
		  +"AND u.name like %:professionalName% " )
	List<Establishment> findEstablishmentByProfessional(@Param("professionalName") String professionalName);
	
	@Query("SELECT e.* "
		  +"FROM establishment e "
		  +"INNER JOIN establishment_service es "
		  +"ON e.cnpj = es.establishmentCnpj "
		  +"WHERE es.service LIKE %:serviceName% ")
	List<Establishment> findEstablishmentByServices(@Param("serviceName") String serviceName);
	
	@Query("SELECT e.* "
		  +"FROM establishment e "
		  +"INNER JOIN address a "
		  +"ON e.addressId = a.id "
		  +"WHERE a.street LIKE %:address% "
		  +"OR a.neighborhood LIKE %:address% "
		  +"OR a.city LIKE %:address% "
		  +"OR a.state LIKE %:address% "	
		  +"OR a.country LIKE %:address% ")
	List<Establishment> findEstablishmentByAddress(@Param("address") String address);
}
