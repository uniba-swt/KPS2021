package evaluation.korat.binarytree;

import evaluation.korat.CellGraphParser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoverageTest {

    public void run(String fileName, int size, int expectedAmountOfGraphs) throws IOException, URISyntaxException {
        List<BinaryTree> binaryTrees = new BinaryTreeParser().fromCellGraphs(new CellGraphParser().parse(fileName),
                size);
        assertEquals(expectedAmountOfGraphs, binaryTrees.size());
        binaryTrees.forEach(bt -> {
            assert !bt.repOK();
        });
    }

    @Test
    public void exhaustive_bt_1() throws IOException, URISyntaxException {
        run("exhaustive-bt-1.json", 1, 7);
    }

    @Test
    public void exhaustive_bt_2() throws IOException, URISyntaxException {
        run("exhaustive-bt-2.json", 2, 239);
    }

    @Test
    public void exhaustive_bt_3() throws IOException, URISyntaxException {
        run("exhaustive-bt-3.json", 3, 16354);
    }

    @Test
    public void korat_bt_1() throws IOException, URISyntaxException {
        run("korat-bt-1.json", 1, 4);
    }

    @Test
    public void korat_bt_2() throws IOException, URISyntaxException {
        run("korat-bt-2.json", 2, 48);
    }

    @Test
    public void korat_bt_3() throws IOException, URISyntaxException {
        run("korat-bt-3.json", 3, 861);
    }

    @Test
    public void mutation_bt_1() throws IOException, URISyntaxException {
        run("mutation-bt-1.json", 1, 3);
    }

    @Test
    public void mutation_bt_2() throws IOException, URISyntaxException {
        run("mutation-bt-2.json", 2, 15);
    }

    @Test
    public void mutation_bt_3() throws IOException, URISyntaxException {
        run("mutation-bt-3.json", 3, 69);
    }
}
