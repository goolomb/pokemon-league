package cz.muni.fi.pa165.web.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
/**
 *
 * @author Martin Golomb (goolomb)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username,password,enabled from User where username=?")
                .authoritiesByUsernameQuery("select username,role from User where username=?");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers(
                        "/pokemon/new/**",
                        "/pokemon/create**"
                        ).hasRole("ADMIN") // only admin can change pokemons
                .antMatchers("/rest/**").hasRole("ADMIN") // allow only admin to use rest api
                .antMatchers(
                        "/trainer/delete/**",
                        "/trainer/update/**",
                        "/trainer/new/**",
                        "/trainer/create/**"
                        ).hasRole("ADMIN") // only admin can change trainers
                .antMatchers(
                        "/pokemon/list/**",
                        "/trainer/list/**",
                        "stadium/list/**"
                        ).hasAnyRole("ADMIN", "USER") // everyone can list
                .antMatchers(
                        "/stadium/new/**",
                        "/stadium/create/**"
                        ).hasRole("ADMIN") // only admin can change stadium
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

        http
                .logout()
                .logoutSuccessUrl("/logout")
                .invalidateHttpSession(true);
        http
                // Sets character encoding to POST parameters for correct conversion.
                // This must be done as the first thing (before any security filters kick in) otherwise it has no effect at all.
                .addFilterBefore(new CharacterEncodingFilter("UTF-8"), ChannelProcessingFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
