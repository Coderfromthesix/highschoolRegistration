package DaniilBacalear;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Created by Daniil.Bacalear on 2017-11-09.
 */
public class File {
    private String path;
    private boolean addToOldFile = false;
    public File (String path,Boolean addToOldFile){
        this.path = path;
        this.addToOldFile = addToOldFile;
    }
    public void writeToFile(String text)throws IOException{
        FileWriter write = new FileWriter(path,addToOldFile);
        PrintWriter x = new PrintWriter(write);
        x.printf("%s" + "%n",text);
        x.close();
    }
    public void deleteLineInFile(String studentNumber)throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader c = new BufferedReader(fr);
        FileWriter writeToTemp = new FileWriter("src/temp.txt");
        PrintWriter temp = new PrintWriter(writeToTemp);
        String line;
        String lineNoWhiteSpaces;
        while(true){
            line = c.readLine();
            if(line==null) break;
            lineNoWhiteSpaces = line.replaceAll("\\s+","");
            lineNoWhiteSpaces = lineNoWhiteSpaces.toLowerCase();
            if(!lineNoWhiteSpaces.endsWith("studentnumber:" + studentNumber)){
                temp.printf("%s" + "%n",line);
            }
        }
        temp.close();
        c.close();
        FileReader fr2 = new FileReader("src/temp.txt");
        BufferedReader c2 = new BufferedReader(fr2);
        FileWriter replacement= new FileWriter(path);
        PrintWriter replace = new PrintWriter(replacement);
        String line2;
        while(true){
            line2 = c2.readLine();
            if(line2==null) break;
            replace.printf("%s" + "%n",line2);
        }
        replace.close();
        c2.close();
    }
    public void eraseFileContent(String fileName)throws IOException{
        FileWriter write = new FileWriter(path);
        PrintWriter x = new PrintWriter(write);
        x.print("");
        x.close();
    }
    public int lineCount()throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader c = new BufferedReader(fr);
        int lineCounter = 0;
        String line;
        while(true){
            line = c.readLine();
            if(line!=null) lineCounter ++;
            else break;
        }
        c.close();
        return lineCounter;
    }
    public String fileGetLine(int line)throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader c = new BufferedReader(fr);
        String storage;
        String stringAtLine ="";
        for(int i = 1; i <= line;i++){
            if(lineCount()==0) break;
            storage = c.readLine();
            if(i==line){
                stringAtLine = storage;
                break;
            }
        }
        return stringAtLine;
    }
}

