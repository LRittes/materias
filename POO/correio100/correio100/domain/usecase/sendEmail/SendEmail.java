package domain.usecase.sendEmail;

import domain.entity.Email;

public interface SendEmail {
    public void sendEmail(Email email) throws Exception;
}
