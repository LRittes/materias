package domain.usecase.sendEmail;

import domain.entity.Email;
import domain.repository.SendEmailRep;

public class SendEmailImp implements SendEmail {
    private SendEmailRep _repository;

    public SendEmailImp(SendEmailRep _repository) {
        this._repository = _repository;
    }

    @Override
    public void sendEmail(Email email) throws Exception {
        _repository.sendEmail(email);
    }

}
