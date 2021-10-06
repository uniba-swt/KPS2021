package evaluation.korat.doublylinkedlist;

import evaluation.korat.CellGraphParser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoverageTest {

    public void run(String fileName, int size, int expectedAmountOfGraphs) throws IOException, URISyntaxException {
        List<DoublyLinkedList> lists = new DoublyLinkedListParser().fromCellGraphs(new CellGraphParser().parse(fileName), size);
        assertEquals(expectedAmountOfGraphs, lists.size());
        lists.forEach(dll -> {
            assert !dll.repOK();
        });
    }


    @Test
    public void exhaustive_dll_1() throws IOException, URISyntaxException {
        run("exhaustive-cdll-1.json", 1, 7);
    }

    @Test
    public void exhaustive_dll_2() throws IOException, URISyntaxException {
        run("exhaustive-cdll-2.json", 2, 241);
    }

    @Test
    public void exhaustive_dll_3() throws IOException, URISyntaxException {
        run("exhaustive-cdll-3.json", 3, 16378);
    }

    @Test
    public void korat_dll_1() throws IOException, URISyntaxException {
        run("korat-cdll-1.json", 1, 4);
    }

    @Test
    public void korat_dll_2() throws IOException, URISyntaxException {
        run("korat-cdll-2.json", 2, 49);
    }

    @Test
    public void korat_dll_3() throws IOException, URISyntaxException {
        run("korat-cdll-3.json", 3, 864);
    }

    @Test
    public void mutation_dll_1() throws IOException, URISyntaxException {
        run("mutation-cdll-1.json", 1, 3);
    }


    @Test
    public void mutation_dll_2() throws IOException, URISyntaxException {
        run("mutation-cdll-2.json", 2, 9);
    }


    @Test
    public void mutation_dll_3() throws IOException, URISyntaxException {
        run("mutation-cdll-3.json", 3, 19);
    }
}