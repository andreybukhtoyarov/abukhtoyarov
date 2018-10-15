package ru.job4j.listtomap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        Map<Integer, User> result = new HashMap<>();
        if (list != null) {
            result = list.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        }
        return (HashMap<Integer, User>) result;
    }
}
