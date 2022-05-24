package io.sfer.xxx.Configuration;

import io.sfer.xxx.Model.Role;
import io.sfer.xxx.Model.User;
import io.sfer.xxx.Respository.RoleRepository;
import io.sfer.xxx.Respository.UserRepository;
import io.sfer.xxx.Service.RoleService;
import io.sfer.xxx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String specials = "~=+%^*/()[]{}/!@#$?|";
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static final String ALL = alpha + alphaUpperCase + digits + specials;
	
	private static Random generator = new Random();
	
	@Autowired
	RoleService roleService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String email = token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findUserByEmail(email).isPresent()) {
			
		} else {
			User user = new User();
			Object fname = token.getPrincipal().getAttributes().get("given_name");
			if(fname == null) {
				user.setFname(" ");
			} else {
				user.setFname(fname.toString());
			}
			Object lname = token.getPrincipal().getAttributes().get("family_name");
			if(lname == null) {
				user.setLname(" ");
			} else {
				user.setLname(lname.toString());
			}
			user.setEmail(email);
			List<Role> roles = new ArrayList<>();
			roles.add(roleService.getRolebyId(2L));
			user.setRoles(roles);
			user.setPassword(randomPassword(8));
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(user);
		}
		redirectStrategy.sendRedirect(request, response, "/");
	}
	
	public static int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}
	
	public String randomPassword(int numberOfCharactor) {
		List<String> result = new ArrayList<>();
		Consumer<String> appendChar = s -> {
			int number = randomNumber(0, s.length() - 1);
			result.add("" + s.charAt(number));
		};
		appendChar.accept(digits);
		appendChar.accept(specials);
		while (result.size() < numberOfCharactor) {
			appendChar.accept(ALL);
		}
		Collections.shuffle(result, generator);
		return String.join("", result);
	}
}
