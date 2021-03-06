package com.sarsila.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarsila.model.*;

@Controller
public class TableController {
	

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
    	if (id != null) { // if ID is null, database connection has failed
    		request.getSession().setAttribute("game", game); //the old way of doing this
    		request.getSession().setAttribute("game_id", id);
    		return "table";
    	}
    	else return "failure"; //TODO: add failure.jsp
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
  
    	//GoGame game = (GoGame)request.getSession().getAttribute("game");
    	//TODO: init game(id) from the db, dont store it in the session
    	Long id = (Long)request.getSession().getAttribute("game_id");
    	GoGame game = new GoGame(id);
    	ClickItem item = new ClickItem(row, column, game.turn, null);
    	if (game.isCellEmpty(row, column)){
    		game.addClick(item);
    		game.analyzeAndClean();
    	}
    	else {
    		game.deleteClick(row, column, true);
    	}
    	return "table";
    }
    
    @RequestMapping(value="/skip", method=RequestMethod.GET)
    public String Skip(Model model, HttpServletRequest request) {
    	GoGame game = (GoGame)request.getSession().getAttribute("game");
    	game.switchTurns();
    	return "table";     
    }
}
