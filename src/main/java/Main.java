import com.google.common.collect.Lists;
import org.openjdk.jol.info.ClassLayout;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: wangmingjian02
 * @create: 2020-12-06
 **/
public class Main {
    public static void main(String[] args) {
//        String s = "10010111011110001001101010111100";
//        String s = "12345";
//        System.out.println(s.length());
//        Integer i  = Integer.parseInt(s);
//        System.out.println(s);
//        int i = 1000000007;
//        System.out.println(Integer.toBinaryString(i));

//        memory2();
//        time();
//        ttt();
//        System.out.println(System.currentTimeMillis());

//        testEqualsNull();
        testGroupingBy();

    }

    public static void testGroupingBy() {
        List<Integer> list = Lists.newArrayList(1, 1, 1, 1, 2, 2, 2, 2);
        Map<String, List<Integer>> collect = list.stream().collect(Collectors.groupingBy(e -> transform2Str(e)));
        System.out.println(collect);
    }

    public static String transform2Str(int i) {
        if (i == 1) {
            return "one";
        } else {
            return "other";
        }
    }

    public static void testEqualsNull() {
        String s = null;
        System.out.println("ABC".equals(s));
    }

    public static void testStreamNull() {
        List<String> list = new ArrayList<>();
        Set<String> set = list.stream().filter(e -> "I".equals(e)).collect(Collectors.toSet());
        if (set.contains("1")) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    public static void ttt() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<Integer> l = list.subList(0, 0);
        System.out.println(list.subList(0, 0));
    }


    public static void time() {
        List<Integer> list = new ArrayList<>();
        Random rd = new Random(1000000);
        long t1 = System.currentTimeMillis();
        for (int i = 1; i < 1000000; i++) {
            list.add(rd.nextInt());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("插入100w数据耗时：" + (t2 - t1));

        long t3 = System.currentTimeMillis();
        List<Integer> li = list.stream().filter(e -> e > 10000).collect(Collectors.toList());
        long t4 = System.currentTimeMillis();
        System.out.println("大小:" + li.size());
        System.out.println("stream过滤耗时:" + (t4 - t3));

        long t5 = System.currentTimeMillis();
        Collections.sort(list, Collections.reverseOrder());
        long t6 = System.currentTimeMillis();
        System.out.println("100w数据逆序耗时：" + (t6 - t5));

        long t7 = System.currentTimeMillis();
        Collections.sort(li, Collections.reverseOrder());
        long t8 = System.currentTimeMillis();
        System.out.println("50w数据逆序耗时：" + (t6 - t5));


    }

    public static void memory2() {
        long total = Runtime.getRuntime().totalMemory();
        long start = Runtime.getRuntime().freeMemory();
        System.out.println(total - start);
        Map<Long, Map<Long, Integer>> map = new ConcurrentHashMap<>();

        for (long i = 1; i < 1001; i++) {
            map.put(i, new HashMap<>());
            for (int j = 1; j < 1000; j++) {
                map.get(i).put(Integer.toUnsignedLong(j), j);
            }
        }
        long total1 = Runtime.getRuntime().totalMemory();
        long end = Runtime.getRuntime().freeMemory();

        System.out.println(total1 - end);
        long res = (total1 - end) - (total - start);
        System.out.println((double) res / (1024 * 1024));

    }


    public static void memory() {
        long total = Runtime.getRuntime().totalMemory();
        long start = Runtime.getRuntime().freeMemory();
        System.out.println(total - start);
        Map<Long, Integer> map = new ConcurrentHashMap<Long, Integer>();

        for (long i = 1; i < 1000001; i++) {
            map.put(i, (int) i);
        }
        long total1 = Runtime.getRuntime().totalMemory();
        long end = Runtime.getRuntime().freeMemory();

        System.out.println(total1 - end);
        long res = (total1 - end) - (total - start);
        System.out.println((double) res / (1024 * 1024));

    }

    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) {
            return -1;
        }
        Arrays.sort(nums);
        int mxf = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
            mxf = Math.max(mxf, map.get(nums[i]).size());
        }
        if (mxf > k) {
            return -1;
        }

        int block = n / k;
        if (k == n) {
            return 0;
        }
        Set<Integer> same = new HashSet<>();
        for (List<Integer> list : map.values()) {
            if (list.size() > 1) {
                int res = 0;
                for (int i : list) {
                    res |= (1 << i);
                }
                same.add(res);
            }
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);

        for (int i = 1; i < (1 << n); ++i) {
            int bit = Integer.bitCount(i);
            if (bit == block) {
                boolean f = false;
                for (int s : same) {
                    if (Integer.bitCount(i & s) > 1) {
                        f = true;
                        break;
                    }
                }
                if (!f) {
                    int low = Integer.lowestOneBit(i), high = Integer.highestOneBit(i);
                    int li = -1, hi = -1;
                    while (low > 0) {
                        li++;
                        low >>= 1;
                    }
                    while (high > 0) {
                        hi++;
                        high >>= 1;
                    }
                    dp[i] = nums[hi] - nums[li];
                }
            } else if (bit > block && bit % block == 0) {
                dp[i] = Integer.MAX_VALUE;
                for (int j = i; j != 0; j = (j - 1) & i) {
                    if (Integer.bitCount(j) % block == 0 && Integer.bitCount(i ^ j) % block == 0 && dp[j] != -1 && dp[i ^ j] != -1) {
                        dp[i] = Math.min(dp[i], dp[i ^ j] + dp[j]);
                    }
                }
                if (dp[i] == Integer.MAX_VALUE) {
                    dp[i] = -1;
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
