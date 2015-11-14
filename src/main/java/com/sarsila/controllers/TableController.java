package com.sarsila.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarsila.model.*;
import com.sarsila.model.dao.*;

@Controller
public class TableController {
	/*
    @RequestMapping(value="/GoGame", method=RequestMethod.GET)
    public String Game(Model model) {
        GoGame game = new GoGame();
    	Long id = game.startNewGame();
    	
    	//TODO: store game id into http session
    	
    	return "tabletest";
    }
    */
    /**
     * Initializes the game. Stores the game's unique id into the httpsession
     * @param model
     * @param request HTTPSession is used to store the game ID
     * @return
     */
    @RequestMapping(value="/start", method=RequestMethod.GET)
    public String Test(Model model, HttpServletRequest request) {
    	GoGame game = new GoGame();
    	Long id = game.startNewGame();
    //	GoGameDao dao = new GoGameSQLDaoImpl(); //TODO: refactor,move this to gogamedao
    //	Long id = dao.saveNewGame(game);
    //	game.turn=1; //black;
    	request.getSession().setAttribute("game", game);
    	//TODO: the turns
    	return "tabletest";
       
    }
    
    /**
     * Handles the click events from the html table
     * @param model
     * @param row Row index for the clicked cell
     * @param column Column index for the clicked cell
     * @param request
     * @return
     */
    @RequestMapping(value="/GoGame", method=RequestMethod.GET)
    public String Receive(Model model, @RequestParam("row") int row, @RequestParam("col") int column, HttpServletRequest request) {
  
    	GoGame game = (GoGame)request.getSession().getAttribute("game");
    	
    	ClickItem item = new ClickItem(row, column);
    	game.addClick(item);

    	//TODO: switch turns
    	//TODO: rename "tabletest" into something more appropriate 
    	return "tabletest";
    }
}
