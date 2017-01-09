package com.niit.collaboration.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Comment;
import com.niit.collaboration.model.User;



@RestController
public class BlogController {

private static final Logger logger	= LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	BlogDAO blogDAO;
	
	@RequestMapping(value="/blogs",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlogs(){
		logger.debug("calling method listAllBlogs");
		List<Blog> blog=blogDAO.list();
		if(blog.isEmpty()){
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}

	@RequestMapping(value="/blog/",method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		logger.debug("calling method createBlog" + blog.getBlog_id());
		if(blogDAO.get(blog.getBlog_id())==null){
			blogDAO.save(blog);			
		}
		logger.debug("blog already exists with id:" + blog.getBlog_id());
		blog.setErrormessage("blog already exists with id:" + blog.getBlog_id());
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			}
	
	@RequestMapping(value="/blog/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int blog_id,@RequestBody Blog blog){
		logger.debug("calling method updateBlog" + blog.getBlog_id());
		if(blogDAO.get(blog_id)==null){
			logger.debug("blog does not exists with id:" + blog.getBlog_id());		
			blog=new Blog();
			blog.setErrormessage("blog does not exists with id:" + blog.getBlog_id());
			return new ResponseEntity<Blog> (blog,HttpStatus.NOT_FOUND);
		}
		blogDAO.update(blog);
		logger.debug("blog updated successfully");
		return new ResponseEntity<Blog> (blog,HttpStatus.OK);		
	}

	@RequestMapping(value="/blog/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int blog_id){
		logger.debug("calling method deleteBlog for blog id: " + blog_id);
		Blog blog=blogDAO.get(blog_id);
		if(blog==null){
			logger.debug("blog does not exists with id:" + blog_id);
			blog=new Blog();
			blog.setErrormessage("blog does not exists with id:" + blog_id);
			return new ResponseEntity<Blog> (blog,HttpStatus.NOT_FOUND);	
		}
		blogDAO.delete(blog_id);
		logger.debug("blog deleted successfully");
		return new ResponseEntity<Blog> (blog,HttpStatus.OK);		
	}
	
	@RequestMapping(value="/blog/{id}",method=RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int blog_id){
		logger.debug("calling method getBlog for blog id: " + blog_id);
		Blog blog=blogDAO.get(blog_id);
		if(blog==null){
			logger.debug("blog does not exists with id:" + blog_id);
			blog=new Blog();
			blog.setErrormessage("blog does not exists with id:" + blog_id);
			return new ResponseEntity<Blog> (blog,HttpStatus.NOT_FOUND);
		}
		logger.debug("blog exists with id:" + blog_id);
		return new ResponseEntity<Blog> (blog,HttpStatus.OK);
	}


	@RequestMapping(value="/blogcomment/{id}",method=RequestMethod.POST)
	public ResponseEntity<Comment> createBlogComment(@PathVariable("id") int blog_id,@RequestBody Comment blogcomment,HttpSession httpSession){
		logger.debug("calling method createBlogComment" + blogcomment.getBlog_id());
		Integer loogedInUserID=(Integer) httpSession.getAttribute("loggedInUserID");
		User username=(User) httpSession.getAttribute("loggedInUser");
		blogcomment.setUser_id(loogedInUserID);
		Date dt=new java.util.Date();
		blogcomment.setDate(dt.toString());
		blogcomment.setUser_id(username.getUser_id());
		blogcomment.setBlog_id(blog_id);
		if(blogDAO.addComment(blogcomment)==true){
			blogcomment.setErrorcode("200");
			blogcomment.setErrormessage("Comment saved");
		
		}else{
			return new ResponseEntity<Comment>(blogcomment,HttpStatus.BAD_REQUEST);
		}
				
		return new ResponseEntity<Comment>(blogcomment,HttpStatus.OK);
			}

	@RequestMapping(value="/blogscommentlistperblog/{id}",method=RequestMethod.GET)
	public ResponseEntity<List<Comment>> listAllBlogsCommentsPerBlog(@PathVariable("id") int blog_id){
		logger.debug("calling method listAllBlogs");
		List<Comment> comment=blogDAO.listComment(blog_id);
		if(comment.isEmpty()){
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(comment,HttpStatus.OK);
	}


	@RequestMapping(value="/blogscommentlist/",method=RequestMethod.GET)
	public ResponseEntity<List<Comment>> listAllBlogsComments(){
		logger.debug("calling method listAllBlogs");
		List<Comment> blogcomment=blogDAO.listOfAllComment();
		if(blogcomment.isEmpty()){
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(blogcomment,HttpStatus.OK);
	}

}
