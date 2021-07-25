package org.dean;
import org.dean.MailService;
import org.dean.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.dean.User;

public class App 
{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
        UserService1 userService1 = context.getBean(UserService1.class);
        User user1 = userService.login("bob@example.com", "password");
        System.out.println(user1.getName());
	System.out.println(userService.getMailService() == userService1.getMailService());
	System.out.println(userService.getMailService().equals(userService1.getMailService()));
    }
}
