package br.com.haircutter.core.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.haircutter.core.model.Establishment;

@Transactional
public interface EstablishmentsQueryRepository extends JpaRepository<Establishment, Long> {
	
	@Query("SELECT e "
		  +"FROM Establishment e "
		  +"WHERE e.name LIKE %:establishmentName%")
	List<Establishment> queryFindEstablishmentByName(@Param("establishmentName")  String establishmentName);
	
	@Query("SELECT e " 
		  +"FROM Establishment e "
		  +"INNER JOIN EstablishmentEmployee ee "
		  +"ON e.cnpj = ee.establishmentCnpj "
		  +"INNER JOIN user u "
		  +"ON ee.username = u.username "
		  +"WHERE u.role = 'ROLE_PROFESSIONAL' "
		  +"AND u.name like %:professionalName% " )
	List<Establishment> queryFindEstablishmentByProfessional(@Param("professionalName") String professionalName);
	
	@Query("SELECT e "
		  +"FROM Establishment e "
		  +"INNER JOIN EstablishmentService es "
		  +"ON e.cnpj = es.establishmentCnpj "
		  +"WHERE es.service LIKE %:serviceName% ")
	List<Establishment> queryFindEstablishmentByServices(@Param("serviceName") String serviceName);
	
	@Query("SELECT e "
		  +"FROM Establishment e "
		  +"INNER JOIN Address a "
		  +"ON e.addressId = a.id "
		  +"WHERE a.street LIKE %:address% "
		  +"OR a.neighborhood LIKE %:address% "
		  +"OR a.city LIKE %:address% "
		  +"OR a.state LIKE %:address% "	
		  +"OR a.country LIKE %:address% ")
	List<Establishment> queryFindEstablishmentByAddress(@Param("address") String address);
	
	
	/*@Query("SELECT e "
		 + "FROM Establishment e "
		 + "INNER JOIN Address a "
		 + "ON e.addressId = a.id "
		 + "INNER JOIN EstablishmentEmployee ee "
		 + "ON e.cnpj = ee.establishmentCnpj "
		 + "INNER JOIN User u "
		 + "ON ee.username = u.username "
		 + "INNER JOIN EstablishmentService es "
		 + "ON e.cnpj = es.establishmentCnpj "
		 + "WHERE a.city LIKE %:city% "
		 + "AND u.role = 'ROLE_PROFESSIONAL' ")
	List<Establishment> findEstablishmentByAddress(@Param("city") String city);*/
}
