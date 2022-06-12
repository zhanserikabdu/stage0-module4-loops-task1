package school.mjc.stage0.loops.task1;

import school.mjc.stage0.base.BaseIOTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatIsBadTaskTest extends BaseIOTest {

    @Test
    void repeatIsBad() {
        StringBuilder expected = new StringBuilder();
        for (int runner = 0; runner < 20; ++runner) {
            expected.append("writing the same code doesn't have much impact, and it's also time consuming\n");
        }
        RepeatIsBad repeatIsBad = new RepeatIsBad();

        repeatIsBad.repeatIsBad();

        assertEquals(expected.toString(), updateLineSpliterators(outContent.toString()));
    }

    @Test
    public void containsNoLoopsOrSteams() throws IOException {
        Path path = Paths.get("src/main/java/school/mjc/stage0/loops/task1/RepeatIsBad.java");
        List<String> strings = Files.readAllLines(path);

        List<String> result = strings.stream()
                .filter(line -> line.contains("for") || line.contains("while") || line.contains("->"))
                .collect(Collectors.toList());
        List<String> souts = strings.stream()
                .filter(line -> line.contains("System.out.println"))
                .collect(Collectors.toList());

        assertEquals(0, result.size());
        assertEquals(20, souts.size());
    }
}