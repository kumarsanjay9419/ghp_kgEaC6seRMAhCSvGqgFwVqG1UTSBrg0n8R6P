 package in.nareshit.raghu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.raghu.exception.ShipmentTypeNotFoundException;
import in.nareshit.raghu.model.ShipmentType;
import in.nareshit.raghu.service.IShipmentTypeService;

    @Controller
    @RequestMapping("/st")
    public class ShipmentTypeController {
    	
    	private static final Logger LOG=LoggerFactory.getLogger(ShipmentTypeController.class);
	
    	@Autowired
	private IShipmentTypeService service; //HAS-A
    	
    	//1. show Register page
    	@GetMapping("/register")
    	public String showRegister() {
    		return "ShipmentTypeRegister";
    	}

    //2. on click submit
    	@PostMapping("/save")
    	public String saveShipmentType( 
    			//Read Form data
    		@ModelAttribute ShipmentType shipmentType,
    		Model model 
    		)
    	{
    		
    		LOG.info("ENTERED INTO SAVE METHOD");
    		try {
    			//call service
        		Integer id=service.saveShipmentType(shipmentType);
        		LOG.debug("RECORDED IS CREATED WITH ID{}",id);
        		//success message
        		String msg="Shipment Type '" +id+ "'  is created";
        		
        		//send to UI (key,value)
        		model.addAttribute("message", msg);
				
			} catch (Exception e) {
				LOG.error("Unable to process request due to {}",e.getMessage());
				e.printStackTrace();
			}
    		
    		LOG.info("ABOUT TO GO UI PAGE!");
    		
    		//goto UI page
    		
    		return "ShipmentTypeRegister";
    		}
    	
    	//3. fetch data to UI
    	@GetMapping("/all")
    	public String fetchShipmentTypes(
    			Model model
    			)
    	{
    		
    		  LOG.info("ENETERED INTO FETCH ALL ROWS");
    		try {
				
    			//call service get List<T>
        		List<ShipmentType> list=service.getAllShipmentTypes();
        		
        		LOG.debug("DATA FOUND WITH SIZE {}",list!=null?list.size():"NO DATA");
        		//send data to UI
        		model.addAttribute("list",list);
			} catch (Exception e) {
				LOG.error("UNABLE TO FETCH DATA {}",e.getMessage());
				e.printStackTrace();
			}
    		
    		LOG.info("MOVING TO DATA PAGE TO DISPLAY");
    		//goto UI page
    		return "ShipmentTypeData";
    	}
    	
    	
    	//4. for delete by id
    	@GetMapping("/delete")
    	public String deleteShipmentType (
    			@RequestParam Integer id ,
    			Model model)
    	{
    		
    		 LOG.info("ENETERED INTO DELETE METHOD");
    		try {
    			//call service
        		service.deleteShipmentType(id);
        		
        		//create message
        		String msg="Shipment Type ' "+id+" ' Delete";
        		LOG.debug(msg);
        		model.addAttribute("message",msg);
    			
			} catch (ShipmentTypeNotFoundException e) {
				LOG.error("UNABLE TO PROCESS DELETE REQUEST {}",e.getMessage());
				e.printStackTrace();
				model.addAttribute("message",e.getMessage());
			}
    		
    		//load new data
    		List<ShipmentType> list=service.getAllShipmentTypes();
              //send to UI
    		 model.addAttribute("list",list);
 
    		//goto UI page ....
    		return "ShipmentTypeData";
    	}
    	
    	//5. show edit page
    	@GetMapping("/edit")
    	public String showShipmentTypeEdit(
    			@RequestParam Integer id,
    			Model model) 
    	{
    		  String page=null;
    		try {
    			//fetch from db using service
        		ShipmentType st = service.getShipmentType(id);
        		
        		//send object to UI as Form data
        		model.addAttribute("shipmentType",st); //using th:object="${shipmentType}"
    			
        		//show edit if record is exist
        		page="ShipmentTypeEdit";
			} catch (ShipmentTypeNotFoundException e) {
				LOG.error("UNABLE TO PROCESS EDIT REQUEST {}",e.getMessage());

			     e.printStackTrace();
			     //if row is not exist
			     page="ShipmentTypeData";
			     model.addAttribute("message",e.getMessage());
			     
			     //load new data
		    		List<ShipmentType> list=service.getAllShipmentTypes();
		    		//send data to UI
		    	    model.addAttribute("list",list);
			     }
    		LOG.info("ABOUT TO GO PAGE{}",page);
    		//goto UI page ....
    		return page;
    		}
    	
    	//6.Read form data and submit
    	
    	@PostMapping("/update")
    	public String updateShipmentType(
    			@ModelAttribute ShipmentType shipmentType) {
    		
    		LOG.info("ENTERED INTO UPDATE METHOD");
    		try {
    			//call service
        		service.updateShipmentType(shipmentType);
			} catch (Exception e) {
				LOG.error("UNABLE TO PERFORM UPDATE : {}",e.getMessage());
				e.printStackTrace();
			}
    		LOG.info("REDIRECTING TO FETCH ALL ROWS");
    		//redirect to all
    		return "redirect:all";
    	}
    	
    	
    	//7. AJAX Validation
    	@GetMapping("/validate")
    	@ResponseBody
    	public  String validateShipmentTypeCode(
    			@RequestParam String code,
    			@RequestParam Integer id
    			)
    	{
    		
    		String message=""; 
    		      
    		//for register check
    		if(id==0 && service.isShipmentTypeCodeExist(code)){
    			message=code + ", already exist";
    			
    		   }else if(id!=0 && service.isShipmentTypeCodeExistForEdit(code,id)){
    			//for edit check
    			message=code + ", already exist";
    		   }
    		
    		
    		return message;
    	}
    	
    	
    }
 