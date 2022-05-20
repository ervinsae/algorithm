package atomic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LeetCode {

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> hashmap = new HashMap<>();

        for(int i=0; i<nums.length; i++) {

            int temp = nums[i];
            if(hashmap.containsKey(temp)) {
                return new int[] {hashmap.get(temp), i};
            }
            else
                hashmap.put(target-temp,i);
        }
        return new int[] {};
    }




}
