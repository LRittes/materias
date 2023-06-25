package domain.usecase.findUser;

import domain.entity.User;

public interface FindUser {
    public User findUser(String email) throws Exception;
}
