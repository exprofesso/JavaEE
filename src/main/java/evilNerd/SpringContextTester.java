package evilNerd;


import evilNerd.domain.User;
import evilNerd.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.stream.Collectors;

public class SpringContextTester {

    public static void main(String[] args) {

//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new
//                ClassPathXmlApplicationContext ("classpath:application-context.xml");
//
//       User user1 = (User)classPathXmlApplicationContext.getBean("user1");
//        User user2 = (User)classPathXmlApplicationContext.getBean("user2");
//        System.out.println(user1);
//        System.out.println(user1.getId());
//        System.out.println(user1.getName());
//        System.out.println(user1.getSurname());
//       System.out.println(user1.getUserCars());
//
//        System.out.println("________________________");
//
//        System.out.println(user2);
//        System.out.println(user2.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getSurname());
//     System.out.println(user2.getUserCars());

//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new
//                AnnotationConfigApplicationContext("evilNerd");
//
//        DatabaseConfig bean = annotationConfigApplicationContext.getBean(DatabaseConfig.class);
//
//        System.out.println(bean);
//
//        Cars generatedCars = annotationConfigApplicationContext.getBean(Cars.class);
//        System.out.println(generatedCars);
//
//        UserRepository userRepository = annotationConfigApplicationContext.getBean(UserRepository.class);
//
//        System.out.println(userRepository.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));
//
//     CarsRepository carsRepository = annotationConfigApplicationContext.getBean(CarsRepository.class);
//
//     System.out.println(carsRepository.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new
                AnnotationConfigApplicationContext("evilNerd");

       UserService userService =  annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

//        CarsService carsService = annotationConfigApplicationContext.getBean(CarsService.class);
//
//        System.out.println(carsService.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

    }


}
