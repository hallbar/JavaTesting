package edu.osu.sort;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class SortTest  {



    private static List<Integer> randomIntegerArray(int n){
    	List<Integer> randomList = new ArrayList<Integer>();
    	Random randomNumber = new Random();

    	for(int i = 0; i < n; i++) {
    		// http://docs.oracle.com/javase/7/docs/api/java/util/Random.html
    		randomList.add(randomNumber.nextInt());	
    	}
		
		return randomList;
    }

    /*
    @Test // This test is an example template using an oracle
    public void randomTestExample() {
	int n_runs = 10;
	for(int i=0;i<n_runs;i++){
	    
	    // Generate a random array of size 10
	    List<Integer> sorted = randomIntegerArray(10);

	    // Copy the sorted array before sorting it 
	    List<Integer> original = new ArrayList(sorted);

	    // Sort it
	    Sort.sort(sorted);

	    // Test the result 
	    assertTrue(SortOracle.isSorted(original,sorted));
	}
    }
    */
    
    @Test
    public void randomTestOracle() {  
    	Random randomSize = new Random();

    	for(int i = 0; i < 100; i++) {
    		List<Integer> sorted = randomIntegerArray(randomSize.nextInt(1001));

    		List<Integer> original = new ArrayList(sorted);

    		Sort.sort(sorted);

    		assertTrue(SortOracle.isSorted(original, sorted));
    	}
    }

    @Test
    public void randomTestAssertion() {    
    	Random randomSize = new Random();

    	for(int i = 0; i < 100; i++) {
    		int j = randomSize.nextInt(1001);

    		List<Integer> sorted = randomIntegerArray(j);

    		List<Integer> original = new ArrayList(sorted);

    		Sort.sort(sorted);

    		// there are elements in the array
    		if(j > 0) {
    			// minimum value should be at first position
    			assertEquals(sorted.get(0), Collections.min(sorted));


    			// the last position contains the maximum value
    			assertEquals(sorted.get(j-1), Collections.max(sorted));
    		}

    		// there is more than one element in the array
    		if(j > 1) {
    			assertTrue(sorted.get(j-2) < sorted.get(j-1));
    		}

    		assertEquals(original.size(), sorted.size());


    	}

    }
}