package com.sarsila.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.annotation.Validated;

@Controller
public class TableController {
    @RequestMapping(value="/GoGame", method=RequestMethod.GET)
    public String Game(Model model) {
       // model.addAttribute("greeting", new FormOlio());
        return "result";
    }
    
    //http://stackoverflow.com/questions/13213061/springmvc-requestmapping-for-get-parameters
    @RequestMapping(value="/tabletest", method=RequestMethod.GET)
    public String Test(Model model) {
       // model.addAttribute("greeting", new FormOlio());
        return "tabletest";
    }
    
    @RequestMapping(value="/GoGame/{row}/{column}", method=RequestMethod.GET)
    public String Test(Model model, @PathVariable("row") int row, @PathVariable("column") int column) {
       // model.addAttribute("greeting", new FormOlio());
    	
        return "tabletest";
    }
}
