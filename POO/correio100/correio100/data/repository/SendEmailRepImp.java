package data.repository;

import data.DTO.UserDTO;
import data.dataSource.interfaceDB.UserDB;
import domain.entity.Email;
import domain.entity.User;
import domain.repository.SendEmailRep;

public class SendEmailRepImp implements SendEmailRep {
    private UserDB _db;

    public SendEmailRepImp(UserDB db) {
        this._db = db;
    }

    @Override
    public void sendEmail(Email email) throws Exception {
        User userAux = UserDTO.fromJson(_db.getUserByEmail(email.getReceiver()));
        userAux.addEmail(email);
        _db.updateUser(UserDTO.toJson(userAux));

    }
}
