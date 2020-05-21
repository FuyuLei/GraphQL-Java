package fuyu.model;

import java.util.List;

public class User {
	public String id;
	public String name;
	public String age;
	public List<String> friendIds;
	public List<String> posts;

	public User(String id, String name, String age, List<String> friendIds, List<String> posts) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.friendIds = friendIds;
		this.posts = posts;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public List<String> getFriendIds() {
		return friendIds;
	}

	public void setFriendIds(List<String> friendIds) {
		this.friendIds = friendIds;
	}

	public List<String> getPosts() {
		return posts;
	}

	public void setPosts(List<String> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (age != null) {
			builder.append("age=");
			builder.append(age);
			builder.append(", ");
		}
		if (friendIds != null) {
			builder.append("friendIds=");
			builder.append(friendIds);
			builder.append(", ");
		}
		if (posts != null) {
			builder.append("posts=");
			builder.append(posts);
		}
		builder.append("]");
		return builder.toString();
	}
}
