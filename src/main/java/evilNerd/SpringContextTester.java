package evilNerd;


import evilNerd.domain.Cars;
import evilNerd.domain.Gender;
import evilNerd.domain.User;
import evilNerd.service.CarsService;
import evilNerd.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.Timestamp;
import java.util.Date;
import java.util.stream.Collectors;

public class SpringContextTester {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("evilNerd");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        System.out.println(userService.findById(5L));

        System.out.println(userService.search("DIMA"));

        User userForSave =
                User.builder()
                .name("VADIM")
                .surname("Podkolzin")
                        .birthDate(new Date())
                        .gender(Gender.MALE)
                        .created(new Timestamp(new Date().getTime()))
                        .changed(new Timestamp(new Date().getTime()))
                        .weight(95F)
                .build();

        System.out.println(userService.save(userForSave));

        System.out.println("*******************");

                CarsService carsService = annotationConfigApplicationContext.getBean(CarsService.class);

        System.out.println(carsService.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

        System.out.println(carsService.findById(4L));

        System.out.println(carsService.search("VW"));

        Cars carsForSave =
                Cars.builder()
                .model("BELAZ")
                .creationYear(2000)
                .price(299430F)
                .userId(5L)
                .color("BLACK")
                .build();

        System.out.println();




    }
}