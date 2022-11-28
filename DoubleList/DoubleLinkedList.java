import java.util.Scanner;

interface ILinkedList{ //All required methods:
    public void add(int element);
    public  void addToIndex(int element, int index);
    public Object get(int index);
    public void set(int index, int element);
    public void clear();
    public boolean isEmpty();
    public void remove(int index);
    public int size();
    public boolean contains (int element);
    public Object sublist(int fromIndex, int toIndex);
}

public class DoubleLinkedList implements ILinkedList{
//Classes for Nodes and Double Linked List are written here
    class Node {
        private int data;
        private Node next;
        private Node previous;

        //Methods for manipulating Nodes
        //Getters
        public int getData(){return data;}
        public Node getNext(){return next;}
        public Node getPrevious() {return previous;}

        //Setters
        public void setData (int newData){data = newData;}
        public void setNext (Node nextNode){next = nextNode;}
        public void setPrevious (Node previousNode){previous = previousNode;}

    }

    class DoubleList {
        private Node head;
        private Node tail;
        int size;

        //A constructor for building an empty linked list
        public DoubleList() {
            head = null;
            tail = null;
            size = 0;
        }
        //Methods for manipulating linked lists
        //Getters
        Node getHead() {return head;}
        Node getTail() {return tail;}
        int getSize() {return size;}

        //Setters
        void setHead(Node node) {head = node;}
        void setTail(Node node) {tail = node;}
        void setSize(int size){this.size =size;}
    }
    //Initializing two lists, one for operations and the other for taking sublist from the main list.
    DoubleList list = new DoubleList();
    DoubleList subList = new DoubleList();

    //Calculates the size of the list, and stores it in the size variable.
    void calSize(){
        int place = 0;
        if (list.getHead() != null) {
            place = 1;
            Node pointer = list.getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
                place++;
            }
            list.setSize(place);
        }
    }
    //Takes a list and prints it in the desired format.
    void showCase(DoubleList list)  {
        if (list.getHead() == null)
            System.out.println("[]");
        else {
            Node pointer;
            pointer = list.getHead();
            System.out.print("[");
            while (pointer != list.getTail()) {
                System.out.print(pointer.getData() + ", ");
                pointer = pointer.getNext();
            }
            System.out.print(pointer.getData());
            System.out.println("]");
        }
    }
