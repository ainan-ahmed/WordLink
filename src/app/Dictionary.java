package app;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
import java.util.Scanner;

public class Dictionary {
     static Scanner myScanner = new Scanner(System.in);
     static String[] wordArrayL1 = new String [1001];
     static String [] wordArrayL2 = new String[1001];
     static int wordArrayIdx1=0;
     static int  wordArrayIdx2 = 0;
    static int difficultyLevel = 1;
    
    public static void main(String[] args) throws IOException {
        
        FileReader file = new FileReader("dictionary.txt");
        
        Scanner fileScan = new Scanner(file);
        ListOfNodes[] data = new ListOfNodes[27];
        //creating object for all the alphabet
        for (int i = 0; i < 27; i++) {
            data[i] = new ListOfNodes();
        }
        boolean l1=false,l2=false;
        //reading words from dictionary.txt and saving them in dictionary
        while(fileScan.hasNext())
        {
           String tmp = fileScan.next();
         //  if(tmp.equals("toy"))
         //  System.out.println("\n\n------------------found toy--------------\n\n---------"+l1+"  \n");
           //System.out.println(tmp);
           
            if(tmp.equals("1"))
            {
                l1=true;
            }
            else if(tmp.equals("2"))
            {
                l2=true;
            }
            else if(l2)
            {
                 //level2
                 wordArrayL2[wordArrayIdx2++] = tmp;
                 int pos = tmp.charAt(0)-'a';  
                 add(data[pos], tmp,2);
            }
            else{
                 
                int pos = tmp.charAt(0)-'a';  
                wordArrayL1[wordArrayIdx1++] = tmp;
                 add(data[pos], tmp,1);
            }
        }
        file.close();
        fileScan.close();



        //Menu
        while(true)
        {
            System.out.println("\tWordLink\n");
            System.out.println("\t\tA. set the difficulty level\n\t\tB. display the dictionary");
            System.out.println("\t\tC. insert a word to the dictionary\n\t\tD. play the game\n\t\tE. exit\n\t\tF. play the game (2 players)\n");
            System.out.print("\tSelect a function from the menu: ");
            switch(myScanner.next().toUpperCase())
            {
                case "A":
                    System.out.println("\nSet the difficulty level\n");
                    System.out.print("The current difficulty level is "+difficultyLevel+". Type the new level: ");
                    int tmp = myScanner.nextInt();
                    if(tmp >2)
                    {
                        System.out.println("Wrong input.Please choose again.\n");
                        break;
                    }
                    if(tmp != difficultyLevel)
                    {
                        System.out.println("\nThe difficulty level has now been set as "+tmp+".\n");
                        difficultyLevel = tmp;
                    }
                    else{
                        System.out.println("\nDifficulty level is already set to "+tmp+".\n");
                    }
                    break;
                case "B":
                    printDictionary(data);
                    //System.out.println("B is chosen");
                    break;
                case "C":
                    insertNewWord(data);
                    break;
                case "D":
                    playGame(data);
                    clear(data);    
                    System.out.println("\nPress ENTER  to continue");
                    System.in.read();
                    break;
                case "E":
                    System.out.println("\nUpdating dictionary.txt ... Bye");
                    updateDictionary();
                    return;
                case "F":
                    playGame2P(data);
                    clear(data);
                    break;    
                default:
                    System.out.println("\nWrong Charecter entered.Please choose again.\n");
                     
                    
            }
        }
    }


    //method for adding existing words to the dictionary
    private static void add(ListOfNodes data ,String word,int level) {
           
        data.storeWord(word,level);
         
    }
    //printing all the words saved in the dictionary
    private  static void printDictionary(ListOfNodes[] data) throws IOException {

        for (int i = 0; i < 26; i++) {

            data[i].collect();

        }

        System.out.println("\n\tDisplay the dictionary");
        System.out.println("\nLevel 1");
        int cnt = 0;
        for (int i = 0; i < DictionaryNode.id1; i++) {
            System.out.print(DictionaryNode.level1[i]);
            ++cnt;
            if (cnt % 7 == 0) {
                System.out.println();
            } else
                System.out.print(" ");
            if (cnt == 35) {
                System.out.println("\npress ENTER  to continue");
                System.in.read();
                cnt =0;

            }    
        }
       // System.out.println("--------------------"+sum+"\n\n");
        System.out.println("\n\npress ENTER to continue");
        System.in.read();
        System.out.println("\nLevel 2");
        cnt=0;
        for (int i = 0; i < DictionaryNode.id2; i++) {
            System.out.print(DictionaryNode.level2[i]);
            ++cnt;
            if(cnt %7 ==0)
            {
                 System.out.println();
            }
            else
                System.out.print(" ");
            if(cnt == 35)
            {
                System.out.println("\n\npress ENTER to continue");
                System.in.read();
                cnt =0;

            }    
        }
        
        
        DictionaryNode.level1 = new String[1001];
        DictionaryNode.level2 = new String[1001];
        DictionaryNode.id1=DictionaryNode.id2=0;
        System.out.println("\n");

    }

