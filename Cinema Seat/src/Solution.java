import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    static final List<Integer> singleGroupMasks = Arrays.asList(
        0b1000011111,
        0b1110000111,
        0b1111100001
    );
    static final int doubleGroupMask = 0b1000000001;  
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans = 2 * n;
        Map<Integer, Integer> binaryMap = new HashMap<>();
        for(int i = 0; i < reservedSeats.length; i++) {
            int row = reservedSeats[i][0];
            int seat = reservedSeats[i][1];
            int mask = 1 << (seat - 1);
            binaryMap.merge(row, mask, (oldValue, newValue) -> mask | oldValue);
        }
        for(int binary : binaryMap.values()) {
            if((binary | doubleGroupMask) == doubleGroupMask)
                continue;
            else if(singleGroupMasks.stream().anyMatch(mask -> (mask | binary) == mask))
                ans--;
            else
                ans -= 2;
        }
        return ans;
    }
}