//Changes the value of an element of the entered index with the entered value, then prints the new list
    public void set(int index, int element){
        if (index < 0 || index >=list.getSize() )
            System.out.println("Error");
        else {
            int place = 0;
            Node pointer = list.getHead();
            while (place != index) {
                pointer = pointer.getNext();
                place++;
            }
            pointer.setData(element);
            calSize();
            showCase(list);
        }
    }

    //Returns the value of the element with the entered index
    public Object get(int index){
        Node pointer = new Node() ;
        if (index < 0 || index >=list.getSize() )
            return null;
        else {
            int place = 0;
            pointer = list.getHead();
            while (place != index) {
                pointer = pointer.getNext();
                place++;
            }
            return pointer.getData();
        }
    }
    //Similar to the "add" method, but takes the index of the new element
    public void addToIndex(int element, int index){
        if (index < 0 || index >= list.getSize() ){
            System.out.println("Error");
        }
        else {
            int place = 0;
            Node pointer;
            Node newNode = new Node();
            newNode.setData(element);
            pointer = list.getHead();
            while (place != index) {
                pointer = pointer.getNext();
                place++;
            }
            if (place == 0) {
                newNode.setNext(list.getHead());
                list.getHead().setPrevious(newNode);
                list.setHead(newNode);
            } else {
                Node stepBack = pointer.getPrevious();
                newNode.setNext(pointer);
                pointer.setPrevious(newNode);
                newNode.setPrevious(stepBack);
                stepBack.setNext(newNode);
            }
            calSize();
            showCase(list);
        }
    }

    //Takes the value of a new element, and adds it to the end of the list
    public void add(int element) {
        Node newNode = new Node();
        newNode.setData(element);
        if (list.getTail() == null) {
            list.setTail(newNode);
            list.setHead(newNode);
        } else {
            newNode.setPrevious(list.getTail());//The new element is connected with the 1st element.
            list.getTail().setNext(newNode);//The 1st element is connected to the new element.
            list.setTail(newNode);
        }
        calSize();
    }

    //Self Explanatory
    public void clear(){
        if (list.getHead() == null)
            System.out.println("[]");
        else {
            list.getHead().getNext().setPrevious(null);
            list.getHead().setNext(null);
            list.setHead(null);
            calSize();
            showCase(list);
        }
    }
    //Checks if a list is empty
    public boolean isEmpty(){
        boolean boolEmpty = (list.getHead() == null)?true :false;
        return boolEmpty;
    }
    //Takes the index of the target element, and removes it.
    public void remove(int index) {
        if (index < 0 || index >= list.getSize())
            System.out.println("Error");
        else {
            int place = 0;
            Node pointer = list.getHead();
            while (place != index) {
                pointer = pointer.getNext();
                place++;
            }
            if (place == 0) { //If the target element is the head
                list.setHead(list.getHead().getNext());
                list.getHead().getPrevious().setNext(null);
                list.getHead().setPrevious(null);
            }
            else if (place == list.getSize()-1){ //If the target element is the tail
                list.setTail(list.getTail().getPrevious());
                list.getTail().getNext().setPrevious(null);
                list.getTail().setNext(null);
            }else {
                pointer.getNext().setPrevious(pointer.getPrevious());
                pointer.getPrevious().setNext(pointer.getNext());
                pointer.setNext(null);
                pointer.setPrevious(null);
            }
            calSize();
            showCase(list);
        }
    }
    //Returns the size
    public int size(){
        int listSize = 0;
        if (list.getHead() != null) {
            listSize = 1;
            Node pointer = list.getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
                listSize++;
            }
            list.setSize(listSize);
        }
        return listSize;
    }
    //Checks if the list contains the entered value
    public boolean contains (int element){
        boolean isHere = false;
        Node pointer = list.getHead();
        while (pointer != null){
            if (pointer.getData() == element){
                isHere = true;
                break;
            }
            pointer = pointer.getNext();
        }
        return isHere;
    }
    //Takes two numbers: fromIndex and toIndex and returns a sublist from the entered limits.
    public Object sublist(int fromIndex, int toIndex){
        if ( (fromIndex < 0) || (fromIndex >= list.getSize()) || (toIndex < 0) || (toIndex >= list.getSize()) || (fromIndex > toIndex) )
            return null;
        else {
            int place = 0;
            Node pointer = list.getHead();
            while (place != fromIndex) {
                place++;
                pointer = pointer.getNext();
            }
            subList.setHead(pointer);
            while (place != toIndex) {
                place++;
                pointer = pointer.getNext();
            }
            subList.setTail(pointer);
            return subList;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList obs = new DoubleLinkedList();
        Scanner myScan = new Scanner(System.in);
        String input = myScan.nextLine();//Takes the input from user in the defined format
        String in = input.substring(1, input.lastIndexOf(']'));
        //If the input is [], then it skips the next steps, leaving an empty list
        if (input.equals("[]")){
        }
        else{//If the list is not empty, it processes it to build the list
            String[] array = in.split(", ");
            int [] integerArray = new int [array.length];
            for (int i = 0; i < array.length; i++) {
                integerArray[i] = Integer.parseInt(array[i]);
            }

            for (int i = 0; i < array.length; i++) {
                obs.add(integerArray[i]);
            }
        }
        obs.calSize(); //Calculates Size to use if needed
        //Awaits the command from user, along with other auxiliary lines.
        String command = myScan.next();
        switch (command){
            case "add":
                int element = myScan.nextInt();
                obs.add(element);
                obs.showCase(obs.list);
                break;
            case "addToIndex":
                int index = myScan.nextInt();
                element = myScan.nextInt();
                obs.addToIndex(element,index);
                break;
            case "get":
                index = myScan.nextInt();
                if (obs.get(index) == null)
                    System.out.println("Error");
                else
                    System.out.println(obs.get(index));
                break;
            case "set":
                index = myScan.nextInt();
                element = myScan.nextInt();
                obs.set(index,element);
                break;
            case "clear":
                obs.clear();
                break;
            case "isEmpty":
                if (obs.isEmpty() == true)
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "remove": //Return for fixing size calculations
                index = myScan.nextInt();
                obs.remove(index);
                break;
            case "size":
                System.out.println(obs.size());
                break;
            case "contains":
                element = myScan.nextInt();
                if (obs.contains(element) == true)
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "sublist":
                int fromIndex = myScan.nextInt();
                int toIndex   = myScan.nextInt();
                if ( (DoubleList) obs.sublist(fromIndex, toIndex) == null )
                    System.out.println("Error");
                else {
                    obs.subList = (DoubleList) obs.sublist(fromIndex, toIndex);
                    obs.showCase(obs.subList);
                }
                break;
            default:
                System.out.println("Error");
                break;
        }
    }
}
