package data.repository;

import data.dataSource.interfaceDB.UserDB;
import domain.entity.Email;
import domain.repository.AnswerEmailRep;
import domain.repository.SendEmailRep;

public class AnswerEmailRepImp implements AnswerEmailRep {
    private UserDB _db;

    public AnswerEmailRepImp(UserDB db) {
        this._db = db;
    }

    @Override
    public void answerEmail(Email emailResponse) throws Exception {
        SendEmailRep usecaseSE = new SendEmailRepImp(_db);

        try {
            usecaseSE.sendEmail(emailResponse);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
