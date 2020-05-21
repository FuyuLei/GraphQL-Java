package fuyu.data;

import java.util.List;

import fuyu.model.User;

import static java.util.Arrays.asList;
import java.util.ArrayList;

public class UserData {
	static User user1 = new User("1", "Fuyu", "23", asList("2", "3"), asList("1", "3"));

	static User user2 = new User("2", "Kevin", "40", asList("1"), asList("2"));

	static User user3 = new User("3", "Mary", "18", asList("1"), null);

	public static List<User> userData = new ArrayList<>();

	static {
		userData.add(user1);
		userData.add(user2);
		userData.add(user3);
	}
}
