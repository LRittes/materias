package domain.repository;

import domain.entity.User;

public interface DeleteEmailRep {
    public void deleteEmail(String idEmail, User user) throws Exception;
}
