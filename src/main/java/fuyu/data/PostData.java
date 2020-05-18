package fuyu.data;

import static java.util.Arrays.asList;
import java.util.ArrayList;
import java.util.List;

import fuyu.model.Post;

public class PostData {
	static Post post1 = new Post("1", "1", "Hello World!", "This is my first post.", asList("2"));

	static Post post2 = new Post("2", "2", "Good Night", "Have a Nice Dream =)", asList("1"));

	static Post post3 = new Post("3", "1", "Happy", "Happy :D", asList("2", "3"));

	public static List<Post> postData = new ArrayList<>();

	static {
		postData.add(post1);
		postData.add(post2);
		postData.add(post3);
	}
}
