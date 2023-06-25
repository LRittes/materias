package data.dataSource.service;

import java.util.Map;

public interface Service {
    public Map<String, Object> get(String path);

    public void put(Object obj);

    public void update(Object obj);

    public void delete(Object obj);

}
