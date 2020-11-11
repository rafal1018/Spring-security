package pl.strefakursow.Spring.Security.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuthenticationManager(AuthenticationManagerBuilder builder, SecretAuthenticationProvider provider){
        builder.authenticationProvider(provider);
    }

    @Order(0)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/secured-basic").authorizeRequests().anyRequest()
                .authenticated().and().httpBasic().and().csrf().disable();
    }

    @Order(1)
    @Configuration
    public static class FormLoginConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/secured-form/**")
                    .authorizeRequests()
                    .antMatchers("/secured-form/login")
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/secured-form/login")
                    .loginProcessingUrl("/secured-form/login")
                    .and()
                    .csrf().disable();
        }
    }

    @Configuration
    public static class SecurityContextHolderConfig {
        @Bean
        public SecurityContextHolderStrategy securityContextHolderStrategy() {
            return SecurityContextHolder.getContextHolderStrategy();
        }
    }
}
