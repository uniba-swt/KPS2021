package evaluation.korat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(value = {"consumed"})
public class CellGraph {
    @JsonProperty("entries")
    public List<String> entries;

    @JsonProperty("matrix")
    public Map<String, Map<String, String>> matrix;

    @Override
    public String toString() {
        return "CellGraph{" +
                "entries=" + entries +
                ", matrix=" + matrix +
                '}';
    }

    public CellGraph clone(){
        CellGraph c = new CellGraph();
        c.entries = new ArrayList<String>(this.entries);
        c.matrix = new HashMap<String, Map<String, String>>(matrix);
        c.matrix.entrySet().stream().forEach(e ->         {
            String srcNode = e.getKey();
            c.matrix.put(srcNode, new HashMap<String, String>(matrix.get(srcNode)));
        });
        return c;
    }
}
