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

	public DataFetcher<?> getFirstUser() {
		return dataFetchingEnvironment -> {
			return UserData.userData.get(0);
		};
	}

	public DataFetcher<?> getUserById() {
		return dataFetchingEnvironment -> {
			// 拿Query時指定的id
			String userId = dataFetchingEnvironment.getArgument("id");
			// 用id去做篩選
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
	        // 取得source資料
			User source = dataFetchingEnvironment.getSource();
	        // 使用source資料裡的friends裡的資料去做篩選
			List<String> friendIds = source.getFriendIds();
			return UserData.userData.stream().filter(user -> friendIds.contains(user.getId())).toArray();
		};
	}

	public DataFetcher<?> getPostByPostId() {
		return dataFetchingEnvironment -> {
			User source = dataFetchingEnvironment.getSource();
			List<String> posts = source.getPosts();
			return PostData.postData.stream().filter(post -> posts.contains(post.getId())).toArray();
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