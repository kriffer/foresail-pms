package fi.foresail.pms.config;

import fi.foresail.pms.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity(debug = false)
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AccessDeniedHandler accessDeniedHandler;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable().authorizeRequests()
				.antMatchers("/", "/api/v1/**").permitAll()
				.antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.and()
				.authorizeRequests().antMatchers( "/main", "/bookings","/properties","/rooms","/account","/rates").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')").and()
				.authorizeRequests().antMatchers("/login", "/resources/**", "/webjars/**").permitAll().and()
				.formLogin()
				.loginPage("/login").usernameParameter("username").passwordParameter("password")
				.permitAll().defaultSuccessUrl("/")
				.failureUrl("/login?error")
				.and()
				.logout().logoutUrl("/logout").and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

		http.headers().frameOptions().sameOrigin();

	}
}
