package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.UserProfile;
import br.com.haircutter.core.model.repository.UserProfileRepository;
import br.com.haircutter.core.model.repository.UserRepository;
import br.com.haircutter.core.service.UserService;
import br.com.haircutter.core.utils.HaircutterMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private HaircutterMailSender haircutterMailSender;

    @Override
    public User create(User user, UserRoleEnum role) {

        if (user.getUsername() != null) {
            throw new CustomInvalidException("Should be null", "username");
        }

        if (user.getEmail() == null) {
            throw new CustomInvalidException("Should not be null", "email");
        }

        user.setRole(role);

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        user.setLastModifiedDate(now);
        user.setCreationTime(now);
        user.setEnabled(true);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setUsername(user.getEmail());
        user.getProfile().getAddress().setCountry("Brasil");
        user.getProfile().getAddress().setNeighborhood("-");
        user.getProfile().getAddress().setStreet("-");
        user.getProfile().setLastModifiedDate(now);
        user.getProfile().setCreationTime(now);

        User createdUser = userRepository.save(user);

        createdUser.setProfile(user.getProfile());
        createdUser.getProfile().setUsername(createdUser.getUsername());

        UserProfile userProfile = userProfileRepository.save(createdUser.getProfile());

        createdUser.setProfile(userProfile);

        sendCreationEmail(user);

        return createdUser;
    }

    @Override
    public void edit(User user) {

        if (user.getUsername() == null) {
            throw new CustomInvalidException("Should not be null", "username");
        }

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        user.setLastModifiedDate(now);

        User editedUser = userRepository.save(user);

        editedUser.setProfile(user.getProfile());
        editedUser.getProfile().setUsername(editedUser.getUsername());

        UserProfile userProfile = userProfileRepository.save(editedUser.getProfile());

        editedUser.setProfile(userProfile);

        userRepository.save(user);
    }

    @Override
    public void delete(String username) {

        User user = userRepository.findOneByUsername(username);

        user.setEnabled(false);

        userRepository.save(user);
    }

    @Override
    public User get(String username) {

        User user = userRepository.findOneByUsername(username);

        UserProfile userProfile = userProfileRepository.findOneByUsername(username);

        user.setProfile(userProfile);

        return user;
    }

    private void sendCreationEmail(User user) {

        String subject = "Bem-vindo ao Haircutter!";

        String text = "Olá " + user.getName() + ",\n\n"
                + "Seja bem vindo ao Haircutter!\n\nEquipe Haircutter";

        haircutterMailSender.sendEmail(user.getName(), subject, text);
    }
}
