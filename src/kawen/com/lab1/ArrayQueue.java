package kawen.com.lab1;

import java.util.Scanner;

public class ArrayQueue {
    private double[] array;
    private int front;
    private int rear;
    private int capacity;

    //初始化
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.array = new double[capacity];
        this.front = this.rear = -1;
    }
    //判空
    public boolean isEmpty() {
        return front == -1;
    }
    //判满
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
    //入队
    public void enqueue(double data) {
        if (isFull()) {
            System.out.println("队满，无法入队.");
            return;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }

        array[rear] = data;
    }
    //出队
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("队空，无法出队.");
            return;
        }

        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
    }
    //打印
    public void display() {
        if (isEmpty()) {
            System.out.println("队空.");
            return;
        }

        int current = front;
        do {
            System.out.print(array[current] + " ");
            current = (current + 1) % capacity;
        } while (current != (rear + 1) % capacity);

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("顺序存储实现队列化：");
        System.out.println();
        System.out.println("输入队列的容量");
        int n=scanner.nextInt();
        ArrayQueue arrayQueue =new ArrayQueue(n);
        System.out.println("入队操作:");
        for (int i = 0; i < n; i++) {
            arrayQueue.enqueue(scanner.nextDouble());
        }
        System.out.println("完成入队操作的队列：");
        arrayQueue.display();
        System.out.println("出队操作：");
        arrayQueue.dequeue();
        System.out.println("出队一次后的队列");
        arrayQueue.display();
    }
}
