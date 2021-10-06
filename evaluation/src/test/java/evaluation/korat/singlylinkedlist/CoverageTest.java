package evaluation.korat.singlylinkedlist;

import evaluation.korat.CellGraphParser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoverageTest {

    public void run(String fileName, int size, int expectedAmountOfGraphs) throws IOException, URISyntaxException {
        List<SinglyLinkedList> lists = new SinglyLinkedListParser()
                .fromCellGraphs(new CellGraphParser().parse(fileName), size);
        assertEquals(expectedAmountOfGraphs, lists.size());
        lists.forEach(sll -> {
            assert !sll.repOK();
        });
    }


    @Test
    public void exhaustive_sll_1() throws IOException, URISyntaxException {
        run("exhaustive-sll-1.json", 1, 3);
    }

    @Test
    public void exhaustive_sll_2() throws IOException, URISyntaxException {
        run("exhaustive-sll-2.json", 2, 25);
    }

    @Test
    public void exhaustive_sll_3() throws IOException, URISyntaxException {
        run("exhaustive-sll-3.json", 3, 250);
    }

    @Test
    public void korat_sll_1() throws IOException, URISyntaxException {
        run("korat-sll-1.json", 1, 2);
    }

    @Test
    public void korat_sll_2() throws IOException, URISyntaxException {
        run("korat-sll-2.json", 2, 5);
    }

    @Test
    public void korat_sll_3() throws IOException, URISyntaxException {
        run("korat-sll-3.json", 3, 9);
    }

    @Test
    public void mutation_sll_1() throws IOException, URISyntaxException {
        run("mutation-sll-1.json", 1, 2);
    }

    @Test
    public void mutation_sll_2() throws IOException, URISyntaxException {
        run("mutation-sll-2.json", 2, 5);
    }

    @Test
    public void mutation_sll_3() throws IOException, URISyntaxException {
        run("mutation-sll-3.json", 3, 9);
    }
}
