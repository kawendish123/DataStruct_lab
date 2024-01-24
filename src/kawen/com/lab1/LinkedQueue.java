package kawen.com.lab1;

import java.util.Scanner;

public class LinkedQueue {
    private Listnode front;
    private Listnode rear;
    //初始化
    public LinkedQueue() {
        this.front = this.rear = null;
    }
    //判空
    public boolean isEmpty() {
        return front == null;
    }
    //入队
    public void enqueue(int data) {
        Listnode newNode = new Listnode(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }
    //出队
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("队空");
            return;
        }
        front = front.next;
        if (front == null) {
            rear = null;
        }
    }
    //打印队列
    public void display() {
        Listnode current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("链式存储实现队列化：");
        System.out.println();
        LinkedQueue linkedQueue=new LinkedQueue();
        System.out.println("输入元素个数");
        int n=scanner.nextInt();
        System.out.println("入队操作:");
        for (int i = 0; i < n; i++) {
            linkedQueue.enqueue(scanner.nextInt());
        }
        System.out.println("完成入队操作的队列：");
        linkedQueue.display();
        System.out.println("出队操作：");
        linkedQueue.dequeue();
        System.out.println("出队一次后的队列");
        linkedQueue.display();
    }
}
