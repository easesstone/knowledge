import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class CompareSuite {

    public static void main(String[] args) {
        // write your code here
        System.out.println("arg[0]:old suite list file");
        System.out.println("arg[1]:new suite list file");
        System.out.println("arg[2]:output suite list file");
        if(args.length==3) {
            compareSuites(args[0], args[1], args[2]);
        } else {
            compareSuites("suite_0.txt", "suites.txt", "suite_00.txt");
        }
    }

    private static void compareSuites(String oldFile, String newFile, String outputFile){
        // compare the suites
        System.out.println("old suite list file:"+ oldFile);
        System.out.println("new suite list file:"+ newFile);
        System.out.println("output suite list file:"+ outputFile);
        // read file line by line
        try {
            if(!isFileExists(oldFile)) return;
            if(!isFileExists(newFile)) return;
            File fin = new File(oldFile);
            // Construct BufferedReader from FileReader
            BufferedReader br = new BufferedReader(new FileReader(fin));
            Set<String> allSuites = new HashSet<String>();
            String line = null;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                allSuites.add(line);
            }
            br.close();

            // read file line by line
            PrintWriter writer = null;
            File fin1 = new File(newFile);
            // Construct BufferedReader from FileReader
            BufferedReader br1 = new BufferedReader(new FileReader(fin1));
            line = null;
            while ((line = br1.readLine()) != null) {
                //System.out.println(line);
                if (!allSuites.contains(line)) {
                    if(writer==null) writer = new PrintWriter(outputFile, "UTF-8");
                    writer.println(line);
                } else{
                    //System.out.println("suite existed");
                }
            }
            br1.close();
            if(writer!=null) writer.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static boolean isFileExists(String path){
        try{
            File f = new File(path);
            boolean exists = f.exists();
            if(!exists) System.out.println("File:" + path +" does not exist!");
            return exists;
        }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
            return false;
        }
    }
}
