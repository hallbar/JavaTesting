package edu.osu.sort;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class SortOracle  {
    public static boolean isSorted(List<Integer> original, List<Integer> sorted){
		/* 
		   Determines whether `sorted` is a sorted (ascending) version
		   of `original` by sorting original with a known good
		   implementation. This method should ***not*** mutate sorted

		   @param   original  a list of integers 
		   @param   sorted    a (possibly sorted) list of integers  
		   @returns      True if the list is sorted in ascending order
		*/


		List<Integer> copy = new ArrayList(original);

		// http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
		Collections.sort(copy);
		for(int i = 0; i < copy.size(); i++) {
			if(copy.get(i) != sorted.get(i)) {
				return false;
			}
		}
		return true;
    }


}
