package domain.repository;

import domain.entity.User;

public interface LoginUserRep {
    public User login(String email, String password) throws Exception;
}
