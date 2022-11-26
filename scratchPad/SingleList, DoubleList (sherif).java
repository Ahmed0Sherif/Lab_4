public class Main {

    static class Node {
        private int data;
        private Node next;
        private Node previous;

        public int getData(){return data;}
        public Node getNext(){return next;}
        public Node getPrevious() {return previous;}

        public void setData (int newData){
            data = newData;
        }
        public void setNext (Node nextNode){
            next = nextNode;
        }
        public void setPrevious (Node previousNode){
            previous = previousNode;
        }

    }

    static class DoubleList{
        private Node head;
        private Node tail;
        private int size;
        public DoubleList(){
            head = null;
            tail = null;
            size = 0;
        }
        void addFirst(int data){
            Node newNode = new Node();
            newNode.setData(data);
            Node headNode = head;
            if (head == null){
                head = newNode;
                tail = newNode;
            }
            else{
                newNode.setNext(head);//The new element is connected with the 1st element.
                head.setPrevious(newNode);//The 1st element is connected to the new element.
                head = newNode;//The new head is assigned
            }
            size ++;
        }

        void addLast(int data){
            Node newNode = new Node();
            newNode.setData(data);
            Node headNode = head;
            //The new head is assigned
            if (tail == null){
                head = newNode;
            }
            else{
                newNode.setPrevious(tail);//The new element is connected with the 1st element.
                tail.setNext(newNode);//The 1st element is connected to the new element.
            }
            tail = newNode;
            size ++;
        }
        void removeHead () throws IllegalStateException{
            if (head == null)
                throw new IllegalStateException("The list is empty.");
            else{
                Node remover;
                remover = head;
                head = head.getNext();
                remover.setNext(null);
                head.setPrevious(null);
                size --;
            }
        }

        void removeTail () throws IllegalStateException{
            if (tail == null)
                throw new IllegalStateException("The list is empty.");
            else{
                Node remover;
                remover = tail;
                tail = tail.getPrevious();
                remover.setPrevious(null);
                tail.setNext(null);
                size --;
            }
        }

        void showCase() throws IllegalStateException{
            if (head.getNext() == null)
                throw new IllegalStateException("The list is empty, Dumb phoque");
            else{
                Node pointer;
                pointer = head;
                System.out.println();
                while (pointer != null) {
                    System.out.print(pointer.getData() + " , ");
                    pointer = pointer.getNext();
                }
                System.out.println("["+size+"]");
            }
        }
    }

    static class SingList {
        private Node head;
        private Node tail;
        private int size;

        public SingList(){
            head = null;
            tail = null;
            size = 0;
        }
        void addFirst (int data) {
            Node newNode = new Node();
            newNode.setData(data);
            if (head == null) {//If it is the first element it becomes both head and tail.
                head = newNode;
                tail = newNode;
            }
            else{
            newNode.next = head; //The element to become the head points to the current head.
            head = newNode;//Now, the added element becomes the new head, pointing to the new
            }
            size++;
        }
        void addLast (int data){
            Node newNode = new Node ();
            newNode.setData(data);
            //The last element of the list becomes the tail
            if (tail == null) { //Similar to addFirst
                head = newNode;
            }
            else{
                newNode.next = null;//The node to become the last one points to ground
                tail.next = newNode;//The current tail element points to the new element, making it the last element in the list
            }
            tail = newNode;
            size++;
        }

        void removeHead () throws IllegalStateException{
            if (head == null){
                throw new IllegalStateException("The list is empty");
            }
            else {
                Node remover;
                remover = head; //The remover has the same properties as head (it points to the same thing)
                head = head.getNext();//The element next to the head element becomes the new head.
                remover.setNext(null);//The remover is discarded
                size--;
            }
        }
        void removeTail() throws RuntimeException{
            if (tail==null){
                throw new IllegalStateException("The list is empty");
            }
            else{
                Node remover ;
                remover = head; //The remover has the same properties as head (it points to the same thing)
                while (remover.getNext()!=tail){//To get access the element preceding the tail,
                                                //, we have to take the long way, from the head towards the tail.
                                                //Because we can only access it this way using "next" pointer.
                    remover = remover.getNext();
                }
                remover.setNext(null);  //Once remover becomes the element before the tail,
                                        //The connection with the tail is cut by connecting the remover to null
                tail = remover;         //Since the element after the remover is null,
                                        // and the remover is connected to its preceding element,
                                        //Then, the remover can be the new tail.
                size --;
            }
        }
        void showCase () throws RuntimeException {
            if (head == null) {
                throw new IllegalStateException("The list is empty");
            } else {
                Node pointer = new Node();
                pointer = head;
                System.out.println();
                while (pointer != null) {
                    System.out.print(pointer.getData() + " , ");
                    pointer = pointer.getNext();
                }
                System.out.println("["+size+"]");
            }
        }


    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        SingList list = new SingList();
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addLast(40);
        list.addLast(70);
        list.showCase();
        list.removeHead();//removes 30
        list.removeHead();// removes 20
        list.showCase();
        list.addFirst(60);
        list.addFirst(43);
        list.removeTail();
        list.removeTail();
        list.showCase();

        DoubleList list2 = new DoubleList();
        list2.addFirst(-2);
        list2.addFirst(-56);
        list2.showCase();
        list2.addLast(-234);
        list2.addLast(-96);
        list2.showCase();
        list2.removeHead();
        list2.showCase();
        list2.addFirst(4);

        list2.showCase();
        list2.removeTail();
        list2.showCase();
    }
}