package korat.exploration;

/**
 * Test for korat.examples.binarytree.BinaryTree example.
 * 
 * @author Aleksandar Milicevic <aca.milicevic@gmail.com>
 * 
 */
public class BinaryTreeExplorationTest extends BaseExplorationTest {

    public void testBinaryTree() throws Exception {

        // results for positive repOk-Method
        String cmdLine = "-c korat.examples.binarytree.BinaryTree -r repOK -a 1";
        doTestForAllConfigs(cmdLine, 1, 4);

        cmdLine = "-c korat.examples.binarytree.BinaryTree -r repOK -a 2";
        doTestForAllConfigs(cmdLine, 2, 16);

        cmdLine = "-c korat.examples.binarytree.BinaryTree -r repOK -a 3";
        doTestForAllConfigs(cmdLine, 5, 63);
        
        // results for negated repOk-Method
        cmdLine = "-c korat.examples.binarytree.BinaryTree -r negatedRepOK -a 1";
        doTestForAllConfigs(cmdLine, 4, 5);

        cmdLine = "-c korat.examples.binarytree.BinaryTree -r negatedRepOK -a 2";
        doTestForAllConfigs(cmdLine, 48, 50);

        cmdLine = "-c korat.examples.binarytree.BinaryTree -r negatedRepOK -a 3";
        doTestForAllConfigs(cmdLine, 861, 866);
    }
}
