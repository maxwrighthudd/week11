package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class which contains the methods to return a topological sorted list.
 *
 * @author Max Wright
 * @param <T> generic type to allow different type graphs to be used with the..
 * implementation.
 */
public class refTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {

    private List<T> sort = new ArrayList<>();
    private HashMap<T, Integer> referenceCount = new HashMap<>();

    /**
     * Calls other methods to set up reference counts for nodes,
     * do the sort and then returns the sort.
     *
     * @return the topological sort as a list.
     * @throws GraphError
     */
    @Override
    public List<T> getSort() throws GraphError{
        setUpReferenceCounts();
        doSort();
        return sort;
    }

    /**
     * Checks that the node has a reference count of 0...
     * if so calls the visit node method.
     *
     * @throws GraphError if no nodes with reference count zero
     */
    public void doSort() throws GraphError {
        T node;
        while ((node = nextReferenceZeroNode()) != null){
            visitNode(node);
        }
    }

    /**
     * Contains a for loop which returns the set of nodes within the reference count
     * HashMap.
     *
     * @return the key of node which has a value of 0..
     * or null if it doesn't.
     */
    private T nextReferenceZeroNode() {
        //System.out.print(referenceCount);
        for (Map.Entry<T, Integer> node : referenceCount.entrySet()) {
            Integer value = node.getValue();
            if (value == 0){
                //System.out.println("=>" + node);
                return node.getKey();
            }
        }
        //System.out.println("=>X");
        return null;
    }

    /**
     * Uses a for loop to set up the reference count calling..
     * the getNodes method. Then increases the count of each node..
     * that has successors by calling the increaseReferenceCount method.
     *
     * @throws GraphError
     */
    private void setUpReferenceCounts() throws GraphError {
        for (T node:getNodes()){
            referenceCount.put(node, 0);
        }

        for (T node: getNodes()) {
            for (T successor: getNeighbours(node)) {
                increaseReferenceCount(successor);
            }
        }
    }

    /**
     * Visit nodes with a reference count of zero
     *
     * @param node in which is being visited from the doSort method.
     * @throws GraphError if node doesn't exist
     */
    private void visitNode(T node) throws GraphError {
        sort.add(node);
        for (T successor: getNeighbours(node)){
            decreaseReferenceCount(successor);
        }
        referenceCount.remove(node);
    }

    /**
     * Increases the count of a specified node
     *
     * @param node in which reference count is being increased within..
     * the reference count HashMap.
     */
    private void increaseReferenceCount(T node){
        Integer count = referenceCount.get(node);

        if (count == null){
            count = 1;
        } else {
            count++;
        }
        referenceCount.put(node, count);
    }

    /**
     * Decreases the count of a specified node
     *
     * @param node in which reference count is being decreased within..
     * the reference count HashMap as it has one less successor.
     */
    private void decreaseReferenceCount(T node){
        Integer count = referenceCount.get(node); //count is the value of the key

        if (count == 0){
            throw new IndexOutOfBoundsException("Can't decrease, reference count of 0");
        } else {
            count--;
        }
        referenceCount.put(node, count);
    }
}

