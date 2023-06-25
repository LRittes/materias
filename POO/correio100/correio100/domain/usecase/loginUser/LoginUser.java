package domain.usecase.loginUser;

import domain.entity.User;

public interface LoginUser {
    public User login(String email, String password) throws Exception;
}
