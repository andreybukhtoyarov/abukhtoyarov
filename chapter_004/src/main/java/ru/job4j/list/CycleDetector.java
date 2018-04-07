package ru.job4j.list;

/**
 * This class find loop in linked lists..
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class CycleDetector {
    /**
     * Find loop in linked list.
     * @param first link to first node.
     * @return true if loop exist.
     */
    public boolean hasCycle(Node first) {
        boolean hasCycle = false;
        Node one = first;
        Node second = first;
        while (one != null && second != null) {
            one = one.next;
            second = second.next.next;
            if (one == second) {
                hasCycle = true;
                break;
            }
        }
        return hasCycle;
    }
}
