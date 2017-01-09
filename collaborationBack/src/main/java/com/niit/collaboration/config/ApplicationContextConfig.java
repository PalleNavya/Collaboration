package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.BlogDAOImpl;
import com.niit.collaboration.dao.ChatDAOImpl;
import com.niit.collaboration.dao.ForumDAOImpl;
import com.niit.collaboration.dao.JobDAOImpl;
import com.niit.collaboration.dao.UserDAOImpl;
import com.niit.collaboration.model.BaseDomain;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Comment;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplication;
import com.niit.collaboration.model.Message;
import com.niit.collaboration.model.OutputMessage;
import com.niit.collaboration.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name = "dataSource")                               
	public DataSource getOracleDataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

	    dataSource.setUsername("project1");
	    dataSource.setPassword("project1");
	    return dataSource;
	}

	private Properties getHibernateProperties() {
	   

	      Properties connectionProperties=new Properties();

		  connectionProperties.setProperty("hibernate.show_sql", "true");   //to display the queries ,,,,can be given false if u want to hide the queries
		  connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		  connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		  connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys","true");
		 // connectionProperties.setProperty("hibernate.current_session_context_class", "thread");
		 // dataSource.setConnectionProperties(connectionProperties);
		  return connectionProperties;
		}



	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Job.class);
	sessionBuilder.addAnnotatedClass(JobApplication.class);
	sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(BaseDomain.class);
		sessionBuilder.addAnnotatedClass(Message.class);
		sessionBuilder.addAnnotatedClass(OutputMessage.class);
		sessionBuilder.addAnnotatedClass(Comment.class);

		return sessionBuilder.buildSessionFactory();
	}

		
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);  //should be same name as bean name of session factory
		return transactionManager;
		}
		
		@Autowired
		@Bean(name = "user")
            public User getUser (){
			return new User();
		}
		
		
		@Autowired
		@Bean(name = "userDAO")
            public UserDAOImpl getUserDAOImpl (SessionFactory sessionFactory){
			return new UserDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name = "blog")
            public Blog getBlog(){
			return new Blog();
		}
		
		
		@Autowired
		@Bean(name = "blogDAO")
            public BlogDAOImpl getBlogDAOImpl (SessionFactory sessionFactory){
			return new BlogDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name = "forum")
            public Forum getForum(){
			return new Forum();
		}
		
		
		@Autowired
		@Bean(name = "forumDAO")
            public ForumDAOImpl getForumDAOImpl (SessionFactory sessionFactory){
			return new ForumDAOImpl(sessionFactory);
		}

		@Autowired
		@Bean(name = "chat")
            public Chat getChat(){
			return new Chat();
		}
		
		
		@Autowired
		@Bean(name = "chatDAO")
            public ChatDAOImpl getChatDAOImpl (SessionFactory sessionFactory){
			return new ChatDAOImpl(sessionFactory);
		}
		@Autowired
		@Bean(name = "Job")
            public Job getJob(){
			return new Job();
		}
		
		
		@Autowired
		@Bean(name = "JobDAO")
            public JobDAOImpl getJobDAOImpl (SessionFactory sessionFactory){
			return new JobDAOImpl(sessionFactory);
		}

}
