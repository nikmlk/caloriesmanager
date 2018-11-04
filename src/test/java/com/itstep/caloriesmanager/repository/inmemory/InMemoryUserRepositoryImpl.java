package com.itstep.caloriesmanager.repository.inmemory;

import com.itstep.caloriesmanager.UserTestData;
import com.itstep.caloriesmanager.model.User;
import com.itstep.caloriesmanager.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.itstep.caloriesmanager.UserTestData.ADMIN;
import static com.itstep.caloriesmanager.UserTestData.USER;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(UserTestData.USER_ID, USER);
        repository.put(UserTestData.ADMIN_ID, ADMIN);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return repository.values().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
