package org.fastcampus.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

public class PostTest {
  private final UserInfo info = new UserInfo("name", "url");
  private final User user = new User(1L, info);
  private final User otherUser = new User(2L, info);

  private final Post post = new Post(1L, user, new PostContent("content"));

  @Test
  void givenPostCreated_whenLike_thenLikeCountShouldBe1() {
    //when
    post.like(otherUser);

    //then
    assertEquals(1, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenLikeByOtherUser_thenThrowException() {
    //when, then
    assertThrows(IllegalArgumentException.class, () -> post.like(user));
  }

  @Test
  void givenPostCreated_whenLikeByUser_thenLikeCountShouldBe0() {
    //given
    post.like(otherUser);

    //when
    post.unlike();

    //then
    assertEquals(0, post.getLikeCount());
  }


}
