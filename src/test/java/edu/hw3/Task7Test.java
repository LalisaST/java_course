package edu.hw3;

import edu.hw3.task7.TreeMapComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка компаратора c типом String")
    void checkingComparatorString() {
        TreeMapComparator<String> treeMapComparator = new TreeMapComparator<>();
        TreeMap<String, String> tree = new TreeMap<>(treeMapComparator);
        tree.put(null, "test");
        tree.put("text", "test");
        tree.put("text2", "test");
        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");
    }

    @Test
    @DisplayName("Проверка компаратора c типом Integer")
    void checkingComparatorInteger() {
        TreeMapComparator<Integer> treeMapComparator = new TreeMapComparator<>();
        TreeMap<Integer, String> tree = new TreeMap<>(treeMapComparator);
        tree.put(4, "test");
        tree.put(1, "test");
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test");
    }

}
