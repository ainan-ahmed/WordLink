package app;
public class ListOfNodes{
    
    private DictionaryNode head = null; //head of the linked list
    ListOfNodes()
    {
        head=null;
    }
    
    protected void storeWord(String word,int level)
    {
         
            if(head == null) {
                head = new DictionaryNode(word, level);
            }
            else{
                
                head = head.insert(head, word, level);
            }   
            // print();
    }

    protected boolean checkWord(String word){
         if(head.checkIfExists(head, word))
            return true;
         return false;   
    }
    protected int search(String word,int level) {
        int ans = head.searchWord(head, word,level);
        return ans;
    }
    protected void collect(){
        head.collectAll(head);
    }

    protected String getWord(int lvl) {
        return head.predictedWord(head, lvl);
    }
     
    protected void clearVisited()
    {
        head.cleanNodes(head);
    }
    



}