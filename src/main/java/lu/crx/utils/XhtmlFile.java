package lu.crx.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XhtmlFile {
    private final String filename;
    private final List<String> lines;
    private final Map<Position, String> occurences = new HashMap<>();

    public XhtmlFile(String filename, List<String> lines) {
        this.filename = filename;
        this.lines = lines;
    }

    public String getFilename() {
        return filename;
    }

    public List<String> getLines() {
        return lines;
    }

    public Map<Position, String> getOccurences() {
        return occurences;
    }
}
