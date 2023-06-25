package data.dataSource.local;

import java.util.HashMap;
import java.util.Map;

import data.dataSource.interfaceDB.UserDB;
import data.dataSource.service.PSQLServiceImp;
import data.dataSource.service.Service;

public class UserDBImp implements UserDB {

    private Service _service;

    DB db = DB.I();

    public UserDBImp() {
        this._service = new PSQLServiceImp();
    }

    @Override
    public Map<String, Object> getUser(String email, String password) {
        Map<String, Object> user = new HashMap<>();
        for (Map<String, Object> u : db.getDB()) {
            if (u.get("email").equals(email) && u.get("userpassword").equals(password)) {
                user = u;
            }
        }
        return user;
    }

    @Override
    public void updateUser(Map<String, Object> user) {
        db.updateDB(user);
    }

    @Override
    public void saveUser(Map<String, Object> user) {
        db.addDB(user);
    }

    @Override
    public Map<String, Object> getUserByEmail(String email) {
        Map<String, Object> user = new HashMap<>();
        for (Map<String, Object> u : db.getDB()) {
            if (u.get("email").equals(email)) {
                user = u;
            }
        }

        return user;
    }

}
