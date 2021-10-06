package com.example.safariwebstore008.services.servicesImpl;

import com.example.safariwebstore008.dto.MyUserDetails;
import com.example.safariwebstore008.exceptions.AccountNotEnabledException;
import com.example.safariwebstore008.models.UserModel;
import com.example.safariwebstore008.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService  implements  UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
          Optional<UserModel> userModel     = userRepository.findUserModelByEmail(userEmail);
          UserModel user = userModel.get();
//        Customer customer= customerRepository.findCustomerByEmail(userEmail);
        if(user!=null){
            if(user.getIsEnabled()){
            return new MyUserDetails(user.getEmail(),user.getPassword(), user.getIsEnabled(), new ArrayList<>());
            }
            throw new AccountNotEnabledException("Account is disabled");
        }
//        else if(customer!=null){
//            if(customer.getIsEnabled()){
//            return new MyUserDetails(customer.getEmail(), customer.getPassword(), customer.getIsEnabled(), new ArrayList<>());
//        }
//            throw new AccountNotEnabledException("Account is disabled");
//        }
        else{
            throw new UsernameNotFoundException("User not Found");
        }
    }

}
