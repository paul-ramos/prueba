package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Phones;
import com.example.demo.model.Persona;
import com.example.demo.service.PhonesService;

@RestController
@RequestMapping(value = "phone")
public class PhoneController {
	@Autowired
	PhonesService paisService;
	
	@GetMapping("/todos")
	public List<Phones> todos() {
		return paisService.findAll();

	}
	
    @RequestMapping(value = "/crear", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Phones crear(@Valid @RequestBody
                               Phones pais,
                               BindingResult result){
     
           
    	
      
              return  paisService.save(pais);
    }   
    
	
    @RequestMapping(value = "/uno/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Object uno(@PathVariable("id") long id) {
        
            return  paisService.findById(id);
        
    }	
    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
       return ResponseEntity.ok("POST request successful");
   }
}
