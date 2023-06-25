package domain.usecase.registerUser;

import domain.entity.User;

public interface RegisterUser {
    public void registerUser(User user) throws Exception;
}
