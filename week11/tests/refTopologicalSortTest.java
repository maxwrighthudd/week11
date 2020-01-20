import graph.GraphError;
import graph.refTopologicalSort;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;


class refTopologicalSortTest {

    @Test
    void getSort() throws GraphError {
        refTopologicalSort<Integer> graph = new refTopologicalSort<>();

        for (int i=0; i<= 9; i++){
            graph.add(i);
        }

        graph.add(0,1);
        graph.add(0,5);
        graph.add(1,7);
        graph.add(3,2);
        graph.add(3,8);
        graph.add(3,4);
        graph.add(6,0);
        graph.add(6,1);
        graph.add(6,2);
        graph.add(8,7);
        graph.add(8,4);

        System.out.println(graph.getSort());
    }

    @Test
    void nodesInGraph() throws GraphError{
        refTopologicalSort<Integer> graph = new refTopologicalSort<>();

        for (int i=0; i<= 9; i++){
            graph.add(i);
        }

        graph.add(0,1);
        graph.add(0,5);
        graph.add(1,7);
        graph.add(3,2);
        graph.add(3,8);
        graph.add(3,4);
        graph.add(6,0);
        graph.add(6,1);
        graph.add(6,2);
        graph.add(8,7);
        graph.add(8,4);

       int graphNodes = graph.getNoOfNodes();

       //Tests that graph contains all the nodes
       assertEquals(10,graphNodes);
    }

    //Testing the size of the sort compared to the size of the graph...
    //this verifies not only all nodes have been added but also that...
    //they have only been added once.
    @Test
    void compareSortToGraph() throws GraphError{
        refTopologicalSort<Integer> graph = new refTopologicalSort<>();

        for (int i=0; i<= 6; i++){
            graph.add(i);
        }

        graph.add(0,1);
        graph.add(0,5);
        graph.add(1,6);
        graph.add(3,2);
        graph.add(3,4);
        graph.add(6,2);

        int graphSize = graph.getNoOfNodes();
        int sortSize = graph.getSort().size();

        assertEquals(graphSize, sortSize);

    }
}

//test size of graph against size of sort (graph.size against sort.getsize)
//test nodes