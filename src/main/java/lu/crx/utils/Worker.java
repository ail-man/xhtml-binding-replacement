package lu.crx.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker {

    private static final String XHTML = ".xhtml";
    private static String PROJECT_PATH = "D:\\job\\crx\\projects\\crx-git-repo\\impl";
    private static final Pattern patternClientId = Pattern.compile(":#\\{([a-zA-Z])*\\.clientId}");

    public void doWork() throws IOException {
        getAllXhtmlFilesStream().forEach(file -> {
            log.info(file.toString());

            Map<Position, String> occurences = findOccurences(file);
            occurences.forEach((k, v) -> log.info(k.toString() + ": " + v));
        });
    }

    private Map<Position, String> findOccurences(Path xhtmlFile) {
        Map<Position, String> result = new HashMap<>();
        try {
            AtomicInteger count = new AtomicInteger();
            Files.lines(xhtmlFile).forEach(line -> {
                Matcher matcher = patternClientId.matcher(line);
                while (matcher.find()) {
                    int startIdx = matcher.start();
                    Position position = new Position(count.getAndIncrement(), startIdx);
                    String occurence = matcher.group();

                    log.info("Start index: " + startIdx);
                    log.info(" Found: " + occurence);
                    result.put(position, occurence);
                }
            });
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    private Stream<Path> getAllXhtmlFilesStream() throws IOException {
        return Files.find(
                Paths.get(PROJECT_PATH),
                Integer.MAX_VALUE,
                (path, attr) -> path.getFileName()
                        .toString()
                        .endsWith(XHTML)
        );
    }
}
