package data.repository;

import data.DTO.UserDTO;
import data.dataSource.interfaceDB.UserDB;
import domain.entity.User;

public class RegisterUserRepImp implements domain.repository.RegisterUserRep {
    private UserDB _db;

    public RegisterUserRepImp(UserDB db) {
        this._db = db;
    }

    public void registerUser(User user) throws Exception {
        _db.saveUser(UserDTO.toJson(user));
    };
}
