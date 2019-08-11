package com.web.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.blog.apachePOI.Generate_Document;
import com.web.blog.apachePOI.Read_Doc_Table;
import com.web.blog.model.LogIn;
import com.web.blog.model.apachePOI;
import com.web.blog.model.createCV;

@Controller
public class crudCVController {

	@GetMapping(value = "/editOwnCV")
	public ModelAndView read() {
				
				apachePOI apachepoi = new apachePOI(); 
				apachepoi.setCollection(Read_Doc_Table.readDocx());
				ModelAndView model = new ModelAndView("editOwnCV");
			    model.addObject("login", new LogIn());
			    model.addObject("createCV", new createCV());
			    model.addObject("readDocx", apachepoi);
			    model.addObject("currentlyPage","CV");

	return model; 
				
	}

	
	@PostMapping(value = "/createCV")
    public ModelAndView create(createCV modelCV) {

		Generate_Document.generate_doc(
				modelCV.getName()
			   ,modelCV.getPhone()
			   ,modelCV.getAddress()
			   ,modelCV.getEmail()
			   ,modelCV.getDateofBirth()
			   ,modelCV.getNationality()
			   ,modelCV.getSkills()
			   ,modelCV.getProgramming()
			   ,modelCV.getLanguages()
			   );
		
		return new ModelAndView("redirect:/editOwnCV");
		
    }
	
}
