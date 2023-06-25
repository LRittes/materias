package domain.usecase.loginUser;

import domain.entity.User;
import domain.repository.LoginUserRep;

public class LoginUserImp implements LoginUser {
    private LoginUserRep _repository;

    public LoginUserImp(LoginUserRep repository) {
        this._repository = repository;
    }

    @Override
    public User login(String email, String password) throws Exception {
        return _repository.login(email, password);
    }

}
