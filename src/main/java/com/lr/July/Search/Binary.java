package com.lr.July.Search;


import java.text.DecimalFormat;

/**
 * 二分查找
 *
 * @author shijie.xu
 * @since 2019年07月04日
 */
public class Binary {

    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int start = 0;
        int end = length - 1;
        while(start <= end) {
            int mid = ((end - start) >> 1) + start;
            if(nums[mid] == target) {
                return mid;
            } else if(start + 1 == end) {
                return end;
            } else if(target < nums[mid]) {
                end = mid;
            } else if(target > nums[mid]) {
                start = mid;
            }

        }
        return 0;
    }

    private static void insert(int[] nums, int target, int value) {
        for(int i = nums.length - 1; i >= target; i--) {
            nums[i + 1] = nums[i];
        }
        nums[target] = value;
    }


    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if(length <= 0 || target < nums[0] || target > nums[length - 1]) {
            return new int[]{-1, -1};
        }
        int start = 0, end = length - 1;

        int[] res = new int[2];
        while(start <= end) {
            int mid = ((end - start) >> 1) + start;
            if(target < nums[mid]) {
                end = mid - 1;
            } else if(target > nums[mid]) {
                start = mid + 1;
            } else {
                if(mid == 0 || nums[mid - 1] != target) {
                    res[0] = mid;
                    break;
                } else {
                    end = mid - 1;
                }
            }

        }
        return res;
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param a
     * @param n
     * @return
     */
    public static int pra1(int[] a, int n) {

        return 0;
    }

    /**
     * 查找最后一个值等于给定值的元素
     *
     * @param a
     * @param n
     * @return
     */
    public static int pra2(int[] a, int n) {
        return 0;
    }

    /**
     * 查找第一大于等于给定值的元素
     *
     * @param a
     * @param n
     * @return
     */
    public static int pra3(int[] a, int n) {
        int length = a.length;
        int start = 0;
        int end = length - 1;
        int mid;
        if(length <= 0 || n > a[end]) {
            return -1;
        }
        while(start <= end) {
            mid = ((end - start) >> 1) + start;
            if(n <= a[mid]) {
                if(mid == 0 || a[mid - 1] < n) {
                    return mid;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return -1;
    }

    /**
     * 查找最后一个值小于等于给定值的元素
     *
     * @param a
     * @param n
     * @return
     */
    public static int pra4(int[] a, int n) {
        return 0;
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search33(int[] nums, int target) {
        int length = nums.length;
        int start = 0;
        int end = length - 1;
        int mid = ((end - start) >> 1) + start;
        if(length <= 0) {
            return -1;
        }
        while(start <= end) {
            if(nums[mid] <= nums[end]) {
                if(target >= nums[mid] && target <= nums[end]) {
                    return binarySort(nums, mid, end, target);
                }
                end = mid - 1;
            } else {
                if(target >= nums[start] && target <= nums[mid]) {
                    return binarySort(nums, start, mid, target);
                }
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int binarySort(int[] a, int start, int end, int target) {
        while(start <= end) {
            int mid = ((end - start) >> 1) + start;
            if(a[mid] == target) {
                return mid;
            } else if(target < a[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }


    public static int search(int[] a, int target) {
        int length = a.length;
        int start = 0;
        int end = length - 1;
        while(start <= end) {
            int mid = ((end - start) >> 1) + start;
            if(a[mid] == target) {
                return mid;
            } else if(target < a[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static String sqort(int n) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        if(n < 0) {
            return null;
        }
        if(n == 0 || n == 1) {
            return decimalFormat.format((double) n);
        }

        int start = 0;
        int end = n;
        while(start < end) {
            int mid = ((end - start) / 2) + start;
            if(mid * mid == n) {
                return decimalFormat.format((double) mid);
            } else if(start + 1 == end) {
                if(end * end == n) {
                    return decimalFormat.format((double) end);
                }


            } else if(mid * mid < n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return decimalFormat.format((double) n);
    }

    public static boolean isNum(Double d, int n) {
        String t = d.toString();
        int index = t.indexOf(".");

        if(t.substring(index, t.length()).length() >= n) {
            return true;
        }
        return false;
    }

    /**
     * leetcode 69
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {

        if(x == 0 || x == 1) {
            return x;
        }

        long start = 0;
        long end = x;
        while(start < end) {
            long mid = ((end - start) / 2) + start;
            long temp = mid * mid;
            if(temp == x) {
                return (int) mid;
            } else if(start + 1 == end) {
                long e = end * end;
                if(e == x) {
                    return (int) end;
                }
                return (int) start;
            } else if(temp > x) {
                end = mid;
            } else {
                start = mid;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        mySqrt(2147395599);

        Double a = 646413.46486513456500000000;
        String b = a.toString();
        System.out.println(b);

    }

}
