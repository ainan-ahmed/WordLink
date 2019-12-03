package app;
public class DictionaryNode{
    //object of the class represents a single word
    protected String word; // word to be stored
    private int level; // level of the word
    private DictionaryNode next;
    private boolean visited;
    static  String[] level1= new String[1001];
    static  String[] level2= new String[1001];
    static  int id1=0,id2=0;    
    protected DictionaryNode(String _word, int _level) {
    //add your implementation for the constructor
        word = _word;
        level = _level;
        next = null;
        visited = false;
    }
    

    protected  DictionaryNode insert(DictionaryNode head, String s1, int lvl) {
         DictionaryNode xxx= head;
       
         if(s1.compareTo(xxx.word) <= 0) { 
            DictionaryNode newNode = new DictionaryNode(s1, lvl);
            newNode.next = xxx;
            return newNode;
         }

        while(xxx.next != null) {
            //System.out.print(xxx.word+"-> ");
            if(s1.compareTo(xxx.word) > 0)
                xxx= xxx.next;
            else {
                DictionaryNode newNode = new DictionaryNode(s1, lvl);
                
                
                newNode.next = xxx.next;
                String temp = newNode.word;
                int tempLevel = newNode.level;
                newNode.word = xxx.word;
                newNode.level = xxx.level;
                xxx.word = temp;
                xxx.level = tempLevel;
                xxx.next = newNode;
                
                return head;
                
            }
        }
        
        xxx.next = new DictionaryNode(s1, lvl);
        
        return head;
    
    }
    protected void collectAll(DictionaryNode head) {
        DictionaryNode ffff = head;
         while(ffff != null)
         {
             
            if(ffff.level == 1){
                level1[id1++] = ffff.word;
            }
            else
                level2[id2++] = ffff.word;

                ffff = ffff.next;
         }

        //System.out.println("\n\n\n\n");
    }
    protected boolean checkIfExists(DictionaryNode head , String word)
    {
        DictionaryNode temp = head;
        while(temp!=null)
        {
            if(temp.word.equals(word))
                return true;
            temp= temp.next;    
        }
        return false;
    }
    protected int  searchWord(DictionaryNode head,String word,int lvl) {
        DictionaryNode temp = head;
        while(temp!=null)
        {
             
            if(temp.word.equals(word) && temp.level <= lvl)
            {
                if(temp.visited == true)
                    return 2;
                else{
                    temp.visited = true;        
                    return 1;
                }
            }
            temp= temp.next;    
        }
        return 0;
    }

    protected String predictedWord(DictionaryNode head, int lvl) {
        DictionaryNode temp = head;
        while(temp != null) {
            if(temp.visited == false && temp.level <= lvl) {
                temp.visited = true;
                return temp.word;
            }
            temp = temp.next;
        }
        return "-1";
    }
    protected void cleanNodes(DictionaryNode head)
    {
        DictionaryNode temp = head;
        while(temp != null) {
                temp.visited = false;
                temp = temp.next;
        }
    }
    
     
//add any other attributes or methods if needed

}