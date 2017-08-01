package lu.crx.utils;

import java.util.List;

public class XhtmlFile {
    private final String filename;
    private final List<String> lines;

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
}
