import controller.MorseController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan( basePackageClasses= {controller.MorseController.class, service.impl.MorseService.class})
public class MorseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorseApplication.class, args);
    }
}