    // method for inserting a new word
    private static  void insertNewWord(ListOfNodes[] data)
    {
        System.out.println("\nInsert a word to the dictionary\n"); 
        System.out.println("Enter the word: ");
        String word = myScanner.next();
        System.out.println("Difficulty level: ");
        int level = myScanner.nextInt();
        System.out.println("");
        int pos = word.charAt(0)-'a';
        if(data[pos].checkWord(word))
            System.out.println("\""+word+"\" exists in the dictionary. Insertion aborted.");
        else{
            if(level>2)
            {
                System.out.println("Wrong input.\n");
                return;
            }
            if(level ==2)
                wordArrayL2[wordArrayIdx2++] = word;
            else
                wordArrayL1[wordArrayIdx1++] = word;
            data[pos].storeWord(word, level);
            System.out.println("\""+word+"\" is inserted.\n");
        }    

    }
    
    private static void playGame(ListOfNodes[] data)
    {
        System.out.println("\nplay the game (Level "+difficultyLevel+")\n");
        System.out.print("Enter a word: ");
        String word = myScanner.next();
        if(!validate(data,word,-1))
        {
                return;
        }
        //System.out.println("adsfasd");
        String[] gameWords = new String[101];
        int idx = 0;
        gameWords[idx++] = word;
        while(true)
        {
            //System.out.println("zzzzzzzzzzzz");
            int pos = word.charAt(word.length()-1) -'a';
            String predictedWord = data[pos].getWord(difficultyLevel);
           // System.out.println(predictedWord);
            if(predictedWord.equals("-1")) {
                System.out.println("Well done! You win.");
                break;
            }
            else {
                gameWords[idx++] = predictedWord;
            }
            for(int i = 0; i < idx; i++) {
                System.out.print(gameWords[i]+" - ");
            }
            word = myScanner.next();
            if(!validate(data,word,predictedWord.charAt(predictedWord.length()-1)-'a'))
            {
                    return;
            }
            gameWords[idx++] = word;
            System.out.println();
            
        }
        
    }
    private static void playGame2P(ListOfNodes[] data)
    {
        System.out.println("\nplay the game (2 Players - Level "+difficultyLevel+")\n");
        System.out.print("P1: Enter a word: ");
        String word = myScanner.next();

        int player = 1;
        String prevWord = "a";
        if(!validate2P(data,word,player,-1))
        {
                return;
        }
        //System.out.println("adsfasd");
        String[] gameWords = new String[101];
        int idx = 0;
        gameWords[idx++] = word;
        player= player==1?2:1;
        prevWord = word;
        while(true)
        {
            System.out.print("P"+player+": - ");
            for(int i = 0; i < idx; i++) {
                System.out.print(gameWords[i]+" - ");
            }
            word = myScanner.next();
            if(!validate2P(data,word,player,prevWord.charAt(prevWord.length()-1)-'a'))
            {
                    return;
            }
            prevWord = word;
            gameWords[idx++] = word;
            //System.out.println();
            player= player==1?2:1;

        }
        
    }
    private static boolean validate(ListOfNodes[] data,String word,int prev){
        int pos = word.charAt(0)-'a';
        if(prev!=-1 && pos!=prev)
        {
            System.out.println("Incorrect word entered. you didn't win.\n");
            return false;

        }
        int ans = data[pos].search(word,difficultyLevel);
        if(ans ==0)
        {
            System.out.println("\""+word+"\" doesn’t exist in the dictionary. You didn’t win.");
            return false;
        }
        else if(ans ==1)
        {
             return true;   
        }
        else{
            System.out.println("Duplicate word.You didn't win.");
            return false;
        }
    }
    private static boolean validate2P(ListOfNodes[] data,String word,int p,int prev){
        int pos = word.charAt(0)-'a';
        String nextPlayer = p==1?"two":"one";
        if(prev!= -1&& pos!=prev)
        {
            System.out.println("Incorrect word entered.");
            System.out.println("--Player "+nextPlayer+" wins!--\n");
            return false;
        }
        int ans = data[pos].search(word,difficultyLevel);
        if(ans ==0)
        {
            System.out.println("\""+word+"\" isn't in the level "+difficultyLevel+" dictionary!\n");
            System.out.println("--Player "+nextPlayer+" wins!--\n");
            return false;
        }
        else if(ans ==1)
        {
             return true;   
        }
        else{
            System.out.println("Duplicate word entered\n");
            System.out.println("--Player "+nextPlayer+" wins!--\n");
            return false;
        }
    }
    private static void clear(ListOfNodes[] data) {
        for (int i = 0; i < 26; i++) {
            data[i].clearVisited();
        }
    }
    private static void updateDictionary()
    {
        FileWriter writer;
        try {
            writer = new FileWriter("dictionary.txt");
            writer.write("1\n");
           
            for (int i = 0; i <wordArrayIdx1 ; i++) {
                writer.write(wordArrayL1[i]+" ");
            }
            writer.write("\n2\n");

            for (int i = 0; i <wordArrayIdx2 ; i++) {
                writer.write(wordArrayL2[i]+" ");
            }
            writer.write("\n");
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}