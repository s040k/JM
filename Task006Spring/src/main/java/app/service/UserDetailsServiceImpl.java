package app.service;

import app.model.User;
import app.model.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {//Основная логика аутентификации
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getByLogin(s);
        //Set<GrantedAuthority> roles = new HashSet<>();
        //roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        System.out.println("Проверочки секьюра "+user.getLogin()+" "+user.getPassword()+" :"+user.getRole());
        //new org.springframework.security.core.userdetails.User(,)
        // new org.springframework.security.core.userdetails.User
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                roles
//        );
        System.out.println(user);
        return user;
    }
}

