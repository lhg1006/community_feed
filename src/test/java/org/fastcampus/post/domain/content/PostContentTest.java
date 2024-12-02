package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class PostContentTest {

  @Test
  void givenContentLengthIsOk_whenCreated_thenReturnTextContext() {
    //given
    String text = "this is a test";

    //when
    PostContent postContent = new PostContent(text);

    //then
    assertEquals(text,postContent.contentText);
  }

  @Test
  void givenContentLengthIsOver_whenCreated_thenThrowError() {
    //given
    String content = "a".repeat(501);

    //when, then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }


  @ParameterizedTest
  @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슳"})
  void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord) {
    //given
    String content = koreanWord.repeat(501);

    //when, then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @Test
  void givenContentLengthIsUnder_whenCreated_thenThrowError() {
    //given
    String content = "a".repeat(4);

    //when, then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void givenContentIsEmpty_whenCreated_thenThrowError(String value) {
    //when, then
    assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
  }

}
