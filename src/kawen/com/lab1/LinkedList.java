package kawen.com.lab1;

import java.util.List;
import java.util.Scanner;

class Listnode {
    double data;
    Listnode next;

    public Listnode(double data) {
        this.data = data;
        this.next = null;
    }
}
public class LinkedList {
    public static Listnode CreateLinkedList(int[] elements){
        Listnode node = new Listnode(0);
        Listnode current = node;
        for (int element : elements) {
            current.next = new Listnode(element);
            current = current.next;
        }
        return node.next;
    }
    //非递减排序
    public static Listnode SortLinkedList(Listnode head) {
        if(head == null || head.next == null)return head;
        Listnode p = head.next, pstart = new Listnode(0), pend = head;
        pstart.next = head; //为了操作方便，添加一个头结点
        while(p != null)
        {
            Listnode tmp = pstart.next, pre = pstart;
            while(tmp != p && p.data >= tmp.data) //找到插入位置
            {tmp = tmp.next; pre = pre.next;}
            if(tmp == p)pend = p;
            else
            {
                pend.next = p.next;
                p.next = tmp;
                pre.next = p;
            }
            p = pend.next;
        }
        head = pstart.next;
        DeleteNode(pstart, pstart.data);
        return head;
    }
    //删除节点
    public static Listnode DeleteNode(Listnode head, double x) {
        Listnode node = new Listnode(0);
        node.next = head;
        Listnode prev = node;
        Listnode current = head;

        while (current != null) {
            if (current.data == x) {
                prev.next = current.next;
                break;
            }
            prev = current;
            current = current.next;
        }
        return node.next;
    }
    public static void  PrintLinkedList(Listnode head){
        Listnode current=head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("输入要建立单链表的元素个数");
        int n=scanner.nextInt();
        int[] elements=new int[n];
        System.out.println("输入元素：");
        for (int i = 0; i < n; i++) {
            elements[i]=scanner.nextInt();
        }
        Listnode head=CreateLinkedList(elements);
        System.out.println("建立的单链表为：");
        PrintLinkedList(head);
        System.out.println("非递减排序后的链表：");
        PrintLinkedList(SortLinkedList(head));
    }


}
