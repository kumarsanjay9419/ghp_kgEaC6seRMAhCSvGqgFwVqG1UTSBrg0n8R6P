
 
 
 $(document).ready(function(){
                //1. hide the error
                $("#uomTypeError").hide();
                $("#uomModelError").hide();
                $("#uomDescError").hide();
                //2. create 
                var uomTypeError=false;
                var uomModelError=false;
                var uomDescError=false;
                //3. define validate function

                //for uomType
                function validate_uomType(){
                       var val=$("#uomType").val();
                       if(val==''){
                         $("#uomTypeError").show();
                         $("#uomTypeError").html("*please choose <b>Type</b>*"); 
                         $("#uomTypeError").css('color','red') ;
                         uomTypeError=false; 
                       }else{
                            $("#uomTypeError").hide();
                            uomTypeError=true;
                       }
                      return uomTypeError; 
                }

                //for uomModel
                function validate_uomModel(){
                       var val=$("#uomModel").val();
                       var exp=/^[A-Z\-\s]{4,12}$/;
                       if(val==''){
                         $("#uomModelError").show();
                         $("#uomModelError").html("*please choose <b>Model</b>*");
                         $("#uomModelError").css('color','red') ;
                         uomModelError=false; 
                       }

                    else if(!exp.test(val)){
                         $("#uomModelError").show();
                         $("#uomModelError").html("*only 4,12 chars allowed*");
                         $("#uomModelError").css('color','red') ;
                         uomModelError=false; 
                       }else{
                    	   
                     //AJAX START
                         var id=0;  //for Register
                         if($("#id").val()!=undefined){
                          id=$("#id").val();  //for edit
                  }
                 
                            $.ajax({
                            	url:'validate',
                            	data:{"model":val,"id":id},
                            	success:function(resTxt){
                            		if(resTxt!=""){ 
                            			//duplicate exist
                            			
                            			$("#uomModelError").show();
                                        $("#uomModelError").html(resTxt);
                                        $("#uomModelError").css('color','red') ;
                                        uomModelError=false; 
                            			} else{
                            				$("#uomModelError").hide();	
                            				uomModelError=true;
                            			}
                            	    } //success end
                            	});
                                 //AJAX END
                       }
                      return uomModelError; 
                }
                
                
                //for uomDesc
                function validate_uomDesc(){
                       var val=$("#uomDesc").val();
                       var exp=/^[A-Za-z0-9\-\s\.\:\;\''\""\,\@]{10,100}$/;
                        if(val==''){
                         $("#uomDescError").show();
                         $("#uomDescError").html("*please enter <b>Desc</b>*"); 
                         $("#uomDescError").css('color','red') ;
                         uomDescError=false; 
                       }

                      else if(!exp.test(val)){
                         $("#uomDescError").show();
                         $("#uomDescError").html("*only 10,100 chars allowed*"); 
                         $("#uomDescError").css('color','red') ;
                         uomDescError=false; 
                       }
                       
                       else{
                            $("#uomDescError").hide();
                            uomDescError=true;
                       }
                      return uomDescError; 
                }
                //4. create action event
                ///for uomType
                $("#uomType").change(function(){
                     validate_uomType();
                });

                //for uomModel
               $("#uomModel").keyup(function(){
                     validate_uomModel(); 
                     $(this).val( $(this).val().toUpperCase());
                });

                //for uomDesc
                $("#uomDesc").keyup(function(){
                     validate_uomDesc();
                   //  $(this).val( $(this).val().toUpperCase());
                });

                //5. on submit
                $("#uomForm").submit(function(){
                     validate_uomType();
                     validate_uomModel();
                     validate_uomDesc(); 
                     if(uomTypeError && uomModelError && uomDescError)
                     return true;
                     else 
                        return false;
                });


         }); //document
 
 
 
 
 
 
 
 
 
 
 