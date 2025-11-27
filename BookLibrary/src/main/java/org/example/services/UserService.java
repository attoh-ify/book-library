package org.example.services;

import org.example.dao.UserDAO;
import org.example.dtos.user.CreateUserRequest;
import org.example.exceptions.BadRequestException;
import org.example.models.User;
import org.example.utils.PasswordUtils;

import javax.inject.Inject;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserService {
    @Inject
    private UserDAO userDAO;

    public User createUser(CreateUserRequest req) {
        String email = req.getEmail();
        boolean emailExists = userDAO.exists(req.getEmail());

        if (emailExists) {
            throw new BadRequestException("A user with this email already exists");
        }

        User user = new User(
                req.getName(),
                req.getEmail(),
                PasswordUtils.hashPassword(req.getPassword()),
                req.getRole()
        );
        userDAO.create(user);
        return user;
    }
}