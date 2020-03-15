import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[] str = new String[]{"a", "AA", "CC", "c", "dd", "hello", "albatross", "oru", "JDK", "JVM", "virus"};

        WordSet wset = new WordSet(str);
        System.out.println(wset.toString());
        System.out.println(wset.newWordSetByWordLength(2));
        System.out.println(wset.vowelDivide()[0].toString());
        System.out.println(wset.vowelDivide()[1].toString());
    }
}
