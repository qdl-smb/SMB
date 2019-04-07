package ch.hearc.sandbox.service;

import ch.hearc.sandbox.model.CustomUser;
import ch.hearc.sandbox.model.PasswordResetToken;
import ch.hearc.sandbox.model.Role;
import ch.hearc.sandbox.repository.CustomUserRepository;
import ch.hearc.sandbox.repository.PasswordResetTokenRepository;
import ch.hearc.sandbox.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


@Service
public class CustomUserServiceImpl implements CustomUserService {
    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(CustomUser customUser) {
        customUser.setPassword(bCryptPasswordEncoder.encode(customUser.getPassword()));
        customUser.setEmail(customUser.getEmail());
        customUser.setRoles(new HashSet<>((Collection<? extends Role>) roleRepository.findAllById(Arrays.asList(2l))));
        customUserRepository.save(customUser);
    }

    @Override
    public CustomUser findByCustomusername(String username) {
        return customUserRepository.findByUsername(username);
    }

    @Override
    public CustomUser findByCustomemail(String email) {
        return customUserRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(CustomUser customUser, String token){
        PasswordResetToken myToken = new PasswordResetToken(token,customUser);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public void changeUserPassword(CustomUser user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        customUserRepository.save(user);
    }

    @Override
    public CustomUser findByCustomId(long id){
        return customUserRepository.findById(id).get();
    }
}
