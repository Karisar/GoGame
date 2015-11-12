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

import com.sarsila.model.*;

@Controller
public class TableController {
    @RequestMapping(value="/GoGame", method=RequestMethod.GET)
    public String Game(Model model) {
        GoGame game = new GoGame();
    	Long id = game.startNewGame();
    	
    	//TODO: store game id into http session
    	
    	return "tabletest";
    }
    
    //http://stackoverflow.com/questions/13213061/springmvc-requestmapping-for-get-parameters
    @RequestMapping(value="/tabletest", method=RequestMethod.GET)
    public String Test(Model model) {
    	GoGame game = new GoGame();
    	Long id = game.startNewGame();
        return "tabletest";
       
    }
    
    @RequestMapping(value="/GoGame/{row}/{column}", method=RequestMethod.GET)
    public String Test(Model model, @PathVariable("row") int row, @PathVariable("column") int column) {
  
    	Long id = new Long(123); //TODO: get id from httpsession
    	GoGame game = new GoGame(id);
    	
    	ClickItem item = new ClickItem(row, column);
    	game.addClick(item);
    	
    	return "tabletest";
    }
}
