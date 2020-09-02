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
		System.out.println("\n\n\t\tCalled toUppercase :: "
				+ (guest != null ? guest.getGuestName() : "NULL"));
		return PhonebookResponse.constructSuccessResponse(getProperCase(guest));

	}

	private String getProperCase(GuestRequest guest) {

		String guestName = guest.getGuestName();
		String[] split = guestName.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			sb.append(" ").append(String.valueOf(s.charAt(0)).toUpperCase())
					.append(s.substring(1));
		}
		System.out.println("\n\n\t\t" + sb.toString().trim());
		return sb.toString().trim();
	}
}
