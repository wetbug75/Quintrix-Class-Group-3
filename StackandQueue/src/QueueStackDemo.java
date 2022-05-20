/**
    Class for testing Queue and Stack Impls
*/
class QueueStackDemo {

    public static void main(String[] args) {
        Queue < Integer > queue = new Queue < Integer > (5);
        //add 2 4 6 8 10 at the end
        queue.add(2);
        queue.add(4);
        queue.add(6);
        queue.add(8);
        queue.add(10);
        
        //remove 2 elements from the start
        queue.remove();
        queue.remove();
        //add 12 and 14 at the end
        queue.add(12);
        queue.add(14);

        System.out.println("Queue:");
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }

        Stack < Integer > stack = new Stack < Integer > (3);

        stack.push(1); // inserting 1 in the stack
        stack.push(2); // inserting 2 in the stack

        stack.pop(); // removing the top element (2)
        stack.pop(); // removing the top element (1)

        stack.push(3); // inserting 3 in the stack

        System.out.println("The top element is " + stack.peek());
        System.out.println("The stack size is " + stack.currentSize());

        stack.pop(); // removing the top element (3)

        // check if the stack is empty
        if (stack.isEmpty()) {
            System.out.println("The stack is empty");
        } else {
            System.out.println("The stack is not empty");
        }
    }
}