import org.example.MiningRepresentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MiningTest {

    @Test
    public void testNoWorker() {
        String input = "M.......B";
        int n = 5;
        List<String> expected = List.of(
                "M.......B",
                "M.......B",
                "M.......B",
                "M.......B",
                "M.......B"
        );

        List<String> actual = MiningRepresentation.generate(input, n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testOneWorker() {
        String input = "M..<..B";
        int n = 12;

        List<String> expected = List.of(
                "M..<..B",
                "M.<...B",
                "M<....B",
                "*.....B", // Worker is mining gold
                "M>....B",
                "M.>...B",
                "M..>..B",
                "M...>.B",
                "M....>B",
                "M.....*", // Worker brought the gold
                "M....<B",
                "M...<.B"
        );

        List<String> actual = MiningRepresentation.generate(input, n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testWorkersWithoutCollision() {
        String input = "M..<>..B";
        int n = 11;

        List<String> expected = List.of(
                "M..<>..B",
                "M.<..>.B",
                "M<....>B",
                "*......*", // one worker mines, other in base
                "M>....<B",
                "M.>..<.B",
                "M..><..B", // smooth collision
                "M..<>..B",
                "M.<..>.B",
                "M<....>B",
                "*......*"
        );

        List<String> actual = MiningRepresentation.generate(input, n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testWorkersCollision() {
        String input = "M>...<B";
        int n = 10;

        List<String> expected = List.of(
                "M>...<B",
                "M.>.<.B",
                "M..#..B", // two workers at the same spot
                "M.<.>.B",
                "M<...>B",
                "*.....*",
                "M>...<B",
                "M.>.<.B",
                "M..#..B", // again collision
                "M.<.>.B"
        );

        List<String> actual = MiningRepresentation.generate(input, n);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testThreeWorkers() {
        String input = "M.<<<.B";
        int n = 12;

        List<String> expected = List.of(
                "M.<<<.B",
                "M<<<..B",
                "*<<...B", // 1st in mine, 2nd and 3rd are waiting
                "*#....B", // 2nd in mine, 1st and 3rd same position(1st goes to base, 3rd waiting to enter mine)
                "*>>...B", // 3rd worker in mine, 1st and 2nd are bringing gold to base
                "M>>>..B",
                "M.>>>.B",
                "M..>>>B",
                "M...>>*", // Same as above
                "M....#*",
                "M...<<*",
                "M..<<<B"  // All three workers goes mining
        );

        List<String> actual = MiningRepresentation.generate(input, n);
        Assertions.assertEquals(expected, actual);
    }

}