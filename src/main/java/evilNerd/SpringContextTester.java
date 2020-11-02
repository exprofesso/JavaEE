package evilNerd;


import evilNerd.domain.Cars;
import evilNerd.domain.User;
import evilNerd.service.CarsService;
import evilNerd.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.stream.Collectors;

public class SpringContextTester {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("evilNerd");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        System.out.println(userService.findById(5L));

        System.out.println("*******************");

                CarsService carsService = annotationConfigApplicationContext.getBean(CarsService.class);

        System.out.println(carsService.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

        System.out.println(carsService.findById(4L));




    }
}