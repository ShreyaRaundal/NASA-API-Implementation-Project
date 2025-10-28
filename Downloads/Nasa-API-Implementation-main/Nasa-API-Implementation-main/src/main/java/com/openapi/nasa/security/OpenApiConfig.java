package com.openapi.nasa.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi Specification- Nasa Api Implementation",
                description = "OpenApi Documentation For Nasa's Astronomy Picture Of The Day And Nasa's Mars Rover Photos",
                termsOfService = "Terms Of Service",
                contact = @Contact(
                        name = "Varun G R",
                        email = "varungrvv@gmail.com",
                        url="http://nasa-webapp-env.eba-bpm6gg2n.ap-south-1.elasticbeanstalk.com/nasa/home-page"
                ),
                license =@License(
                        name = "Nasa Api",
                        url = "https://api.nasa.gov/"
                ),
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:5000"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "http://nasa-webapp-env.eba-bpm6gg2n.ap-south-1.elasticbeanstalk.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name="bearerAuth "
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth",
        scheme = "bearer",
        type= SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in= SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    // This is OpenApi Configuration Class //
}
