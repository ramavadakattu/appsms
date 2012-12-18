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
    	Logger.debug("Itemid = %d textweb-message = %d",itemId,params.get("txtweb-message"));   	
    	if (itemId == null)
    	{
    		if (params.get("txtweb-message") != null)
    		{
    			itemId = Long.valueOf(params.get("txtweb-message"));
    		}
    	}    	
    	/**
    	 * Call eBay API and get the title
    	 */
    	String title = ProductionApiWrapper.getItemTitle(itemId);    	  	
    	render("appsms/txtweb.html",itemId,title);
    	
    }

}