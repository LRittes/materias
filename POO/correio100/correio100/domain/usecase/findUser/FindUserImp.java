package domain.usecase.findUser;

import domain.entity.User;
import domain.repository.FindUserRep;

public class FindUserImp implements FindUser {
    private FindUserRep _repository;

    public FindUserImp(FindUserRep repository) {
        _repository = repository;
    }

    @Override
    public User findUser(String email) throws Exception {
        try {
            return _repository.findUser(email);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
