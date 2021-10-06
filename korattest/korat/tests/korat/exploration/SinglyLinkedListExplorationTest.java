package korat.exploration;

public class SinglyLinkedListExplorationTest extends BaseExplorationTest {

    public void testSinglyLinkedList() throws Exception {

        // results for repOk-Method
        String cmdLine = "-c korat.examples.singlylinkedlist.SinglyLinkedList -a 1,1,2,0 -r repOK";
        doTestForAllConfigs(cmdLine, 1, 6);        

        // results for negated repOk-Method
        cmdLine = "-c korat.examples.singlylinkedlist.SinglyLinkedList -n finSinglyLinkedList -a 0,0,1,0 -r negatedRepOK";
        doTestForAllConfigs(cmdLine, 2, 3);
        
        cmdLine = "-c korat.examples.singlylinkedlist.SinglyLinkedList -n finSinglyLinkedList -a 1,1,2,0 -r negatedRepOK";
        doTestForAllConfigs(cmdLine, 5, 6);

        cmdLine = "-c korat.examples.singlylinkedlist.SinglyLinkedList -n finSinglyLinkedList -a 2,2,3,0 -r negatedRepOK";
        doTestForAllConfigs(cmdLine, 9, 10);

    }
}