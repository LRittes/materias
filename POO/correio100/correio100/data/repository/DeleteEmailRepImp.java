package data.repository;

import java.util.ArrayList;
import java.util.List;

import data.DTO.UserDTO;
import data.dataSource.interfaceDB.UserDB;
import domain.entity.Email;
import domain.entity.User;
import domain.repository.DeleteEmailRep;

public class DeleteEmailRepImp implements DeleteEmailRep {
    private UserDB _db;

    public DeleteEmailRepImp(UserDB db) {
        this._db = db;
    }

    public void deleteEmail(String idEmail, User user) throws Exception {
        User userAux;
        try {
            userAux = UserDTO.fromJson(_db.getUserByEmail(user.getEmail()));
        } catch (Exception e) {
            throw new Exception(e);
        }

        List<Email> emailsAux = new ArrayList<>();

        for (Email email : userAux.getEmails()) {
            if (!email.getId().equals(idEmail)) {
                emailsAux.add(email);
            }
        }

        userAux.setEmails(emailsAux);
        _db.updateUser(UserDTO.toJson(userAux));
    };
}
