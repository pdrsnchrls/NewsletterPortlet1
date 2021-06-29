package com.liferay.amf.newsletter.service.action;

import java.util.HashMap;

import org.osgi.service.component.annotations.Reference;

public class ProcessCreateWebContent {
	public void parseContent(String articleContent) {
		
		// split string for each dynamic element
		String[] content = articleContent.split("</dynamic-element>", 5);
		
		// a HashMap for storing the values of the newsletter/issue content
		HashMap<String, String> contentData = new HashMap<String, String>(); // a hashmap to store the data

		// data for storing and searching
		String[] key = new String[content.length], value = new String[content.length];
		String keySearchName = "name=\"", valueSearchName = "CDATA["; // string to search content for
		Character keyStopChar = '\"', valueStopChar = ']'; // character to stop at
		
		int i = 0; // iterator to keep track of positioning
		for (String c : content) {
			key[i]=splitString(c, keySearchName, keyStopChar);
			value[i]=splitString(c, valueSearchName, valueStopChar);
			contentData.put(key[i], value[i]);
			i++;
		}
		
		for (HashMap.Entry<String, String> e : contentData.entrySet())
            System.out.println("Key: " + e.getKey()+ "\t Value: " + e.getValue());
		
	}
	
	public String splitString(String string, String searchName, Character stopChar) {

		String result = ""; // String to be returned
		
		// find location of searchName and add its length to start at right place
		int location = string.indexOf(searchName) + searchName.length();
		Character charValue = string.charAt(location); // get first character
		
		while (!charValue.equals(stopChar)) { // as long as the character is not the token to stop at...
			result += charValue;
			location++;
			charValue = string.charAt(location); // new character
		}
		
		return result;
	}

}
