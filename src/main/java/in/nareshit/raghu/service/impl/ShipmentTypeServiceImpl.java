package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.ShipmentTypeNotFoundException;
import in.nareshit.raghu.model.ShipmentType;
import in.nareshit.raghu.repo.ShipmentTypeRepository;
import in.nareshit.raghu.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl
  implements IShipmentTypeService
     {
	@Autowired
        private ShipmentTypeRepository repo;//HAS-A
	
	//for insert the data UI to database
	@Override
	public Integer saveShipmentType(ShipmentType st) {
		st=repo.save(st);
		return st.getId();  //return PK(ID)
	}

	
	//for get the data from databse to UI
	public List<ShipmentType> getAllShipmentTypes() {
	    List<ShipmentType> list= repo.findAll();
		return list;
	}


	  /*
	 //old format
	//for delete
	@Override
	public void deleteShipmentType(Integer id) {
		// if given object is present
				//then do delete
				//else throw exception
				
		repo.deleteById(id);
		
	}


	//for edit or update 
	@Override
	public ShipmentType getShipmentType(Integer id) {
		Optional<ShipmentType> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			//throw new ShipmentTypeNotFoundException(ShipmentType '"+id+"' Not Exist);
		}
		return null;
	}
          */
	
	//new format

		//for delete
		@Override
		public void deleteShipmentType(Integer id) {
			repo.delete(getShipmentType(id));
			
		}


		//for edit or update 
		@Override
		public ShipmentType getShipmentType(Integer id) {
			return repo.findById(id)
					.orElseThrow(
							()->new ShipmentTypeNotFoundException(
									"ShipmentType '"+id+"' Not Exist")
			             );    //lamda expression
			 }

	
           
	//for update
	@Override
	public void updateShipmentType(ShipmentType st) {
		// if given object is present
		//then update
		//else throw exception
		repo.save(st);
		
		}


	@Override
	public boolean isShipmentTypeCodeExist(String code) {
		/*
		Integer count=repo.getShipmentTypeCodeCount(code);
		boolean isExist=count>0?true:false;
		return isExist;
		  */
		
		//return repo.getShipmentTypeCodeCount(code)>0?true:false;
		return repo.getShipmentTypeCodeCount(code)>0; 
		//short form
	}


	//@Override
	public boolean isShipmentTypeCodeExistForEdit(String code, Integer id) {
		
		return repo.getShipmentTypeCodeCountForEdit(code, id)>0;
	}


	
	
	
	
}
    