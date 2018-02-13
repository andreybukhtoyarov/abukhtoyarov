package ru.job4j.listtomap;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserConvertTest {
    private List<User> users = new ArrayList<>();
    private Map<Integer, User> expected = new HashMap<>();

    @Before
    public void setList() {
        this.users.addAll(Arrays.asList(
                new User(0, "Andrey", "Moscow"),
                new User(1, "Ivan", "Mahachkala"),
                new User(2, "Nastya", "Piter")
                )
        );
    }

    @Before
    public void setUsersMap() {
        for (User user : users) {
            expected.put(user.getId(), user);
        }
    }

    @Test
    public void whenProcessThenNewMap() {
        UserConvert convert = new UserConvert();
        assertThat(convert.process(this.users), is(expected));
    }
}
