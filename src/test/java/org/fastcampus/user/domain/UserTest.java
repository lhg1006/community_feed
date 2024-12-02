package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

  private final UserInfo userInfo = new UserInfo("test", "");
  private User user1;
  private User user2;

  @BeforeEach
  void init() {
    user1 = new User(1L, userInfo);
    user2 = new User(2L, userInfo);
  }

  @Test
  void givenTwoUser_whenEqual_thenReturnFalse() {
    //when
    boolean value = user1.equals(user2);

    //then
    assertFalse(value);
  }

  @Test
  void givenTwoSameIdUser_whenEqual_thenReturnTure(){
    //given
    User sameUser = new User(1L, userInfo);

    //when
    boolean isSame = user1.equals(sameUser);

    //then
    assertTrue(isSame);
  }

  @Test
  void givenTwoUser_whenHashCode_thenNotEqual() {
    //when
    int hashCode1 = user1.hashCode();
    int hashCode2 = user2.hashCode();

    //then
    assertNotEquals(hashCode1,hashCode2);
  }

  @Test
  void givenTwoUser_whenHashCode_thenEqual() {
    //give
    User sameUser = new User(1L, userInfo);
    //when
    int hashCode1 = user1.hashCode();
    int sameUserHashCode = sameUser.hashCode();

    //then
    assertEquals(hashCode1, sameUserHashCode);
  }

  @Test
  void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
    //when
    user1.follow(user2);

    //then
    assertEquals(1, user1.followingCount());
    assertEquals(0, user1.followerCount());
    assertEquals(0, user2.followingCount());
    assertEquals(1, user2.followerCount());
  }

  @Test
  void givenTwoUser_whenUnfollow_thenDecreaseUserCount() {

    //given
    user1.follow(user2);

    //when
    user1.unfollow(user2);

    //then
    assertEquals(0, user1.followingCount());
    assertEquals(0, user1.followerCount());
    assertEquals(0, user2.followingCount());
    assertEquals(0, user2.followerCount());
  }


  @Test
  void givenTwoUser_whenUnFollow_thenNotDecreaseUserCount() {
    //when
    user1.unfollow(user2);

    //then
    assertEquals(0, user1.followingCount());
    assertEquals(0, user1.followerCount());
    assertEquals(0, user2.followingCount());
    assertEquals(0, user2.followerCount());
  }

}