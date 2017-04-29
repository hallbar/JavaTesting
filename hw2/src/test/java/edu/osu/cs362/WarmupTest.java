package edu.osu.cs362;

import static org.junit.Assert.*;
import org.junit.Test;


public class WarmupTest  {
    @Test
    public void testFindIntegerExample() {

		int result = WarmUp.findInteger(new int[] { 1, 2, 3, 4, 5 }, 100);
		assertEquals(result, -1);

		for (int i = 1; i < 5; i++) {
			int result2 = WarmUp.findInteger(new int[] { 1, 2, 3, 4, 5 }, i + 1);
			assertEquals("findInteger(new int[]{1,2,3,4,5}," + i + ")", result2, i);
		}
	}
	@Test
	public void testLastZero() {
		int result = WarmUp.lastZero(new int[] { 1, 2, 3, 4, 5 });
		assertEquals(-1, result);
	
		int result2 = WarmUp.lastZero(new int[] {1, 0, 0, 3, 0, 4, 0});
		assertEquals(6, result2);

		int result3 = WarmUp.lastZero(new int[] {1, 0, 0, 3, 0, 4});
		assertEquals(4, result3);

		int result4 = WarmUp.lastZero(new int[] {0, 1, 2, 3});
		assertEquals(0, result4);

		int[] nullArr = null;
		int result5 = WarmUp.lastZero(nullArr);
		assertEquals(-1, result5);
	}

	@Test
	public void testLast() {
		int[] nullArr = null;
		int result = WarmUp.last(nullArr, 1);
		assertEquals(-1, result);

		int result2 = WarmUp.last(new int[] {1, 2, 3, 4, 5}, 6);
		assertEquals(-1, result2);

		int result3 = WarmUp.last(new int[] {1, 2, 3, 4, 5}, 4);
		assertEquals(3, result3);
	}

	@Test
	public void testPositive() {
		int result = WarmUp.positive(new int[] {0, 0, 0, -1, -2});
		assertEquals(0, result);

		int result2 = WarmUp.positive(new int[] {1, 2, 3, 4, 5});
		assertEquals(5, result2);

		int result3 = WarmUp.positive(new int[] {0, 1, 0, 2, 0, 3});
		assertEquals(3, result3);
		
		int[] nullArr = null;
		int result4 = WarmUp.positive(nullArr);
		assertEquals(-1, result);
	}

	@Test
	public void testOddOrPos() {
		int[] nullArr = null;
		int result5 = WarmUp.oddOrPos(nullArr);
		assertEquals(-1, result5);
		
		int result = WarmUp.oddOrPos(new int[] {-1, -2, -3, -4, -5});
		assertEquals(5, result);

		int result2 = WarmUp.oddOrPos(new int[] {-1, 2, -3, 4, 5});
		assertEquals(5, result2);

		int result3 = WarmUp.oddOrPos(new int[] {0, 0, 0, 0, 0});
		assertEquals(0, result3);

		int result4 = WarmUp.oddOrPos(new int[] {1, 2, 3, 4, 5});
		assertEquals(5, result4);
	}
}
