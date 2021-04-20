package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.entity.Informacja;
import platform.service.Informacje;

@SpringBootApplication
//@RestController
public class CodeSharingPlatform {

    public static Informacja kot=new Informacja("public static void main(String[] args) {\n" +
                                                  "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                                                  "}");
    public  static Informacje zbiorKotow = new Informacje() ;

    public static void main(String[] args) {


        SpringApplication.run(CodeSharingPlatform.class, args);
    }

}
