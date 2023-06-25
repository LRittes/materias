package domain.repository;

import domain.entity.User;

public interface RegisterUserRep {
    public void registerUser(User user) throws Exception;
}
