package gamesys.factory;

import gamesys.model.User;
import org.mockito.internal.util.collections.Sets;
import java.util.Set;

public class UserFactory {

    public static Set<User> getUsers() {
        return Sets.newSet(User.builder().id(1).username("a1").password("Aaa1").dob("1985-10-17T23:00:00.000Z").ssn("111-11-1111").build(),
                User.builder().id(2).username("a2").password("Aaa2").dob("1996-06-11T23:00:00.000Z").ssn("222-22-2222").build());
    }

    public static User someUser() {
        return User.builder().id(1).username("a1").password("Aaa1").dob("1985-10-17T23:00:00.000Z").ssn("111-11-1111").build();
    }

}
