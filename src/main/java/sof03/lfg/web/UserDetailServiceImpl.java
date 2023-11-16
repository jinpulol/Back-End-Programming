package sof03.lfg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sof03.lfg.domain.User;
import sof03.lfg.domain.UserRepository;

// This class is used by spring security to authenticate and authorize user
@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User currentUser = userRepository.findByUserName(userName);
        UserDetails user = new org.springframework.security.core.userdetails.User(userName,
                    currentUser.getPasswordHash(),
                    AuthorityUtils.createAuthorityList(currentUser.getRole()));

      return user;
    }
    
}
