package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class SMSAppController extends Controller {

    public static void index() {
        render("appsms/main.html");
    }
    
    public static void getItemTitle(Long itemId)
    {
    	Logger.debug("Itemid = %d",itemId);   	
    	
    	/**
    	 * Call eBay API and get the title
    	 */
    	String title = ProductionApiWrapper.getItemTitle(itemId);    	  	
    	render("appsms/main.html",itemId,title);
    	
    }

}