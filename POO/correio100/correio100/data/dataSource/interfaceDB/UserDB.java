package data.dataSource.interfaceDB;

import java.util.Map;

public interface UserDB {
    public Map<String, Object> getUser(String id, String password);

    public Map<String, Object> getUserByEmail(String email);

    public void updateUser(Map<String, Object> user);

    public void saveUser(Map<String, Object> user);

}
