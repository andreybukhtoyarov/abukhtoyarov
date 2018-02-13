package ru.job4j.listtomap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = null;
        if (list != null) {
            result = new HashMap<>();
            Iterator<User> iter = list.iterator();
            while (iter.hasNext()) {
                User user = iter.next();
                result.put(user.getId(), user);
            }
        }
        return result;
    }
}
