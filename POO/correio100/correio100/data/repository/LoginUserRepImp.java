package data.repository;

import data.DTO.UserDTO;
import data.dataSource.interfaceDB.UserDB;
import domain.entity.User;
import domain.repository.LoginUserRep;

public class LoginUserRepImp implements LoginUserRep {
    private UserDB _db;

    public LoginUserRepImp(UserDB db) {
        this._db = db;
    }

    public User login(String email, String password) throws Exception {
        try {
            return UserDTO.fromJson(_db.getUser(email, password));
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    };
}
