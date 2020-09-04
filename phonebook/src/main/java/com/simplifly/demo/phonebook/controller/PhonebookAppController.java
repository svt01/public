package com.simplifly.demo.phonebook.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifly.demo.phonebook.request.GuestRequest;
import com.simplifly.demo.phonebook.response.PhonebookResponse;

/**
 * This is a controller class to handle phonebook app requests
 * 
 * @author sagarvt
 *
 */
@RestController
public class PhonebookAppController {

	@PostMapping(value = "/toUpperCase", consumes = "application/json",
			produces = "application/json")
	public PhonebookResponse toUppercase(@RequestBody String jsonString) {
		
		GuestRequest guestObject = (GuestRequest) getClassObject(jsonString,
				GuestRequest.class);
		
		//Logic
		printJsonValue(guestObject);
		String properCase = getProperCase(guestObject);

		// String response
		PhonebookResponse constructSuccessResponse = PhonebookResponse
				.constructSuccessResponse(properCase);

		printJsonValue(constructSuccessResponse);
		return constructSuccessResponse;

	}

	/**
	 * This method returns an object from a json string
	 * @param jsonString
	 * @param classType
	 * @return
	 */
	private Object getClassObject(String jsonString, Class<?> classType) {
		ObjectMapper om = new ObjectMapper();
		Object guest = null;
		try {
			guest = om.readValue(jsonString, classType);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return guest;
	}

	/**
	 * This method prints JSON representation
	 * 
	 * @param object
	 */
	private void printJsonValue(Object object) {
		ObjectMapper om = new ObjectMapper();
		try {
			System.out.println(om.writerWithDefaultPrettyPrinter()
					.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getProperCase(GuestRequest guest) {

		String guestName = guest.getGuestName();
		String[] split = guestName.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			sb.append(" ").append(String.valueOf(s.charAt(0)).toUpperCase())
					.append(s.substring(1));
		}
		// System.out.println("\n\n\t\t" + sb.toString().trim());
		return sb.toString().trim();
	}
}
