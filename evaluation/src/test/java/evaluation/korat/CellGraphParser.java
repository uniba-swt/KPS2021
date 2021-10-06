package evaluation.korat;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CellGraphParser {

    public List<CellGraph> parse(String fileName) throws IOException, URISyntaxException {

        Path path = Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI());
        CellGraph[] graphs_ = new ObjectMapper().readValue(path.toFile(), CellGraph[].class);
        List<CellGraph> cellGraphs = Arrays.asList(graphs_);
        return cellGraphs;
    }
}
