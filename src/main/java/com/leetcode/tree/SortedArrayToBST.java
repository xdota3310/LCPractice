package com.leetcode.tree;

import com.leetcode.base.TreeNode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * @author shijie.xu
 * @since 2019年10月31日
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return create(nums,0,nums.length-1);
    }

    TreeNode create(int[] nums,int start,int end){
        if(start > end){
            return null;
        }
        int mid = (end - start)/2 + start;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = create(nums,start,mid-1);
        node.right = create(nums,mid+1,end);
        return node;
    }

    public static void main(String[] args) {
        int[] a = {-10,-3,0,5,9};
        new SortedArrayToBST().sortedArrayToBST(a);
    }
}
