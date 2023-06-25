package domain.usecase.deleteEmail;

import domain.entity.User;

public interface DeleteEmail {
    public void deleteEmail(String id, User user) throws Exception;
}
