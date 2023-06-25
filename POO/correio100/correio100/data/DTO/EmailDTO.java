package data.DTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import domain.entity.Email;
import utils.FunctionsUtil;

public class EmailDTO {

    public EmailDTO() {
    }

    public static Email fromJson(Map<String, Object> email) {
        LocalDate date = LocalDate.parse(email.get("date").toString());
        String message = FunctionsUtil.decriptar(date.getDayOfMonth(), email.get("usermessage").toString());

        Email emailRet = new Email(email.get("id").toString(), email.get("author").toString(),
                email.get("receiver").toString(),
                message, email.get("subject").toString(), date);

        return emailRet;
    }

    public static Map<String, Object> toJson(Email email) {
        Map<String, Object> emailMap = new HashMap<>();

        emailMap.put("author", email.getAuthor());
        emailMap.put("receiver", email.getReceiver());
        emailMap.put("usermessage", FunctionsUtil.encriptar(email.getDate().getDayOfMonth(), email.getMessage()));
        emailMap.put("subject", email.getSubject());
        emailMap.put("id", email.getId());
        emailMap.put("date", email.getDate().toString());

        return emailMap;
    }
}
