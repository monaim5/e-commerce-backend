package experiences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SingletonList {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("monaim", 24));
        users.add(new User("rachid", 30));
        users.add(new User("mohamed", 12));

        Collection<User> users2 = Collections.singletonList(new User("mohamed", 12));

        for(User u: users) {
            System.out.println(u);
        }

        for(int i=0; i<users2.size(); i++) {
            System.out.println(users2);
        }
    }
}

class User {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "name : " + this.name + "\tage : " + this.age;
    }
}