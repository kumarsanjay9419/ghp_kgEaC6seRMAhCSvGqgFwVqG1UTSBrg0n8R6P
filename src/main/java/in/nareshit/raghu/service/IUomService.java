package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.model.Uom;

public interface IUomService {
	  Integer save(Uom uom);
	  
	  void updateUom(Uom uom);
	  
	  void deleteUom(Integer id);
	
	Uom getOneUom(Integer id);
	List<Uom> getAllUoms();
	
	boolean isUomModelExist(String uomModel);
	
	boolean isUomModelExistForEdit(String uomModel,Integer id);

	
}
