package korat.exploration;

/**
 * Test for korat.examples.binheap.BinomialHeap example
 * 
 * @author Aleksandar Milicevic <aca.milicevic@gmail.com>
 */
public class BinomialHeapExplorationTest extends BaseExplorationTest {

    public void testBinomialHeap() throws Exception {

        String cmdLine = "-c korat.examples.binheap.BinomialHeap -a 7";
        doTestForAllConfigs(cmdLine, 107416, -1);

        // results for negated repOk-Method
        cmdLine = "-c korat.examples.binheap.BinomialHeap -r negatedRepOK -a 1";
        doTestForAllConfigs(cmdLine, 32, 34);
    }

}
