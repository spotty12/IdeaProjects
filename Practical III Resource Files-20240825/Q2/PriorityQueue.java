import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> {
    private MinHeap<E> heap = new MinHeap<E>();
	
	/* Gets the element with the highest priority*/
    public E peek(){
        //Add your code here;
        return heap.getFirst();
    }
	/* Adds an element to the queue*/
    public void enqueue(E newObject) {
         //Add your code here;
        heap.add(newObject);
    }
	/* Removes an element from the queue*/
    public E dequeue() {
        // Add your code here;
        return heap.remove();
    }

    public int getSize() {
        // Add your code here;
        return heap.getSize();
    }
	/* Returns a list of elements in the queue in the order 
	*they are pesisted in the underlying data structure*/
    public List <E> listElements() {
        ArrayList<E> list = new ArrayList<>();
        Iterator<E> itr = heap.iterator();
        while(itr.hasNext()) {
            list.add(itr.next());
        }
        return list;
    }
}