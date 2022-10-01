
   $(document).ready(function(){
    //1. hide error section
    $("#shipModelError").hide();
    $("#shipCodeError").hide();
    $("#enableShipError").hide();
    $("#shipGradeError").hide();
    $("#shipDescError").hide();
    //2. define error variables
    var shipModelError=false;
    var shipCodeError=false;
    var enableShipError=false;
    var shipGradeError=false;
    var shipDescError=false;
    //3. define validate function
    function validate_shipModel(){
        var val=$("#shipModel").val();
        if(val==''){
            $("#shipModelError").show();
            $("#shipModelError").html("*please select<b> Mode*</b>");
            $("#shipModelError").css('color','red');
            shipModelError=false;
         }else{
            $("#shipModelError").hide();
            shipModelError=true;  
         }
     return shipModelError; 
          }

          //for shipCode
          function validate_shipCode(){
        	  
            var val=$("#shipCode").val();
            var exp=/^[A-Z\-\s]{4,8}$/;
            if(val==''){
            $("#shipCodeError").show();
            $("#shipCodeError").html("*please select<b> Code*</b>");
            $("#shipCodeError").css('color','red');
            shipCodeError=false;
         }
            else if(!exp.test(val)){
            	 $("#shipCodeError").show();
                 $("#shipCodeError").html("*<b>Code</b> must be 4-8 upperCase letters");
                 $("#shipCodeError").css('color','red');
                 shipCodeError=false;
              
            	}else{
            	
            	var id=0;
            	if($("#id").val()!=undefined){
            	id=$("#id").val(); // if present id
            	}
            //AJAX CALL START
            
            $.ajax({
            	url : 'validate',
            	data: {"code": val,"id":id},
              // method:'POST',
            	success:function(resTxt){
            		if(resTxt!=''){ 
            			             //error, duplicate exist
            			
            			$("#shipCodeError").show();
                        $("#shipCodeError").html(resTxt);
                        $("#shipCodeError").css('color','red');
                        shipCodeError=false;
            			
            		}else{ 
            			 //validate , no duplicate
            			$("#shipCodeError").hide();
            			shipCodeError=true;
            			
            		}  // in main other else
            		
            	}    //success
            });   //ajax
              
            //AJAX END
                    //shipCodeError=true; 
         }  // main else
           
                 return shipCodeError; 
          }

           //for enablemode
          function validate_enableShip(){
              var len=$('[name="enableShip"]:checked').length;
              if(len==0){
                $("#enableShipError").show();
            $("#enableShipError").html("*select one<b> Enable shipment*</b>");
            $("#enableShipError").css('color','red');
            enableShipError=false;
         }else{
            $("#enableShipError").hide();
            enableShipError=true;  
         }
     return enableShipError; 
          }

            //shipGrademode
          function validate_shipGrade(){
            var len=$('[name="shipGrade"]:checked').length;
              if(len==0){
                $("#shipGradeError").show();
            $("#shipGradeError").html("*select one<b> shipment Grade*</b>");
            $("#shipGradeError").css('color','red');
            shipGradeError=false;
         }else{
            $("#shipGradeError").hide();
            shipGradeError=true;  
         }

     return shipGradeError; 
          }

            //shipDesc mode
          function validate_shipDesc(){
            var val=$("#shipDesc").val();
        if(val==''){
            $("#shipDescError").show();
            $("#shipDescError").html("*please enter<b> Description*</b>");
            $("#shipDescError").css('color','red');
            shipDescError=false;
         }else{
            $("#shipDescError").hide();
            shipDescError=true;  
         }
     return shipDescError; 
          }
            
    //4. link with action event
    $("#shipModel").change(function(){
        validate_shipModel();
    })
    $("#shipCode").keyup(function(){
        validate_shipCode();
        $(this).val($(this).val().toUpperCase());
    })
    $('[name="enableShip"]').change(function(){
        validate_enableShip();
    })
    $('[name="shipGrade"]').change(function(){
        validate_shipGrade();
    })
    $("#shipDesc").keyup(function(){
        validate_shipDesc();
    })

    //5. on click form submit
    $("#shipmentTypeRegister").submit(function(){
        validate_shipModel();
        validate_shipCode();
        validate_enableShip()
        validate_shipGrade();
        validate_shipDesc();

        if(shipModelError && shipCodeError&&enableShipError
        &&shipGradeError&&shipDescError)
        return true;
        else return false;


    })

    });