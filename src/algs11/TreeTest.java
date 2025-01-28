package algs11;

import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void testSize() {

        assertEquals(1, new Node(5).size());

        Node root = new Node(5, new Node(3), new Node(7));
        assertEquals(3, root.size());

        Node root1 = new Node(5, new Node(3, new Node(1), new Node(2)), new Node(7));
        assertEquals(5, root1.size());

    }

    @Test
    public void testmaxDepth() {
        assertEquals(1, new Node(5).maxDepth());

        Node root = new Node(5, new Node(3), new Node(7));
        assertEquals(2, root.maxDepth());

        Node root1 = new Node(5, new Node(3, new Node(1), new Node(2)), new Node(7));
        assertEquals(3, root1.maxDepth());

    }

    @Test
    public void testPrintInorder() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node secondTest = new Node(9, new Node(7, new Node(6), new Node(8)), new Node(10));
        Node thirdTest = new Node(4);
        Node fourthTest = new Node(4, new Node(5), new Node(3));
        Node fifthTest = new Node(5, new Node(6, new Node(7)));

        assertEquals("4", thirdTest.printInorder());
        assertEquals("543", fourthTest.printInorder());
        assertEquals("765", fifthTest.printInorder());
        assertEquals("12345", testTree.printInorder());
        assertEquals("678910", secondTest.printInorder());
    }

    @Test
    public void testPrintPostorder() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node secondTest = new Node(9, new Node(7, new Node(6), new Node(8)), new Node(10));
        Node thirdTest = new Node(4);
        Node fourthTest = new Node(4, new Node(5), new Node(3));
        Node fifthTest = new Node(5, new Node(6, new Node(7)));

        assertEquals("4", thirdTest.printPostOrder());
        assertEquals("534", fourthTest.printPostOrder());
        assertEquals("765", fifthTest.printPostOrder());
        assertEquals("13254", testTree.printPostOrder());
        assertEquals("687109", secondTest.printPostOrder());
    }

    @Test
    public void testPrintPreOrder() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node secondTest = new Node(9, new Node(7, new Node(6), new Node(8)), new Node(10));
        Node thirdTest = new Node(4);
        Node fourthTest = new Node(4, new Node(5), new Node(3));
        Node fifthTest = new Node(5, new Node(6, new Node(7)));

        assertEquals("4", thirdTest.printPreOrder());
        assertEquals("453", fourthTest.printPreOrder());
        assertEquals("567", fifthTest.printPreOrder());
        assertEquals("42135", testTree.printPreOrder());
        assertEquals("976810", secondTest.printPreOrder());
    }

    @Test
    public void testLevelOrder() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node secondTest = new Node(9, new Node(7, new Node(6), new Node(8)), new Node(10));
        Node thirdTest = new Node(4);
        Node fourthTest = new Node(4, new Node(5), new Node(3));
        Node fifthTest = new Node(5, new Node(6, new Node(7)));

        assertEquals("4", thirdTest.printLevelOrder());
        assertEquals("453", fourthTest.printLevelOrder());
        assertEquals("567", fifthTest.printLevelOrder());
        assertEquals("42513", testTree.printLevelOrder());
        assertEquals("971068", secondTest.printLevelOrder());
    }

    @Test
    public void testCountLeaves() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node secondTest = new Node(9, new Node(7, new Node(6), new Node(8)), new Node(10));
        Node thirdTest = new Node(4);
        Node fourthTest = new Node(4, new Node(5), new Node(3));
        Node fifthTest = new Node(5, new Node(6, new Node(7)));

        assertEquals(1, thirdTest.countLeaves());
        assertEquals(2, fourthTest.countLeaves());
        assertEquals(1, fifthTest.countLeaves());
        assertEquals(3, testTree.countLeaves());
        assertEquals(3, secondTest.countLeaves());
    }

    @Test
    public void testRoottoLeaf() {
        Node testTree = new Node(5, new Node(4, new Node(11, new Node(7), new Node(2))),
                new Node(8, new Node(13), new Node(4)));

        assertTrue(testTree.rootoleaf(27));
        assertTrue(testTree.rootoleaf(22));
        assertTrue(testTree.rootoleaf(26));
        assertTrue(testTree.rootoleaf(17));
        assertFalse(testTree.rootoleaf(18));
    }

    @Test
    public void testMirror() {
        Node root = new Node(5, new Node(3), new Node(7));
        Node rootMirror = new Node(5, new Node(7), new Node(3));

        rootMirror.mirror();
        assertEquals(root.printInorder(), rootMirror.printInorder());

        // Pl check this test
        Node root1 = new Node(5, new Node(3, new Node(1), new Node(2)), new Node(7));
        Node root1Mirror = new Node(5, new Node(7), new Node(3, new Node(2), new Node(1)));
        root1Mirror.mirror();
        assertEquals(root1.printInorder(), root1Mirror.printInorder());
    }

    @Test
    public void testDoubleTree() {
        Node testTree = new Node(5);
        Node secondTree = new Node(5, new Node(3, new Node(1), new Node(2)), new Node(7));
        Node thirdTree = new Node(2, new Node(1), new Node(3));

        assertEquals("5", testTree.printLevelOrder());
        testTree.doubleTree();
        assertEquals("55", testTree.printLevelOrder());

        assertEquals("53712", secondTree.printLevelOrder());
        secondTree.doubleTree();
        assertEquals("5573732121", secondTree.printLevelOrder());

        assertEquals("213", thirdTree.printLevelOrder());
        thirdTree.doubleTree();
        assertEquals("223131", thirdTree.printLevelOrder());
    }

    @Test
    public void testSame() {
        Node root = new Node(5);
        assertEquals(false, root.sameTree(null));
        assertEquals(true, root.sameTree(root));
        assertEquals(true, root.sameTree(new Node(5)));

        Node testTree = new Node(2, new Node(1), new Node(3));
        assertEquals(true, testTree.sameTree(testTree));
    }

    @Test
    public void testPrintPaths() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        String[] paths = { "421", "423", "45" };
        assertArrayEquals(paths, testTree.printPaths());
    }

    @Test
    public void testTreeToList() {
        Node testTree = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node.treeToList(testTree);
        assertEquals(4, testTree.value);
        assertEquals(5, testTree.right.value);
        assertEquals(1, testTree.right.right.value);
        assertEquals(2, testTree.right.right.right.value);
        assertEquals(3, testTree.right.right.right.right.value);
        assertEquals(4, testTree.right.right.right.right.right.value);

        assertEquals(3, testTree.left.value);
        assertEquals(2, testTree.left.left.value);
        assertEquals(1, testTree.left.left.left.value);
        assertEquals(5, testTree.left.left.left.left.value);
        assertEquals(4, testTree.left.left.left.left.left.value);

    }
}
