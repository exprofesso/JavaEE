package evilNerd;


import evilNerd.domain.Cars;
import evilNerd.domain.Gender;
import evilNerd.domain.User;
import evilNerd.repository.impl.CarsRepositoryJdbcTemplateImpl;
import evilNerd.service.CarsService;
import evilNerd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//@Slf4j
public class SpringContextTester {

    private static final Logger log = Logger.getLogger(SpringContextTester.class);

    public static void main(String[] args) {


        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("evilNerd");

        UserService userService = annotationConfigApplicationContext.getBean(UserService.class);

        log.info(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));

        log.info(userService.findById(5L).toString());


        List<User> userCreate = userService.search("DIMA");
        for (User user : userCreate){
            log.info(userService.toString());
        }

//        System.out.println(userService.findAll().stream().map(User::getName).collect(Collectors.joining(", ")));
//        System.out.println(userService.findById(5L));
//        System.out.println(userService.search("Sasha"));



        User userForSave =
                User.builder()
                .name("Slavs")
                .surname("Petrov")
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

        log.info(carsService.findAll().stream().map(Cars::getModel).collect(Collectors.joining(", ")));

        log.info(carsService.findById(5L).toString());

        List<Cars> carsCreate = carsService.search("VAZ");
        for (Cars cars : carsCreate){
            log.info(carsService.toString());
        }

        Cars carsForSave =
                Cars.builder()
                .model("TAZ")
                .creationYear(2009)
                .price(3430F)
                .userId(7L)
                .color("BLACK")
                .build();

        System.out.println(carsService.save(carsForSave));




    }
}