
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1=pHead1;
        ListNode p2=pHead2;
        int l1=getLength(p1);
        int l2=getLength(p2);
        ListNode plong=pHead1;
        ListNode pshort=pHead2;
        int diff=l1-l2;
        if(diff<0){
            plong=pHead2;
            pshort=pHead1;
            diff=l2-l1;
        }
        while(diff>0){
            plong=plong.next;
            diff--;
        }
        while(plong!=pshort&&plong!=null&&pshort!=null){
            plong=plong.next;
            pshort=pshort.next;
        }
        return plong;
    }
    public int getLength(ListNode p){
        int i=0;
        while(p!=null){
            i++;
            p=p.next;
        }
        return i;
    }
}