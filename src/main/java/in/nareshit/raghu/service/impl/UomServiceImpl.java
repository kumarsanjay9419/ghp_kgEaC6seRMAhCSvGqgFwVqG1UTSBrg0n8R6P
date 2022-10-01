 package in.nareshit.raghu.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.UomNotFoundException;
import in.nareshit.raghu.model.Uom;
import in.nareshit.raghu.repo.UomRepository;
import in.nareshit.raghu.service.IUomService;

@Service
public class UomServiceImpl
  implements IUomService
     {
	
	  @Autowired
        private UomRepository repo;//HAS-A

	  
	@Override
	public Integer save(Uom uom) {
		uom=repo.save(uom);
		return uom.getId();
	   }

	
	@Override
	public void updateUom(Uom uom) {
	 repo.save(uom);
		
	  }

	@Override
	public void deleteUom(Integer id) {
	   repo.delete(getOneUom(id));
		
	}

	@Override
	public Uom getOneUom(Integer id) {
		
		return repo.findById(id)
				.orElseThrow(
						()->new UomNotFoundException(
								"Uom '"+id+"'  Not Exist")
						);
	}            //checking for exception

	
	@Override
	public List<Uom> getAllUoms() {
		
		return repo.findAll();
	}


	@Override
	public boolean isUomModelExist(String uomModel) {
	
		return repo.getUomModelCount(uomModel)>0;
	}


	@Override
	public boolean isUomModelExistForEdit(String uomModel, Integer id) {
		
		return repo.getUomModelCountForEdit(uomModel, id)>0;
	}
	
	         
	
	
	 }
    