/**
 * 
 */
package controllers;

import java.io.IOException;

import play.Play;
import play.Logger;


import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.GetItemCall;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;

/**
 * @author rvadakattu
 *
 */
public class ProductionApiWrapper {
	
	private static ApiContext eBayApiContext;


	/**
	 * Populate eBay SDK ApiContext object with data input from user
	 * 
	 * @param username
	 * @return ApiContext object
	 */
	protected synchronized static ApiContext getApiContext()
			throws IOException {

		if (eBayApiContext != null) {
			return eBayApiContext;
		} else {
			String input = null;
			String apiurl =Play.configuration.getProperty("prodapiurl");
			eBayApiContext = new ApiContext();
			ApiCredential cred = eBayApiContext.getApiCredential();
			input = Play.configuration.getProperty("produsertoken");
			cred.seteBayToken(input);
			eBayApiContext.setApiServerUrl(apiurl);
			return eBayApiContext;
		}
	}
	
	
	public static String getItemTitle(Long itemId)
	{
		GetItemCall gc;
		try {
			gc = new GetItemCall(getApiContext());
		
	      DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
	          DetailLevelCodeType.RETURN_ALL,
	          DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
	          DetailLevelCodeType.ITEM_RETURN_DESCRIPTION
	      };
	      gc.setDetailLevel(detailLevels);

	      ItemType item;
		
			item = gc.getItem(itemId.toString());
		
	      Logger.debug("Item title = %s",item.getTitle());
	      return item.getTitle();
		} 
		catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		Logger.debug("item not found");
		return "item not found";	      
	      
	}

	
	
	
	
	
	

}
