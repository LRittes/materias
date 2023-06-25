package domain.usecase.registerUser;

import domain.entity.User;
import domain.repository.RegisterUserRep;

public class RegisterUserImp implements RegisterUser {
    private RegisterUserRep _repository;

    public RegisterUserImp(RegisterUserRep repository) {
        this._repository = repository;
    }

    @Override
    public void registerUser(User user) throws Exception {
        _repository.registerUser(user);
    }

}
