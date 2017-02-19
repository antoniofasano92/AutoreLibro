package it.hellopersona.utility;

public class Utility {
	
	public static boolean isNotEmptyString(String input){
		return input != null && !input.equals("");
	}
	
	public  Integer parseIntFromString(String sInput){
		
		Integer result = null;
		try{
			
			result =Integer.parseInt(sInput);	
		}catch (NumberFormatException e){
			result=null;
		}
		
		return result;
	}

}
