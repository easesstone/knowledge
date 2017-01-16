import java.io.*;
import java.lang.reflect.Array;

/**
 * Created by my on 2017/1/12.
 */
public class Comare {
    public static void main(String[] args) {
//        String lessPath = args[1];
//        String morePath = args[2];
//        String outPath = args[3];

       // outFile("f:\\test\\t.txt","f:\\test\\t2.txt","f:\\test\\t3.txt");
        outFile(args[0], args[1], args[2]);
    }

    //    String[]写入指定文件中
    public static void outFile(String lessPath,String morePath,String outPath){
//        比较文件差异
        String[] less = readFileByLines(lessPath);
        String[] more = readFileByLines(morePath);
        String[] out = more;
        for (int j = 0; j <less.length ; j++) {
            for (int i = 0; i <more.length ; i++) {
                if(less[j].equals(more[i])){
                    System.out.println("文件2包含文件1中的元素："+less[j]);
                    out[i] = "";
                }
            }
        }

//        如果文件已经有内容则删除新建。
        File outFile = new File(outPath);
        if(outFile.exists()){
            outFile.delete();
        }

        if(!outFile.exists()){
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream outS= null;
        try {
            outS = new FileOutputStream(outFile,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i=0;i<out.length;i++){
            StringBuffer sb=new StringBuffer();
            sb.append(out[i]+"\r\n");
            try {
                outS.write(sb.toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            outS.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    txt读成string[]
    private static String[] readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String ss[] = new String[0];
        int i = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                ss = insert(ss, tempString.trim());
                i++;
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ss;
    }

    /*
     * 插入到数组
     */
    private static String[] insert(String[] arr, String str) {
        int size = arr.length;
        String[] tmp = new String[size + 1];
        System.arraycopy(arr, 0, tmp, 0, size);
        tmp[size] = str;
        return tmp;
    }

}
