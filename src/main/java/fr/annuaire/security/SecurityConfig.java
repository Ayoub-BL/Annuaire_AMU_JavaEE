package fr.annuaire.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
* @author Ayoub BOULAHYA (boulahya.ayoub@gmail.com OR ayoub.boulahya@etu.univ-amu.fr)
*/

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth
			.inMemoryAuthentication()
				//.withUser("BOULAHYA").password("{noop}1234").roles("ADMIN", "USER")
				.withUser("BOULAHYA").password(passwordEncoder().encode("1234")).roles("ADMIN", "USER")
				
				.and()
				//.withUser("user").password("{noop}1234").roles("USER")
				.withUser("17028432").password(passwordEncoder().encode("1234")).roles("ADMIN", "USER")
				
				.and()
				//.withUser("user").password("{noop}1234").roles("USER")
				.withUser("17028433").password(passwordEncoder().encode("1234")).roles("USER")
				
				;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
	    return new CustomLogoutSuccessHandler();
	}
	
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		
		http
		.authorizeRequests()
			.antMatchers("/anonymous*").anonymous()
			.antMatchers("/home","/groupes","/personnes","/affichePersonne","/modifierPersonne",
					"/chercherPersonne","/css/**","/img/**","/js/**","restpersonne", "/","/apropos",
					"/forgotPassword").permitAll()
			.anyRequest().authenticated()
		
		.and()
		.formLogin()
			.loginPage("/login").permitAll() // ==> login.html
			.loginProcessingUrl("/login") // login action
			.successHandler(myAuthenticationSuccessHandler())
			.usernameParameter("identifiant")
			.passwordParameter("motDePasse")
			.failureUrl("/login?error=true")
			
		.and()
		.logout().permitAll()
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.logoutUrl("/logout") // logout.html
		//.logoutSuccessHandler(logoutSuccessHandler())
		.logoutSuccessUrl("/home")
		
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		
		.and()
		.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400)
		
		.and()
		.sessionManagement()
		  .sessionFixation().migrateSession() // Protection against typical Session Fixation attacks
		
		.and()
		.csrf().disable()
		
		;
	}
	
}
