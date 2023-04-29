package javatechnology.project.configuration;

import javatechnology.project.model.User;
import javatechnology.project.repository.RoleRepository;
import javatechnology.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = token.getPrincipal().getAttributes().get("email").toString();

        System.out.println("Name: " + token.getPrincipal().getAttributes());

        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
                    User user = new User();
                    user.setName(token.getPrincipal().getAttributes().get("name").toString());
                    user.setAddress("Chưa cập nhật");
                    user.setEmail(email);
                    user.setPassword(passwordEncoder.encode("1"));
                    user.setRoles(Arrays.asList(roleRepository.findById(2).orElse(null)));
                    userRepository.save(user);
                    return user;
                });

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
    }
}