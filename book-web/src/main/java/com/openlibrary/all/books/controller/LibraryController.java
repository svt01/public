package com.openlibrary.all.books.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openlibrary.all.books.service.BookService;
import com.openlibrary.all.books.service.UserService;

@Controller
public class LibraryController {

	private static final String SUCCESS = "SUCCESS";

	@Value("${all.book.backend.message}")
	private String BOOKAPIOK;

	@Value(value = "${all.book.backend.version}")
	private String version;

	private static Logger LOGGER = LoggerFactory
			.getLogger(LibraryController.class);

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public @ResponseBody String health() {
		return BOOKAPIOK + this.version;
	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String showIndex() {
		return "index";
	}

	@RequestMapping(value = "/view-books", method = RequestMethod.GET)
	public String showBookList(ModelMap map) {
		map.addAttribute("bookList", bookService.list());
		return "view-books";
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.GET)
	public String showAddBook(ModelMap map) {
		map.addAttribute("bookModel", new BookModel());
		return "add-book";
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public String addBook(ModelMap map, @Valid BookModel book,
			BindingResult result) {
		if (result.hasErrors()) {
			return "add-book";
		}
		bookService.create(book);
		return "redirect:/view-books";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.POST)
	public String updatePage(ModelMap map, @Valid BookModel book,
			BindingResult result) {
		if (result.hasErrors()) {
			return "add-book";
		}
		map.addAttribute("action", "Update");
		String user = "" + map.get("user");
		System.out.println(" User: " + user);
		book.setUser(user);
		bookService.update(book);
		return "redirect:/view-books";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.GET)
	public String showUpdatePage(ModelMap map, BookModel book) {
		BookModel findOne = bookService.findOne(book);
		System.out.println("Retrieved book : " + findOne);
		map.addAttribute("bookModel", findOne);
		return "add-book";
	}

	@RequestMapping(value = "/delete-book", method = RequestMethod.GET)
	public String deleteBook(ModelMap map, BookModel book) {
		bookService.delete(book);
		return "redirect:/view-books";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap map, BookModel book) {
		return "welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(ModelMap map, UserModel book) {
		return "login1";
	}

	@RequestMapping(value = "/login1", method = RequestMethod.GET)
	public String showLogin1(ModelMap map, UserModel book) {
		return "login1";
	}

	@RequestMapping(value = "/login1", method = RequestMethod.POST, 
			consumes="application/json", produces="application/json")
	public AppResponse login1(ModelMap map, @RequestBody String data) {
		ObjectMapper om = new ObjectMapper();
		UserModel user;
		System.out.println("\n\ndata ::" + data);

		try {
			user = om.readValue(data, UserModel.class);
		
		System.out.println("\n\nLogin for ::" + user);
		System.out.println("\n\nmap ::" + map);
		map.addAttribute("user", user.getUsername());
		if (findUser(user)) {
			return new AppResponse("welcome");
		} else {
			return new AppResponse("login");
		}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AppResponse("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST,
			consumes = "application/json")
	public String login(ModelMap map, @RequestBody String data,
			UserModel user) throws Exception{
		System.out.println("\n\nLogin for ::" + user);
		System.out.println("\n\nmap ::" + map);
		System.out.println("\n\ndata ::" + data);
		map.addAttribute("user", user.getUsername());
		if (findUser(user)) {
			return "welcome";
		} else {
			System.out.println("Invalid credentials");
			map.put("errorMessage", "Invalid credentials. Try again !");
			throw new Exception("Login invalid");
		}
	}

	private boolean findUser(UserModel user) {
		List<UserModel> find = userService.find(user);
		System.out.println(find);
		for (UserModel u : find) {
			if (u.getUsername().equals(user.getUsername())
					&& u.getPassword().equals((user.getPassword()))) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistration(ModelMap map, UserModel user) {
		map.addAttribute("userModel", new UserModel());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(ModelMap map, UserModel user) {
		System.out.println(user);
		userService.create(user);
		map.addAttribute("username", user.getUsername());
		map.addAttribute("password", "");

		return "redirect:/login";
	}

}

class AppResponse{
	private String path;
	
	public AppResponse(String path) {
		this.path = path;
	}
}
