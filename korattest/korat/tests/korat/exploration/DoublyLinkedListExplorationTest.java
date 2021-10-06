package korat.exploration;

public class DoublyLinkedListExplorationTest extends BaseExplorationTest {

    public void testDoublyLinkedList() throws Exception {

        // results for repOk-Method
        String cmdLine = "-c korat.examples.doublylinkedlist.DoublyLinkedList -a 1,1,1,0 -r repOK";
        doTestForAllConfigs(cmdLine, 1, 4);        

        // results for negated repOk-Method
        cmdLine = "-c korat.examples.doublylinkedlist.DoublyLinkedList -a 1,1,1,0 -r negatedRepOK";
        doTestForAllConfigs(cmdLine, 4, 5);

        cmdLine = "-c korat.examples.doublylinkedlist.DoublyLinkedList -a 2,2,2,0 -r negatedRepOK";
        doTestForAllConfigs(cmdLine, 49, 50);

        cmdLine = "-c korat.examples.doublylinkedlist.DoublyLinkedList -a 3,3,3,0 -r negatedRepOK";
        doTestForAllConfigs(cmdLine, 865, 866);
    }
}