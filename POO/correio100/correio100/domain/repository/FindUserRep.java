package domain.repository;

import domain.entity.User;

public interface FindUserRep {
    public User findUser(String email) throws Exception;
}
