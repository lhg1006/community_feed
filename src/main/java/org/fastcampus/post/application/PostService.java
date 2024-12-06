package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.Content;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {
  private final UserService userService;
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;

  public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
    this.userService = userService;
    this.postRepository = postRepository;
    this.likeRepository = likeRepository;
  }

  public Post getPost(Long id){
    return postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Post not found"));
  }

  public Post createPost(CreatePostRequestDto dto) {
    User author = userService.getUser(dto.userId());
    Content content = new PostContent(dto.content());
    Post post = new Post(null, author, content, dto.state());
    return postRepository.save(post);

  }

  public Post updatePost(Long id, UpdatePostRequestDto dto) {
    Post post = getPost(id);
    User user = userService.getUser(dto.userId());

    post.updatePost(user, dto.content(), dto.state());

    return postRepository.save(post);
  }

  public void likePost(LikeRequestDto dto) {
    Post post = getPost(dto.targetId());
    User user = userService.getUser(dto.userId());

    if(likeRepository.checkLike(post, user)) {
      return;
    }

    post.like(user);
    likeRepository.like(post, user);
  }

  public void unlikePost(LikeRequestDto dto) {
    Post post = getPost(dto.targetId());
    User user = userService.getUser(dto.userId());

    if(likeRepository.checkLike(post, user)) {
      post.unlike();
      likeRepository.unlike(post, user);
    }
  }
}
