import java.util.Arrays;
import java.util.Scanner;

// CLASS TO REPRESENT A SINGLE NODE
    class Node {
        int data;
        Node next = null;

        //constructor
        public Node() {
            
        }
        public Node(int val) {
            this.data = val;
        }
        public Node(int val, Node nextNode) {
            this.data = val;
            this.next = nextNode;
        }
    }

interface ILinkedList {
    /**
    * Inserts a specified element at the specified position in the list.
    * @param index
    * @param element
    */
    public void add(int index, Node element);
    /**
    * Inserts the specified element at the end of the list.
    * @param element
    */
    public void add(Node element);
    /**
    * @param index
    * @return the element at the specified position in this list.
    */
    public Node get(int index);
    
    /**
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index
    * @param element
    */
    public void set(int index, Node element);
    /**
    * Removes all of the elements from this list.
    */
    public void clear();
    /**
    * @return true if this list contains no elements.
    */
    public boolean isEmpty();
    /**
    * Removes the element at the specified position in this list.
    * @param index
    */
    public void remove(int index);
    /**
    * @return the number of elements in this list.
    */
    public int size();
    /**
    * @param fromIndex
    * @param toIndex
    * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
    */
    public SingleLinkedList sublist(int fromIndex, int toIndex);
    /**
    * @param o
    * @return true if this list contains an element with the same value as the specified element.
    */
    public boolean contains(Node o);
    }
    

        
        


    public class SingleLinkedList implements ILinkedList {
        static Node Head;

        public SingleLinkedList(Node head){
            Head = head;
        }


        public static void main(String[] args) {

            SingleLinkedList myList = new SingleLinkedList(Head);

            //GET INPUT FROM USER
            Scanner scan = new Scanner(System.in);
            String strArr = scan.nextLine();
            // String operation = scan.next();

            //GET LINKED LIST FROM INPUT
            int[] values = myList.stringToIntArray(strArr);
            myList.arrayToList(values);

            myList.dispList();
            
        
        
            scan.close();
        }


        public void arrayToList(int[] intArray) {

            int len = intArray.length;
            if (len == 0) {
                return;
            } else {
                Node[] arrayOfNodes = new Node[len];
                // LOOP TO SET VALUE OF EACH NODE
                for (int i = 0; i < len; i++) {
                    arrayOfNodes[i] = new Node(intArray[i]);
                }

                // LOOP TO SET POINTER OF EACH NODE
                for (int i = 0; i < len; i++) {
                    if (i == len-1) {
                        arrayOfNodes[i].next = null;
                    } else {
                        arrayOfNodes[i].next = arrayOfNodes[i+1];
                    }
                    
                }
                Head = arrayOfNodes[0];

            }
            
        }


        public int[] stringToIntArray(String s){
            if (s.equals("[]")) {
                int[] output = new int[0];
                return output;
            } else {
                String[] arrayOfStrings = s.trim().replace("[", "").replace("]", "").split(",");
                int len = arrayOfStrings.length;
                int[] output = new int[len];

                for (int i = 0; i < len; i++) {
                    output[i] = Integer.valueOf(arrayOfStrings[i].trim());
                }
                return output;

            }
            
        }
        

        @Override
        public void add(int index, Node element) {
            int currentIndex = 0;
            Node currentNode = Head, tempNode = null;

            if (index == 0) {
                element.next = Head;
                Head = element;
            } else {
                while (currentIndex < index-1) {
                    currentNode = currentNode.next;
                    currentIndex ++;
                }
                
                tempNode = currentNode.next;
                currentNode.next = element;
                element.next = tempNode;
            }
            
            

        }

        @Override
        public void add(Node element) {
            if (Head == null) {
                Head = element;
            } else {
                Node currentNode = Head;
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = element;
            }
            
        }

        @Override
        public Node get(int index) {
            Node currentNode = Head;
            int currentIndex = 0;
            while (currentNode != null) {
                if (currentIndex == index){
                    return currentNode;
                }
                else{
                    currentNode = currentNode.next;
                    currentIndex++;
                }
                
            }
            return null;
        }

        @Override
        public void set(int index, Node element) {
            Node currentNode = Head;
            int currentIndex = 0;
            
            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }

            currentNode.data = element.data;
        }

        @Override
        public void clear() {
            Head = null;            
        }

        @Override
        public boolean isEmpty() {
            return (Head == null);
        }

        @Override
        public void remove(int index) {
            if (index == 0) {
                Node temp = Head;
                Head = Head.next;
                temp.next = null;
            } else {
                Node prevNode = get(index-1);
                Node targetNode = prevNode.next;

                prevNode.next = targetNode.next;
                targetNode.next = null;
            }            
        }

        @Override
        public int size() {
            Node currentNode = Head;
            int size = 0;
            while (currentNode != null) {
                size++;
                currentNode = currentNode.next;
            }
            return size;
        }

        @Override
        public SingleLinkedList sublist(int fromIndex, int toIndex) {
            Node startNode = get(fromIndex);
            Node endNode = get(toIndex);

            Node newStartNode = new Node(startNode.data);
            SingleLinkedList resultList = new SingleLinkedList(newStartNode);
            Node currentNode = startNode;

            while (currentNode != endNode) {
                currentNode = currentNode.next;
                Node copyOfCurrentNode = new Node(currentNode.data);
                resultList.add(copyOfCurrentNode);
            }
            
            return resultList;
        }

        @Override
        public boolean contains(Node o) {
            Node currentNode = Head;
            while(currentNode != null){
                if (currentNode.data == o.data) {
                    return true;
                } else {
                    currentNode = currentNode.next;
                }
            }
            return false;
        }

        public void dispList() {
            Node currentNode = Head;
            while (currentNode != null) {
                System.out.println(currentNode.data);
                currentNode = currentNode.next;
            }
        }



        
    }
