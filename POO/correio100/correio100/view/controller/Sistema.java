package view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

import data.dataSource.interfaceDB.UserDB;
import data.dataSource.local.DB;
import data.dataSource.local.UserDBImp;
import data.repository.AnswerEmailRepImp;
import data.repository.DeleteEmailRepImp;
import data.repository.FindUserRepImp;
import data.repository.LoginUserRepImp;
import data.repository.RegisterUserRepImp;
import data.repository.SendEmailRepImp;
import domain.entity.Email;
import domain.entity.User;
import domain.repository.AnswerEmailRep;
import domain.repository.DeleteEmailRep;
import domain.repository.FindUserRep;
import domain.repository.LoginUserRep;
import domain.repository.RegisterUserRep;
import domain.repository.SendEmailRep;
import domain.usecase.answerEmail.AnswerEmail;
import domain.usecase.answerEmail.AnswerEmailImp;
import domain.usecase.deleteEmail.DeleteEmail;
import domain.usecase.deleteEmail.DeleteEmailImp;
import domain.usecase.findUser.FindUser;
import domain.usecase.findUser.FindUserImp;
import domain.usecase.loginUser.LoginUser;
import domain.usecase.loginUser.LoginUserImp;
import domain.usecase.registerUser.RegisterUser;
import domain.usecase.registerUser.RegisterUserImp;
import domain.usecase.sendEmail.SendEmail;
import domain.usecase.sendEmail.SendEmailImp;

public class Sistema {
    private UserDB db = new UserDBImp();

    private AnswerEmailRep answerEmailRep = new AnswerEmailRepImp(db);
    private AnswerEmail answerEmail = new AnswerEmailImp(answerEmailRep);

    private DeleteEmailRep deleteEmailRep = new DeleteEmailRepImp(db);
    private DeleteEmail deleteEmail = new DeleteEmailImp(deleteEmailRep);

    private LoginUserRep loginUserRep = new LoginUserRepImp(db);
    private LoginUser loginUser = new LoginUserImp(loginUserRep);

    private RegisterUserRep registerUserRep = new RegisterUserRepImp(db);
    private RegisterUser registerUser = new RegisterUserImp(registerUserRep);

    private SendEmailRep sendEmailRep = new SendEmailRepImp(db);
    private SendEmail sendEmail = new SendEmailImp(sendEmailRep);

    private FindUserRep findUserRep = new FindUserRepImp(db);
    private FindUser findUser = new FindUserImp(findUserRep);

    private User user;

    public User getUser() throws Exception {
        try {

            return findUser.findUser(user.getEmail());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void login(String email, String password) throws Exception {
        try {
            user = loginUser.login(email, password);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void logout() {
        user = null;
    }

    public User findUserController(String email) throws Exception {
        try {
            return findUser.findUser(email);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void register(String name, String password, String email) throws Exception {
        List<Email> list = new ArrayList<>();

        User user = new User(name, password, email, list);

        try {

            registerUser.registerUser(user);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<Email> emailsReceived() {
        return user.getEmails();
    }

    public void enviarEmail(String receiverEmail, String message, String subject) {
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        Email email = new Email(uuid.toString(), user.getEmail(), receiverEmail, message, subject, date);
        try {
            sendEmail.sendEmail(email);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void excluirEmail(String id) throws Exception {
        try {
            deleteEmail.deleteEmail(id, user);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void responderEmail(String receiverEmail, String message, String subject) {
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        Email email = new Email(uuid.toString(), user.getEmail(), receiverEmail, message, subject, date);
        try {
            answerEmail.answerEmail(email);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}