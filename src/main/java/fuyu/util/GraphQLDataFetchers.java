package fuyu.util;

import java.util.List;

import org.springframework.stereotype.Component;

import fuyu.data.PostData;
import fuyu.data.UserData;
import fuyu.model.Post;
import fuyu.model.User;
import graphql.schema.DataFetcher;

@Component
public class GraphQLDataFetchers {
	
//	private static List<Map<String, String>> users = Arrays.asList(
//			ImmutableMap.of("id", "1", "name", "Fong", "age", "23", "friendIds", "2"),
//			ImmutableMap.of("id", "2", "name", "Kevin", "age", "40", "friendIds", "1"),
//			ImmutableMap.of("id", "3", "name", "Mary", "age", "18", "friendIds", "1"));
//
//	private static List<Map<String, String>> posts = Arrays.asList(
//			ImmutableMap.of("id", "1", "authorId", "1", "title", "Hello World!", "content", "This is my first post.",
//					"likeGiverIds", "2"),
//			ImmutableMap.of("id", "2", "authorId", "2", "title", "Good Night", "content", "Have a Nice Dream =)",
//					"likeGiverIds", "2, 3"),
//			ImmutableMap.of("id", "3", "authorId", "1", "title", "I Love U", "content", "Here's my second post!",
//					"likeGiverIds", ""));

	public DataFetcher<?> getFirstUser() {
		return dataFetchingEnvironment -> {
			return UserData.userData.get(0);
		};
	}

	public DataFetcher<?> getUserById() {
		return dataFetchingEnvironment -> {
			String userId = dataFetchingEnvironment.getArgument("id");
			return UserData.userData.stream().filter(user -> user.getId().equals(userId)).findFirst();
		};
	}

	public DataFetcher<?> getAllUsers() {
		return dataFetchingEnvironment -> {
			return UserData.userData.toArray();
		};
	}
	
	public DataFetcher<?> getPostById() {
		return dataFetchingEnvironment -> {
			String postId = dataFetchingEnvironment.getArgument("id");
			return PostData.postData.stream().filter(post -> post.getId().equals(postId)).findFirst();
		};
	}

	public DataFetcher<?> getAllPosts() {
		return dataFetchingEnvironment -> {
			return PostData.postData.toArray();
		};
	}
	
	public DataFetcher<?> getUserByFriends() {
		return dataFetchingEnvironment -> {
			User source = dataFetchingEnvironment.getSource();
			List<String> friendIds = source.getFriendIds();
			return UserData.userData.stream().filter(user -> friendIds.contains(user.getId())).toArray();
		};
	}

	public DataFetcher<?> getUserByPostAuthor() {
		return dataFetchingEnvironment -> {
			Post source = dataFetchingEnvironment.getSource();
			String authorId = source.getAuthor();
			return UserData.userData.stream().filter(user -> user.getId().equals(authorId)).findFirst();
		};
	}
	
	public DataFetcher<?> getUserByPostLikeGivers() {
		return dataFetchingEnvironment -> {
			Post source = dataFetchingEnvironment.getSource();
			List<String> likeGivers = source.getLikeGivers();
			return UserData.userData.stream().filter(user -> likeGivers.contains(user.getId())).toArray();
		};
	}

}