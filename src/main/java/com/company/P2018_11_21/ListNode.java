package com.company.P2018_11_21;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2018年11月26日
 */
public class ListNode {
    int data;
    ListNode next;

    public ListNode(int x) {
        this.data = x;
        this.next = null;
    }

    @Override
    public String toString() {
        StringBuilder re = new StringBuilder();
        re.append("ListNode{");
        while(this.next!=null){
            re.append("data="+this.data);
            next=next.next;
        }
        return re.toString();
    }
}
