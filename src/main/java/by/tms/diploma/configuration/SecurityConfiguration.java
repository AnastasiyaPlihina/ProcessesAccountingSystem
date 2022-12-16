package by.tms.diploma.configuration;

import by.tms.diploma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    private static final String[] PUBLIC_URLS = {
            "/",
            "/equipmentInfo",
            "/selectEquipment",
            "/equipmentLog"
    };
    private static final String[] ADMIN_AND_HEAD_URLS = {
            "user/addEmployee",
            "user/{id}/deleteEmployee",
            "user/addEquipment",
            "user/{id}/deleteEquipment"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers("/user/addDepartment").hasAuthority("ADMIN")
                .antMatchers(ADMIN_AND_HEAD_URLS).hasAnyAuthority("ADMIN", "HEAD_OF_DEPARTMENT")
                .antMatchers("user/selectProcess", "user/stopProcess").hasAnyAuthority("PRODUCTION_WORKER", "SERVICE_ENGINEER")
                .antMatchers("user/showEquipmentList").hasAnyAuthority("ADMIN", "HEAD_OF_DEPARTMENT", "PRODUCTION_WORKER", "SERVICE_ENGINEER")
                .antMatchers("/user/cleaning","/user/production").hasAuthority("PRODUCTION_WORKER")
                .antMatchers("/user/maintenance","/user/qualification").hasAuthority("SERVICE_ENGINEER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }
}
