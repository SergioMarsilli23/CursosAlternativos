package mx.com.tutum.shared.infrastructure.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class JWTConfig extends WebSecurityConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/gui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/auth",
            "/res",
			"/res/**",
			"/admin",
			"/admin/*"
    };
    
    private final JWTAuthorizationFilter jwtAuthFilter;

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.getEncodedUrlBlocklist().remove("//");
		web.httpFirewall(firewall);
		
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}
}
