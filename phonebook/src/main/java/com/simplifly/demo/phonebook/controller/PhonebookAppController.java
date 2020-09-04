package com.simplifly.demo.phonebook.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplifly.demo.phonebook.request.GuestRequest;
import com.simplifly.demo.phonebook.response.PhonebookResponse;

@RestController
public class PhonebookAppController {

	@PostMapping(value = "/toUpperCase", consumes = "application/json",
			produces = "application/json")
	public PhonebookResponse toUppercase(@RequestBody String data) {
		ObjectMapper om = new ObjectMapper();
		GuestRequest guest = null;
		try {
			guest = om.readValue(data, GuestRequest.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		printJsonValue(guest);
		// System.out.println("\n\n\t\tCalled toUppercase :: "
		// + (guest != null ? guest.getGuestName() : "NULL"));
		PhonebookResponse constructSuccessResponse = PhonebookResponse
				.constructSuccessResponse(getProperCase(guest));
		printJsonValue(constructSuccessResponse);
		return constructSuccessResponse;

	}

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
		//System.out.println("\n\n\t\t" + sb.toString().trim());
		return sb.toString().trim();
	}
}
