package application.services;

import application.models.Post;
import application.models.PostRepository;
import application.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    public List<Post> getPostByUsername(String username){
        return postRepository.findPostsByUsername(username);
    }

    public void createPost(String  ownerUsername, String text) {
        Post newPost = new Post();
        newPost.setOwnerUsername(ownerUsername);
        newPost.setText(text);
        postRepository.save(newPost);
    }
    public List<Post> allPost() {
        return postRepository.allPosts();
    }
}