import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameOfLifeTestGRADING {

  @Test
  public void testConstructorAndGettersInAnEmptySociety() {
    GameOfLife society = new GameOfLife(5, 8);
    assertEquals(5, society.numberOfRows());
    assertEquals(8, society.numberOfColumns());
    for (int r = 0; r < society.numberOfRows(); r++) {
      for (int c = 0; c < society.numberOfColumns(); c++) {
        assertFalse(society.cellAt(r, c));
      }
    }
  }

  @Test
  public void testGrowCellAtAndCellAt() {
    GameOfLife society = new GameOfLife(5, 5);
    society.growCellAt(1, 1);
    society.growCellAt(2, 2);
    society.growCellAt(3, 3);
    assertTrue(society.cellAt(1, 1));
    assertTrue(society.cellAt(2, 2));
    assertTrue(society.cellAt(3, 3));
  }
  
  @Test
  public void testGrowCellAtAndCellAt2() {
    GameOfLife society = new GameOfLife(5, 5);
    society.growCellAt(1, 1);
    society.growCellAt(2, 2);
    society.growCellAt(3, 3);
    assertFalse(society.cellAt(0, 0));
    assertFalse(society.cellAt(0, 4));
    assertFalse(society.cellAt(4, 0));
    assertFalse(society.cellAt(4, 4));
  }


  @Test
  public void testNeighborsNoWrapping() {
    GameOfLife society = new GameOfLife(10, 16);
    society.growCellAt(3, 3);
    society.growCellAt(3, 4);
    society.growCellAt(3, 5);
    assertEquals(0, society.neighborCount(2, 1));
    assertEquals(1, society.neighborCount(2, 2));
    assertEquals(2, society.neighborCount(2, 3));
    assertEquals(3, society.neighborCount(2, 4));

  }

  @Test
  public void testEasyUpdate_4() {
    GameOfLife g = new GameOfLife(6, 6);
    g.growCellAt(2, 2);
    g.growCellAt(2, 3);
    g.growCellAt(2, 4);
    // ......
    // ......
    // ..OOO.
    // ......
    // ......
    // ......

    g.update();
    // ......
    // ...O..
    // ...O..
    // ...O..
    // ......
    // ......
    assertTrue(g.cellAt(1, 3));
    assertTrue(g.cellAt(2, 3));
    assertTrue(g.cellAt(3, 3));
    assertFalse(g.cellAt(2, 2));
    assertFalse(g.cellAt(2, 4));

    g.update();
    assertTrue(g.cellAt(2, 2));
    assertTrue(g.cellAt(2, 3));
    assertTrue(g.cellAt(2, 4));
    assertFalse(g.cellAt(1, 3));
    assertFalse(g.cellAt(3, 3));

    g.update();
    assertTrue(g.cellAt(1, 3));
    assertTrue(g.cellAt(2, 3));
    assertTrue(g.cellAt(3, 3));
    assertFalse(g.cellAt(2, 2));
    assertFalse(g.cellAt(2, 4));
  }

  @Test
  public void testUpdate_5() {
    GameOfLife society2 = new GameOfLife(12, 12);
    society2.growCellAt(3, 5);
    society2.growCellAt(4, 6);
    society2.growCellAt(4, 7);
    society2.growCellAt(5, 6);
    // ................
    // ................
    // ................
    // .....O..........
    // ......OO........
    // ......O.........
    // ................
    // ................

    society2.update();

    assertTrue(society2.cellAt(3, 6));
    assertTrue(society2.cellAt(4, 5));
    assertTrue(society2.cellAt(4, 6));
    assertTrue(society2.cellAt(4, 7));
    assertTrue(society2.cellAt(5, 6));
    assertTrue(society2.cellAt(5, 7));
  }

  @Test
  public void testneghborCountOnWraparound() {
    GameOfLife society = new GameOfLife(6, 5);
    society.growCellAt(0, 0);
    society.growCellAt(0, 2);
    society.growCellAt(0, 4);
    society.growCellAt(5, 2);
    society.growCellAt(5, 3);
    society.growCellAt(5, 4);
    assertEquals(2, society.neighborCount(0, 0));
    assertEquals(2, society.neighborCount(0, 2));
    assertEquals(3, society.neighborCount(0, 4));
    assertEquals(2, society.neighborCount(5, 2));
    assertEquals(4, society.neighborCount(5, 3));
    assertEquals(3, society.neighborCount(5, 4));
    System.out.println(society);
    society.update();
    System.out.println();
    System.out.println(society);
  }

  @Test
  public void testNeighborCountWrap_2() {
    GameOfLife g = new GameOfLife(5, 9);
    g.growCellAt(0, 5);
    g.growCellAt(0, 8);
    g.growCellAt(1, 0);
    g.growCellAt(2, 0);
    g.growCellAt(3, 0);
    g.growCellAt(2, 8);
    g.growCellAt(3, 8);
    g.growCellAt(4, 4);
    g.growCellAt(4, 6);
    assertEquals(2, g.neighborCount(0, 5));
    assertEquals(4, g.neighborCount(2, 8));
    assertEquals(4, g.neighborCount(2, 0));
    assertEquals(3, g.neighborCount(1, 0));
    assertEquals(3, g.neighborCount(4, 8));
    assertEquals(3, g.neighborCount(3, 8));
  }

  @Test
  public void testEasyWrapAround_3() {
    GameOfLife society = new GameOfLife(10, 16);
    society.growCellAt(6, 6);
    society.growCellAt(6, 7);
    society.growCellAt(7, 6);
    society.growCellAt(5, 5);
    society.growCellAt(9, 2);
    society.growCellAt(9, 3);
    society.growCellAt(9, 4);
    society.update();
    assertTrue(society.cellAt(8, 3));
    assertTrue(society.cellAt(9, 3));
    assertTrue(society.cellAt(0, 3));
  }

  @Test
  public void testHarderWrap_6() {
    GameOfLife g = new GameOfLife(8, 9);
    g.growCellAt(0, 0);
    g.growCellAt(0, 1);
    g.growCellAt(1, 0);
    g.growCellAt(1, 1);
    assertEquals(3, g.neighborCount(0, 0));

    g.growCellAt(0, 8);
    g.growCellAt(1, 8);
    g.growCellAt(7, 1);
    g.growCellAt(7, 0);
    g.growCellAt(7, 8);
    assertEquals(8, g.neighborCount(0, 0));
  }

  @Test
  public void testNeighborsWhenWrapping_7() {
    GameOfLife society = new GameOfLife(10, 16);
    society.growCellAt(6, 6);
    society.growCellAt(6, 7);
    society.growCellAt(7, 6);
    society.growCellAt(5, 5);

    society.growCellAt(3, 0);
    society.growCellAt(3, 15);
    assertEquals(1, society.neighborCount(3, 15));
    assertEquals(1, society.neighborCount(3, 0));
    society.growCellAt(4, 0);
    society.growCellAt(4, 15);
    assertEquals(3, society.neighborCount(3, 15));
    assertEquals(3, society.neighborCount(3, 0));
    assertEquals(3, society.neighborCount(4, 15));
    assertEquals(3, society.neighborCount(4, 0));
    society.update();
    assertEquals(3, society.neighborCount(3, 15));
    assertEquals(3, society.neighborCount(3, 0));
    assertEquals(3, society.neighborCount(4, 15));
    assertEquals(3, society.neighborCount(4, 0));

    society.growCellAt(5, 0);
    society.update();
    assertTrue(society.cellAt(5, 15));
    assertTrue(society.cellAt(5, 15));
    assertTrue(society.cellAt(4, 1));
    assertFalse(society.cellAt(4, 15));
  }

  @Test
  public void testWrapLowerLeftUpperRight_8() {
    GameOfLife society = new GameOfLife(10, 16);
    society.growCellAt(6, 6);
    society.growCellAt(6, 7);
    society.growCellAt(7, 6);
    society.growCellAt(5, 5);

    society.growCellAt(9, 1);
    society.growCellAt(8, 0);
    assertEquals(2, society.neighborCount(9, 0));
    society.growCellAt(0, 15);

    society.update();
    assertTrue(society.cellAt(9, 0));
    assertFalse(society.cellAt(9, 1));
    assertFalse(society.cellAt(8, 0));
    assertEquals(0, society.neighborCount(0, 9));
  }

  @Test
  public void testWrapUpperLeftLowerRight_9() {
    GameOfLife society = new GameOfLife(10, 16);
    society.growCellAt(6, 6);
    society.growCellAt(6, 7);
    society.growCellAt(7, 6);
    society.growCellAt(5, 5);

    society.growCellAt(0, 1);
    society.growCellAt(1, 0);
    society.growCellAt(9, 15);
    assertEquals(2, society.neighborCount(0, 15));

    society.update();
    assertTrue(society.cellAt(0, 0));
    assertFalse(society.cellAt(0, 1));
    assertFalse(society.cellAt(1, 0));
    assertEquals(0, society.neighborCount(0, 0));
  }

  /**
   * These tests check the GameOfLife class and assumes method headers are
   * correct. <br>
   * Created on: September 28, 2006<br>
   * Last Modified on: September 29, 2006
   * 
   * @author michaels
   * 
   */
  @Test
  public void testUpdateOnEdge_10() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(2, 0);
    society.growCellAt(3, 0);
    society.growCellAt(4, 0);
    society.update();
    assertTrue(society.cellAt(3, 6));
    assertTrue(society.cellAt(3, 0));
    assertTrue(society.cellAt(3, 1));
    assertFalse(society.cellAt(2, 0));
    assertFalse(society.cellAt(4, 0));
  }

  @Test
  public void testUpdateOnEdge_11() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 2);
    society.growCellAt(0, 3);
    society.growCellAt(0, 4);
    society.update();
    assertTrue(society.cellAt(6, 3));
    assertTrue(society.cellAt(0, 3));
    assertTrue(society.cellAt(1, 3));
    assertFalse(society.cellAt(0, 2));
    assertFalse(society.cellAt(0, 4));
  }

  @Test
  public void testUpdateOnEdge_12() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(2, 6);
    society.growCellAt(3, 6);
    society.growCellAt(4, 6);
    society.update();
    assertTrue(society.cellAt(3, 5));
    assertTrue(society.cellAt(3, 6));
    assertTrue(society.cellAt(3, 0));
    assertFalse(society.cellAt(2, 6));
    assertFalse(society.cellAt(4, 6));
  }

  @Test
  public void testUpdateOnEdge_13() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(6, 2);
    society.growCellAt(6, 3);
    society.growCellAt(6, 4);
    society.update();
    assertTrue(society.cellAt(5, 3));
    assertTrue(society.cellAt(6, 3));
    assertTrue(society.cellAt(0, 3));
    assertFalse(society.cellAt(6, 2));
    assertFalse(society.cellAt(6, 4));
  }

  @Test
  public void testUpdateInCorners_14() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 0);
    society.growCellAt(1, 0);
    society.growCellAt(6, 0);
    society.update();
    assertTrue(society.cellAt(0, 1));
    assertTrue(society.cellAt(0, 0));
    assertTrue(society.cellAt(0, 6));
    assertFalse(society.cellAt(1, 0));
    assertFalse(society.cellAt(6, 0));
  }

  @Test
  public void testUpdateInCorners_15() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 0);
    society.growCellAt(0, 1);
    society.growCellAt(0, 6);
    society.update();
    assertTrue(society.cellAt(1, 0));
    assertTrue(society.cellAt(0, 0));
    assertTrue(society.cellAt(6, 0));
    assertFalse(society.cellAt(0, 1));
    assertFalse(society.cellAt(0, 6));
  }

  @Test
  public void testUpdateInCorners_16() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 0);
    society.growCellAt(5, 0);
    society.growCellAt(6, 0);
    society.update();
    assertTrue(society.cellAt(6, 1));
    assertTrue(society.cellAt(6, 6));
    assertTrue(society.cellAt(6, 0));
    assertFalse(society.cellAt(5, 0));
    assertFalse(society.cellAt(0, 0));
  }

  @Test
  public void testUpdateInCorners_17() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 0);
    society.growCellAt(0, 5);
    society.growCellAt(0, 6);
    society.update();
    assertTrue(society.cellAt(1, 6));
    assertTrue(society.cellAt(6, 6));
    assertTrue(society.cellAt(0, 6));
    assertFalse(society.cellAt(0, 5));
    assertFalse(society.cellAt(0, 0));
  }

  @Test
  public void testGrowCellAtCorner_18() {
    GameOfLife society = new GameOfLife(7, 7);
    assertFalse(society.cellAt(5, 6));
    assertFalse(society.cellAt(6, 6));
    assertFalse(society.cellAt(0, 6));
    assertFalse(society.cellAt(5, 5));
    assertFalse(society.cellAt(6, 5));
    assertFalse(society.cellAt(0, 5));
    assertFalse(society.cellAt(5, 0));
    assertFalse(society.cellAt(6, 0));
    assertFalse(society.cellAt(0, 0));
    society.growCellAt(6, 6);
    assertTrue(society.cellAt(6, 6));
    assertFalse(society.cellAt(5, 6));
    assertFalse(society.cellAt(0, 6));
    assertFalse(society.cellAt(5, 5));
    assertFalse(society.cellAt(6, 5));
    assertFalse(society.cellAt(0, 5));
    assertFalse(society.cellAt(5, 0));
    assertFalse(society.cellAt(6, 0));
    assertFalse(society.cellAt(0, 0));
  }

  @Test
  public void testNeighborCount_19() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(2, 2);
    society.growCellAt(3, 2);
    society.growCellAt(4, 2);
    assertEquals(3, society.neighborCount(3, 3));
    assertEquals(2, society.neighborCount(3, 2));
    assertEquals(2, society.neighborCount(2, 1));
    assertEquals(0, society.neighborCount(3, 4));
  }

  @Test
  public void testNeighborCountOnEdge_20() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 2);
    society.growCellAt(0, 3);
    society.growCellAt(0, 4);
    assertEquals(3, society.neighborCount(6, 3));
    assertEquals(1, society.neighborCount(6, 1));
    assertEquals(2, society.neighborCount(6, 2));
    assertEquals(0, society.neighborCount(6, 0));
  }

  @Test
  public void testNeighborCountOnEdge21() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(2, 6);
    society.growCellAt(3, 6);
    society.growCellAt(4, 6);
    assertEquals(3, society.neighborCount(3, 0));
    assertEquals(1, society.neighborCount(1, 0));
    assertEquals(2, society.neighborCount(2, 0));
    assertEquals(0, society.neighborCount(0, 0));
  }

  @Test
  public void testNeighborCountOnEdge_22() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(6, 2);
    society.growCellAt(6, 3);
    society.growCellAt(6, 4);
    assertEquals(3, society.neighborCount(0, 3));
    assertEquals(1, society.neighborCount(0, 1));
    assertEquals(2, society.neighborCount(0, 2));
    assertEquals(0, society.neighborCount(0, 0));
  }

  @Test
  public void testNeighborCountInCorners_23() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 0);
    society.growCellAt(1, 0);
    society.growCellAt(6, 0);
    assertEquals(3, society.neighborCount(0, 6));
    assertEquals(2, society.neighborCount(1, 6));
    assertEquals(2, society.neighborCount(0, 0));
    assertEquals(1, society.neighborCount(6, 0));
    assertEquals(2, society.neighborCount(6, 6));
    assertEquals(1, society.neighborCount(5, 6));
    assertEquals(0, society.neighborCount(4, 6));
  }

  @Test
  public void testNeighborCountInCorners_24() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(6, 5);
    society.growCellAt(6, 6);
    society.growCellAt(6, 0);
    assertEquals(3, society.neighborCount(5, 6));
    assertEquals(3, society.neighborCount(0, 6));
    assertEquals(2, society.neighborCount(5, 5));
    assertEquals(2, society.neighborCount(6, 6));
    assertEquals(1, society.neighborCount(6, 0));
    assertEquals(2, society.neighborCount(0, 0));
    assertEquals(1, society.neighborCount(0, 4));
    assertEquals(0, society.neighborCount(4, 6));
  }

  @Test
  public void testNeighborCountInCorners_25() {
    GameOfLife society = new GameOfLife(7, 7);
    society.growCellAt(0, 5);
    society.growCellAt(0, 6);
    society.growCellAt(0, 0);
    assertEquals(3, society.neighborCount(1, 6));
    assertEquals(3, society.neighborCount(6, 6));
    assertEquals(2, society.neighborCount(1, 5));
    assertEquals(2, society.neighborCount(0, 6));
    assertEquals(1, society.neighborCount(0, 0));
    assertEquals(2, society.neighborCount(6, 0));
    assertEquals(1, society.neighborCount(6, 4));
    assertEquals(0, society.neighborCount(2, 6));

    GameOfLife society2 = new GameOfLife(7, 7);
    society2.growCellAt(0, 6);
    society2.growCellAt(1, 6);
    society2.growCellAt(6, 6);
    assertEquals(3, society2.neighborCount(0, 0));
    assertEquals(2, society2.neighborCount(1, 0));
    assertEquals(2, society2.neighborCount(0, 6));
    assertEquals(1, society2.neighborCount(6, 6));
    assertEquals(2, society2.neighborCount(6, 0));
    assertEquals(1, society2.neighborCount(5, 0));
    assertEquals(0, society2.neighborCount(4, 0));
  }

  @Test
  public void testNeighborCountwrapping() {
    GameOfLife society = new GameOfLife(5, 9);
    society.growCellAt(0, 5);
    society.growCellAt(0, 8);
    society.growCellAt(1, 0);
    society.growCellAt(2, 0);
    society.growCellAt(2, 8);
    society.growCellAt(3, 0);
    society.growCellAt(3, 8);
    society.growCellAt(4, 4);
    society.growCellAt(4, 6);
    /*
     * .....O..O O........ O.......O O.......O ....O.O..
     */
    assertEquals(2, society.neighborCount(0, 5));
    assertEquals(4, society.neighborCount(2, 8));
    assertEquals(4, society.neighborCount(2, 0));
    assertEquals(3, society.neighborCount(1, 0));
    assertEquals(3, society.neighborCount(4, 8));
    assertEquals(3, society.neighborCount(3, 8));
  }
}