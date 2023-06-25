package domain.repository;

import domain.entity.Email;

public interface SendEmailRep {
    public void sendEmail(Email email) throws Exception;
}
