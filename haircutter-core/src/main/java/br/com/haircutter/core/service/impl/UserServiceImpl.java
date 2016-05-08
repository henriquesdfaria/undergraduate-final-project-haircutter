package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.model.UserProfile;
import br.com.haircutter.core.model.repository.UserProfileRespository;
import br.com.haircutter.core.model.repository.UserRespository;
import br.com.haircutter.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRespository userRespository;

    @Autowired
    UserProfileRespository userProfileRespository;

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

        User createdUser = userRespository.save(user);

        createdUser.setProfile(user.getProfile());
        createdUser.getProfile().setUsername(createdUser.getUsername());

        UserProfile userProfile = userProfileRespository.save(createdUser.getProfile());

        createdUser.setProfile(userProfile);

        return createdUser;
    }

    @Override
    public void edit(User user) {

        if (user.getUsername() == null) {
            throw new CustomInvalidException("Should not be null", "username");
        }

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        user.setLastModifiedDate(now);

        User editedUser = userRespository.save(user);

        editedUser.setProfile(user.getProfile());
        editedUser.getProfile().setUsername(editedUser.getUsername());

        UserProfile userProfile = userProfileRespository.save(editedUser.getProfile());

        editedUser.setProfile(userProfile);

        userRespository.save(user);
    }

    @Override
    public void delete(String username) {

        User user = userRespository.findOneByUsername(username);

        user.setEnabled(false);

        userRespository.save(user);
    }

    @Override
    public User get(String username) {
        return userRespository.findOneByUsername(username);
    }
}
