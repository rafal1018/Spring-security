package pl.strefakursow.Spring.Security.Course;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SecretUserRepository {

    private ConcurrentMap<String, User> users;

    public SecretUserRepository(){
        users = new ConcurrentHashMap<>();
        users.put("SECRET_123",  new User("user123"));
        users.put("SECRET_456",  new User("user456"));
    }

    public Optional<User> loadBySecret(String secret) {
        return Optional.ofNullable(users.get(secret));
    }
}
