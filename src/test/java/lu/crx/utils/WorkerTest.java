package lu.crx.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class WorkerTest {

    private static final String XHTML = ".xhtml";
    private static final String PATTERN_CLIENT_ID = ":#\\{([a-zA-Z])*\\.clientId}";
    private static String PROJECT_PATH = "D:\\job\\crx\\projects\\crx-git-repo\\impl";

    @Test
    public void doWork() throws Exception {
        Worker worker = new Worker();
        worker.doWork();
    }

    @Test
    public void findOccurences() throws Exception {
        String lineContent = "<ui:param name=\"kycProcessesFormId\" value=\":#{kycProcessesForm.clientId}\" value=\":#{kycProcessesForm.clientId}\"/>";
        Pattern pattern = Pattern.compile(PATTERN_CLIENT_ID);
//        Pattern pattern = Pattern.compile(":#");
        Matcher matcher = pattern.matcher(lineContent);
        // Check all occurrences
        while (matcher.find()) {
            log.info("Start index: " + matcher.start());
            log.info(" End index: " + matcher.end());
            log.info(" Found: " + matcher.group());
        }
    }

    @Test
    public void getFilesWithExtension() throws Exception {
        Stream<Path> stream = Files.find(
                Paths.get(PROJECT_PATH),
                Integer.MAX_VALUE,
                (path, attr) -> path.getFileName()
                        .toString()
                        .endsWith(XHTML)
        );
        stream.forEach(System.out::println);
    }

    @Test
    public void readFileByLine() throws Exception {
        String path = "D:\\job\\crx\\projects\\crx-git-repo\\impl\\main\\crx-app\\target\\temp\\crx-web.war\\app\\onboarding\\inc\\admin-onboarding-duplicate-check.xhtml";
        Files.lines(Paths.get(path))
        .forEach(System.out::println);
    }

    /*
    * Tool must:
0) Parse each *.xhtml-file as a plain text with UTF-8 encoding
1) Find all occurences in each FILE where ":#{<pattern>.clientId}" is.
2) Extract "someBindingName" from pattern ":#{someBindingName.clientId}"
3) Determine whether the binding with name "someBindingName" exists in exactly THIS file
4) If not - report to Bogdan and Alexey about problem that bindings can be in different files and you need to consider each situation separately and be careful.

5) If yes (all is ok) - replace all occurences of ":#{someBindingName.clientId}" with ":#{p:component('componentId')}" (Need to get 'componentId')
6) Check in this FILE, whether other properties are derived from #{someBindingName}
7) If so - binding remains. Else - remove binding attribute


1) Find binding="#{anySymbols[a-z][A-Z][0-9]GoHere}" (find out pattern)
2) Find any occurences
    *
    * */
}
