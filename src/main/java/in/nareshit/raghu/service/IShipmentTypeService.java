package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.model.ShipmentType;

public interface IShipmentTypeService {
	//for insert data in database
	Integer saveShipmentType(ShipmentType st); 
	
	//for get the data from database to UI
	List<ShipmentType> getAllShipmentTypes();
	
	//for delete byId
	void deleteShipmentType (Integer id);
	
	//for update
	void updateShipmentType(ShipmentType st); 
	
	//for edit by
	ShipmentType getShipmentType(Integer id);
	
	//for findout data exit 
	boolean isShipmentTypeCodeExist(String code);
	
	//for editpage bug fix
	
	boolean isShipmentTypeCodeExistForEdit(String code,Integer id);
	
 }
