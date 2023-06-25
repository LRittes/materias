package data.repository;

import data.DTO.UserDTO;
import data.dataSource.interfaceDB.UserDB;
import domain.entity.User;
import domain.repository.FindUserRep;

public class FindUserRepImp implements FindUserRep {
    private UserDB _db;

    public FindUserRepImp(UserDB db) {
        this._db = db;
    }

    @Override
    public User findUser(String email) throws Exception {
        try {
            return UserDTO.fromJson(_db.getUserByEmail(email));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
