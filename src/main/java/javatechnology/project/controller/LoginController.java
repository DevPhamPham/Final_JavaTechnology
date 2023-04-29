package javatechnology.project.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javatechnology.project.dto.UserDTO;
import javatechnology.project.global.GlobalData;
import javatechnology.project.model.Role;
import javatechnology.project.model.User;
import javatechnology.project.repository.RoleRepository;
import javatechnology.project.repository.UserRepository;
import javatechnology.project.service.RoleService;
import javatechnology.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }//page login

    @GetMapping("/forgotpassword")
    public String forgotPass(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "forgotpassword";
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        return "register";
    } //page register

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User userModel, HttpServletRequest request,Model model) throws ServletException{
        //chuyen password tu form dki thanh dang ma hoa
        String password = userModel.getPassword();
        userModel.setPassword(bCryptPasswordEncoder.encode(password));
        //set mac dinh role user
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.findRoleById(2).get());
        userModel.setRoles(roles);
//        System.out.println(userModel.getRoles());
//        System.out.println("=-------------debug"+userModel);
        try {
            User user = userService.saveUser(userModel);
            //login & chuyen den page home
//            request.login(user.getEmail(), password);
            return "redirect:/login";
        } catch(Exception e){
            model.addAttribute("errorRegister", "Đăng ký không thành công, email đã tồn tại");
            return "register";
        }
    }//after register success

}
