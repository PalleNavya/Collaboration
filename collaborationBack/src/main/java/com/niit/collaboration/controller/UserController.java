package com.niit.collaboration.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;



@RestController
public class UserController {
	
	private static final Logger logger	= LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	FriendDAO friendDAO;
		
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers(){
		logger.debug("calling method listAllUsers");
		List<User> user=userDAO.list();
		if(user.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}

	@RequestMapping(value="/user/",method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){//,@RequestParam("image") MultipartFile file){
		logger.debug("calling method createUser" + user.getUser_id());
		if(userDAO.get(user.getUser_id())==null){
			userDAO.save(user);			
		}
		
		/*MultipartFile image=user.getImage();
		Path path;
		path = Paths.get(
				"E://CollaborationProject//CollaborationFrontEnd//WebContent//" + user.getUser_id() + ".jpg");
		System.out.println("Path = " + path);
		System.out.println("File name = " + user.getImage().getOriginalFilename());
		if(image==null){
			System.out.println("Image not found");
		}
		else if (image != null && !image.isEmpty()) {
			try {
				image.transferTo(new File(path.toString()));
				System.out.println("Image Saved in:" + path.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Image not saved");
			}
		}*/

		
		logger.debug("user already exists with id:" + user.getUser_id());
		user.setErrormessage("user saved with id:" + user.getUser_id());
		return new ResponseEntity<User>(user,HttpStatus.OK);
			}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int user_id,@RequestBody User user){
		logger.debug("calling method updateUser" + user.getUser_id());
		if(userDAO.get(user_id)==null){
			logger.debug("user does not exists with id:" + user.getUser_id());		
			user=new User();
			user.setErrormessage("user does not exists with id:" + user.getUser_id());
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
		}
		userDAO.update(user);
		logger.debug("user updated successfully");
		return new ResponseEntity<User> (user,HttpStatus.OK);		
	}

	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int user_id){
		logger.debug("calling method deleteUser for user id: " + user_id);
		//String x=Integer.toString(user_id);
		User user=userDAO.get(user_id);
		
		if(user==null){
			logger.debug("user does not exists with id:" + user_id);
			user=new User();
			user.setErrormessage("user does not exists with id:" + user_id);
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);	
		}else
		userDAO.delete(user_id);
		logger.debug("user deleted successfully");
		return new ResponseEntity<User> (user,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int user_id){
		logger.debug("calling method getUser for user id: " + user_id);
		User user=userDAO.get(user_id);
		if(user==null){
			logger.debug("user does not exists with id:" + user_id);
			user=new User();
			user.setErrormessage("user does not exists with id:" + user_id);
			return new ResponseEntity<User> (user,HttpStatus.NOT_FOUND);
		}
		logger.debug("user exists with id:" + user_id);
		return new ResponseEntity<User> (user,HttpStatus.OK);
	}

	@RequestMapping(value="/user/authenticate/",method=RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user,HttpSession session){
		logger.debug("calling method a uthenticate");	
		user=userDAO.isValidUser(user.getEmail(),user.getPassword());	
		if(user==null){
			user=new User();
			user.setErrormessage("Invalid Credentials.Please enter valid credentials");
			return new ResponseEntity<User>(user,HttpStatus.UNAUTHORIZED);
				}
		else{
			logger.debug("Valid credentials");
			session.setAttribute("loggedInUser",user);
			session.setAttribute("loggedInUserID",user.getUser_id());
			friendDAO.setOnline(user.getUser_id());
			userDAO.setOnline(user.getUser_id());
			}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public ResponseEntity<User> logOut(HttpSession session){
		
		//Integer loogedInUserID=(Integer) session.getAttribute("loggedInUserID");
		User user=new User();
		friendDAO.setOffline(user.getUser_id());
		userDAO.setOffline(user.getUser_id());
		
		session.invalidate();
		user.setErrorcode("200");
		user.setErrormessage("successfully logged out");
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}}

