package net.javaguides.todomanagement.security;

import lombok.AllArgsConstructor;
import net.javaguides.todomanagement.entity.User;
import net.javaguides.todomanagement.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String UsernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(UsernameOrEmail, UsernameOrEmail).orElseThrow(
                ()->new UsernameNotFoundException("User not exists by username or email")
        );

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(UsernameOrEmail,null,authorities);
    }
}
