package domain.usecase.answerEmail;

import domain.entity.Email;
import domain.repository.AnswerEmailRep;

public class AnswerEmailImp implements AnswerEmail {
    private AnswerEmailRep _repository;

    public AnswerEmailImp(AnswerEmailRep repository) {
        _repository = repository;
    }

    @Override
    public void answerEmail(Email email) throws Exception {
        _repository.answerEmail(email);
    }

}
