package kawen.com.lab1;

import org.w3c.dom.Node;

import java.util.PriorityQueue;
import java.util.Scanner;
//冒泡
class SortBubbleSortLinkedList{
    public static Listnode bubbleSort(Listnode head) {
        if (head == null || head.next == null) {
            return head;
        }
        boolean swapped;
        Listnode last = null;
        do {
            swapped = false;
            Listnode current = head;
            Listnode prev = null;
            while (current.next != last) {
                if (current.data > current.next.data) {
                    if (prev != null) {
                        prev.next = current.next;
                    } else {
                        head = current.next;
                    }

                    Listnode temp = current.next.next;
                    current.next.next = current;
                    current.next = temp;
                    swapped = true;
                }
                prev = current;
                current = current.next;
            }
            last = current;
        } while (swapped);
        return head;
    }
}

//快排
 class QuickSortLinkedList {
    public static Listnode quickSort(Listnode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Listnode pivot = partition(head);
        Listnode left = quickSort(head);
        Listnode right = quickSort(pivot.next);

        pivot.next = right;
        return left != null ? left : pivot;
    }

    public static Listnode partition(Listnode head) {
        Listnode slow = head;
        Listnode fast = head;
        Listnode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // Split the list into two halves
        return slow;
    }
}
//归并
 class MergeSortLinkedList {
    public static Listnode mergeSort(Listnode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Listnode middle = getMiddle(head);
        Listnode nextOfMiddle = middle.next;

        middle.next = null;

        Listnode left = mergeSort(head);
        Listnode right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    public static Listnode getMiddle(Listnode head) {
        if (head == null) {
            return null;
        }

        Listnode slow = head;
        Listnode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static Listnode merge(Listnode left, Listnode right) {
        Listnode result = null;

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        if (left.data <= right.data) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }

        return result;
    }
}

//堆排序
 class HeapSortLinkedList {
    public static Listnode heapSort(Listnode head) {
        if (head == null || head.next == null) {
            return head;
        }

        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        Listnode current = head;

        while (current != null) {
            minHeap.add(current.data);
            current = current.next;
        }

        current = head;
        while (!minHeap.isEmpty()) {
            current.data = minHeap.poll();
            current = current.next;
        }

        return head;
       /* if (head == null || head.next == null) {
            return head;
        }

        // 创建一个新的链表，用于存储排序后的结果
        Listnode sortedList = new Listnode(0); // 使用一个虚拟头结点简化代码
        Listnode current = head;

        // 创建一个最小堆
        PriorityQueue<Double> minHeap = new PriorityQueue<>();

        // 将链表元素放入最小堆中
        while (current != null) {
            minHeap.add(current.data);
            current = current.next;
        }

        // 从最小堆中取出元素，构建有序链表
        current = sortedList;
        while (!minHeap.isEmpty()) {
            current.next = new Listnode(minHeap.poll());
            current = current.next;
        }
        return sortedList.next; // 返回排序后链表的头结点*/
    }
}
public class SortExample {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("输入要建立单链表的元素个数");
        int n=scanner.nextInt();
        int[] elements=new int[n];
        System.out.println("输入元素：");
        for (int i = 0; i < n; i++) {
            elements[i]=scanner.nextInt();
        }
        Listnode head= LinkedList.CreateLinkedList(elements);
        System.out.println("建立的单链表为：");
        LinkedList.PrintLinkedList(head);

        System.out.println("简单插入排序:");
        LinkedList.PrintLinkedList(LinkedList.SortLinkedList(head));
        System.out.println("冒泡排序:");
        LinkedList.PrintLinkedList(SortBubbleSortLinkedList.bubbleSort(head));
        System.out.println("快速排序:");
        LinkedList.PrintLinkedList(QuickSortLinkedList.quickSort(head));
        System.out.println("归并排序:");
        LinkedList.PrintLinkedList(MergeSortLinkedList.mergeSort(head));
        System.out.println("堆排序:");
        LinkedList.PrintLinkedList(HeapSortLinkedList.heapSort(head));

    }
}
