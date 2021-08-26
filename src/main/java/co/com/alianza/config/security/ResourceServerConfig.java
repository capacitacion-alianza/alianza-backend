package co.com.alianza.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	private final ResourceServerTokenServices tokenServices;
    private final TokenStore tokenStore;

    public ResourceServerConfig(ResourceServerTokenServices tokenServices, TokenStore tokenStore) {
        this.tokenServices = tokenServices;
        this.tokenStore = tokenStore;
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(new AuthenticationExceptionResponse())
                .tokenStore(this.tokenStore)
                .tokenServices(this.tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(new AuthenticationExceptionResponse())
                .and().requestMatchers()
                .and().authorizeRequests()
                .antMatchers(
                        "/oauth/**",
                        "/auth/**"
                ).permitAll()
                .antMatchers(HttpMethod.GET, "/productos/viewFile/**").permitAll()
                .anyRequest().authenticated();
    }

}
