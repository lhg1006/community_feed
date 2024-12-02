package org.fastcampus.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PositiveIntegerCounterTest {

  @Test
  void geventCreated_whenIncrease_thenCountIsOne() {
    //given
    PositiveIntegerCounter counter = new PositiveIntegerCounter();

    //when
    counter.increase();

    //then
    assertEquals(1, counter.getCount());
  }

  @Test
  void givenCreated_whenDecrease_thenCountIsZero() {
    //given
    PositiveIntegerCounter counter = new PositiveIntegerCounter();

    //when
    counter.decrease();

    assertEquals(0, counter.getCount());
  }

  @Test
  void givenCreated_whenDecrease_thenCounterIsZero() {
    //given
    PositiveIntegerCounter counter = new PositiveIntegerCounter();

    //when
    counter.decrease();

    //then
    assertEquals(0, counter.getCount());
  }
}
