package com.openapi.nasa.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;


import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


@Configuration
public class SecurityConfiguration {



    // JDBC MEMORY DETAILS MANAGER FOR AUTH AND ROLES-> CUSTOM TABLE SCHEMA //

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from nasa_members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from nasa_roles where user_id=?");
        return jdbcUserDetailsManager;
    }

    // custom security filter  chain //

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config ->

                        config
                                // securing endpoints for api-apods //
                                // permitting static resources allowing them to permit all //
                                .requestMatchers("/images/**", "/resources/**", "/static/**", "/public/**", "/webui/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/authenticate").permitAll()
                              //  .requestMatchers(HttpMethod.GET,"/get-token").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/swagger-resources/**").permitAll()
                                .requestMatchers("/swagger-ui.html").permitAll()
                                .requestMatchers("/swaggerdoc.html").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/nasa/home-page").hasAnyAuthority("SCOPE_ROLE_EMPLOYEE","ROLE_EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/apod").hasAnyAuthority("SCOPE_ROLE_EMPLOYEE","ROLE_EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/apods").hasAnyAuthority("SCOPE_ROLE_EMPLOYEE","ROLE_EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/save-apod").hasAnyAuthority("SCOPE_ROLE_EMPLOYEE","ROLE_EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/apod/**").hasAnyAuthority("SCOPE_ROLE_EMPLOYEE","ROLE_EMPLOYEE")
                                .requestMatchers(HttpMethod.DELETE, "/api/apod/**").hasAnyAuthority("SCOPE_ROLE_ADMIN","ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/apod/**").hasAnyAuthority("SCOPE_ROLE_ADMIN","ROLE_ADMIN")
                                .anyRequest().authenticated()
                // form login //
                ).formLogin(form -> form
                        .loginPage("/show-login-page")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                // logout //
                .logout(logout -> logout.permitAll()
                                  .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")  // Clearing JSESSIONID cookie
                        .clearAuthentication(true)  // Clearing current authentication
                        .addLogoutHandler((request, response, authentication) -> {
                            // Additional custom logout handling, if needed
                        }))
                // Disabled caching for login and logout pages
                .headers(headers -> headers
                        .cacheControl().disable()
                        .frameOptions().disable())
                // jwt configuration //
                .httpBasic(Customizer.withDefaults())
                //.csrf(csrf->csrf.disable())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }

  // JWT CONFIGURATION //
  @Bean
  public KeyPair keyPair()
  {
      try {
          KeyPairGenerator keyPairGenerator =  keyPairGenerator = KeyPairGenerator.getInstance("RSA");
          keyPairGenerator.initialize(2048);
          return  keyPairGenerator.generateKeyPair();
      } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
      }

  }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair)
    {
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey)
    {
        var jwkSet=new JWKSet(rsaKey);
        return (jwkSelector,context) ->jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource)
    {
        return new NimbusJwtEncoder(jwkSource);
    }
}