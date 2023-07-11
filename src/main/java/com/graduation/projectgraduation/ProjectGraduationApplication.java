package com.graduation.projectgraduation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    servers = {
        @Server(url = "http://localhost:8080", description = "localhost")
    },
    info = @Info(
        title = "Api for project graduation",
        version = "1.0.0",
        description = "This project create api",
        termsOfService = "",
        contact = @Contact(
            name = "Email",
            email = "voanhtuan13321@gmail.com"
        ),
        license = @License(
            name = "Facebook",
            url = "https://www.facebook.com/13301vat"
        )
    )
)
public class ProjectGraduationApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProjectGraduationApplication.class, args);
  }

}
