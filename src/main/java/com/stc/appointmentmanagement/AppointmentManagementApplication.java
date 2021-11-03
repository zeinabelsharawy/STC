package com.stc.appointmentmanagement;

import com.stc.appointmentmanagement.config.JpaAuditingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(JpaAuditingConfiguration.class)
public class AppointmentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentManagementApplication.class, args);
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.musala.gatewaymanagement.modules.gateway.rest"))
                .build();
    }

    private ApiInfo apiInfo(){
    	return new ApiInfoBuilder().title("Appointment Management")
				.description("Tool for managing appointments, where clinic admin can review appointments by date or patient  ")
				.termsOfServiceUrl("")
				.licenseUrl("")
				.build();
	}

}
