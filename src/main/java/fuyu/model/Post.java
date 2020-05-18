package fuyu.model;

import java.util.List;

public class Post {
	public String id;
	public String author;
	public String title;
	public String content;
	public List<String> likeGivers;
	
	public Post(String id, String author, String title, String content, List<String> likeGivers) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.likeGivers = likeGivers;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
		
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getLikeGivers() {
		return likeGivers;
	}
	
	public void setLikeGivers(List<String> likeGivers) {
		this.likeGivers = likeGivers;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (author != null) {
			builder.append("author=");
			builder.append(author);
			builder.append(", ");
		}
		if (title != null) {
			builder.append("title=");
			builder.append(title);
			builder.append(", ");
		}
		if (content != null) {
			builder.append("content=");
			builder.append(content);
			builder.append(", ");
		}
		if (likeGivers != null) {
			builder.append("likeGivers=");
			builder.append(likeGivers);
		}
		builder.append("]");
		return builder.toString();
	}	
}
