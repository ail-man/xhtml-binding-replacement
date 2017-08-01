package lu.crx.utils;

import java.util.Objects;

public class Position {
    private final int line;
    private final int row;

    public Position(int line, int row) {
        this.line = line;
        this.row = row;
    }

    public int getLine() {
        return line;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return line == position.line &&
                row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, row);
    }
}
