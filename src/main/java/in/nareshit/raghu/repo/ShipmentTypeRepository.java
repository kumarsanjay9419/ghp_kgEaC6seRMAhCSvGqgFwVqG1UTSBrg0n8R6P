package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.ShipmentType;

public interface ShipmentTypeRepository
   extends JpaRepository<ShipmentType, Integer>
{

	//Register check 
	@Query("SELECT count(shipCode) from ShipmentType where shipCode=:code")
	Integer getShipmentTypeCodeCount(String code);
	
	//Edit check 
		@Query("SELECT count(shipCode) from ShipmentType where shipCode=:code and id!=:id")
		Integer getShipmentTypeCodeCountForEdit(String code,Integer id);
}
