## where

![ss_in_app_arct](pic\ss_in_app_arct.png) 

## Authentication flow

![springsecu_authen_flow](pic\springsecu_authen_flow.png)  


## User Management
Dependencies between the components involved in user management.. .  
*  UserDetailsService obj returns the details of a user, finding the user by its name
* The UserDetails contract describes the user seen by SS
* A user has one or more authorities, represented by the GrantedAuthority interface.
* To add operations such as create, delete, or change password to the user, the UserDetailsManager contract extends UserDetailsService

![user management](pic\user management.png) 

## Default FilterChain

![filterchain-1a](pic\filterchain-1a.png) 

**BasicAuthenticationFilter:** Tries to find a Basic Auth HTTP Header on the request and if found, tries to authenticate the user with the header’s username and password.

**UsernamePasswordAuthenticationFilter:** Tries to find a username/password request parameter/POST body and if found, tries to authenticate the user with those values.

**DefaultLoginPageGeneratingFilter:** Generates a login page for you, if you don’t explicitly disable that feature. THIS filter is why you get a default login page when enabling Spring Security.

**DefaultLogoutPageGeneratingFilter:** Generates a logout page for you, if you don’t explicitly disable that feature.

**FilterSecurityInterceptor:** Does your authorization.

 [in-depth](https://www.marcobehler.com/guides/spring-security)
 
## FilterChain

![filterchain-1b](pic\filterchain-1b.png) 


 ```
@Configuration
public class ProjectConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		http.addFilterBefore(new RequestValidationFilter(),BasicAuthenticationFilter.class)
			.addFilterAfter(new AuthenticationLoggingFilter(),BasicAuthenticationFilter.class)
			.authorizeRequests(c -> c.anyRequest().permitAll());
			return http.build();
		}
}
```
 * addFilterBefore(): we want the application to execute this custom filter before authentication.
 * addFilterAfter(): execute some logic after authen
 * addFilterAt(): add fil at the position off other fil
 
```
 http.addFilterAt(filter, BasicAuthenticationFilter.class).authorizeRequests(c -> c.anyRequest().permitAll());

```
 **Custom Filter**

 Write logic of filter inside doFilter: check if Request-Id header exst --> forward to next fil --> else set a http status without forward
 
```
public class RequestValidationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest,
					ServletResponse servletResponse,FilterChain filterChain)
					throws IOException, ServletException {...}}
```
* OncePerRequestFilter implements logic to make sure that the filter’s doFilter() method is executed only one time per request ex:logging

```
public class JwtRequestFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, 							HttpServletResponse response,FilterChain filterChain)
							throws ServletException, IOException {}}
```
* If you anticipate a component is not needed, can disable autoconfig

```
@SpringBootApplication(exclude ={UserDetailsServiceAutoConfiguration.class })
```

## Password Encoder

* For wanting multiple encoding stra, use delegatePE to delegate to another

![delegatingPE](pic\delegatingPE.png) 

## Authentication Provider

![Authen-Interface](pic\Authen-Interface.png)

```
public interface AuthenticationProvider {
	Authentication authenticate(Authentication authentication) hrows AuthenticationException;
	boolean supports(Class<?> authentication);}
```

```
@Component
public class CustomAuthenticationProvider mplements AuthenticationProvider {
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails u = userDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(password, u.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username,password,u.getAuthorities());}
		else {throw new BadCredentialsException("Something went wrong!");}
			}}
```


![authenprovider-flow](pic\authenprovider-flow.png)



## Security Context

![secucontext-1](pic\secucontext-1.png)

 Managed by SecurityContextHolder with 3 stra:
 * MODE_THREADLOCAL—Allows each thread to store its own details in the
security context. In a thread-per-request web application, this is a
common approach as each request has an individual thread

![secucontext-threadlocal](pic\secucontext-threadlocal.png)

 * MODE_INHERITABLETHREADLOCAL—Similar to THREADLOCAL but
also instructs Spring Security to copy the security context to the next
thread in case of an asynchronous method. This way the new thread running the @Async method inherits the security context.
@Async annotation is used with methods to instruct Spring to call the
annotated method on a separate thread.

![inheritthreadlocal](pic\inheritthreadlocal.png)

 
 * MODE_GLOBAL—all threads of appl see the same 
security context instance. ---> standalone appl, not thread-safe, need to take care of concurrent access

 To manage the SecurityContext for the threads that you create, you can use
 * DelegatingSecurityContextRunnable
 * DelegatingSecurityContextCallable
 * DelegatingSecurityContextExecutor
 
  DelegatingSecurityContextCallables: designed as a decorator of the Callable
object. When building such an object, you provide the callable task that the application executes
asynchronously. IT copies the details from the security context
to the new thread and then executes the task
 

```
public interface SecurityContext extends Serializable {
	Authentication getAuthentication();
	void setAuthentication(Authentication authentication);}
```

## Security exceptions

![ss_exception](pic\ss_exception.png)

##  AuthenticationEntryPoint

* To customize the response for a failed authentication
* Used directly by a component called ExceptionTranslationManager, which handles any
AccessDeniedException and AuthenticationException thrown within the filter chain. You can view the ExceptionTranslationManager as a bridge between Java exceptions and HTTP responses.

```
public class CustomEntryPoint implements AuthenticationEntryPoint {
 @Override
 public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, 
		 AuthenticationException e) throws IOException, ServletException {
		 httpServletResponse.addHeader("message", "Luke, I am your father!");
		httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());}
}
```

** AuthenticationSuccessHandler, AuthenticationFailureHandler**

	:u implement an object through which you can apply the logic executed for
authentication.


#Authorization Flow


![springsecu_authori_flow](pic\springsecu_authori_flow.png)

## Granted Authoritity

```
public interface GrantedAuthority extends Serializable {
		String getAuthority();}
```