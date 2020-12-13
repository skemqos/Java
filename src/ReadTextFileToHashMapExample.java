import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
 
public class ReadTextFileToHashMapExample {
 
    /*** Change this - replace file path ****/
    final static String filePath = "C:\\Auto\\Java\\text-values.txt";
    
    public static void main(String[] args) {
        
        //read text file to HashMap
        Map<String, Integer> mapFromFile = getHashMapFromTextFile();
        
        //iterate over HashMap entries
        for(Map.Entry<String, Integer> entry : mapFromFile.entrySet()){
            System.out.println( entry.getKey() + " => " + entry.getValue() );
        }
        
    }
    
    public static Map<String, Integer> getHashMapFromTextFile(){
        
        Map<String, Integer> mapFileContents = new HashMap<String, Integer>();
        BufferedReader br = null;
        
        try{
            
            //create file object
            File file = new File(filePath);
            FileReader fr = new FileReader(file);
            //create BufferedReader object from the File
            br = new BufferedReader(fr);
            
            String line = null;
            
            //read file line by line
            while ( (line = br.readLine()) != null ){
                
                //split the line by :
                String[] parts = line.split(":");
                
                //first part is name, second is age
                String name = parts[0].trim();
                Integer age = Integer.parseInt( parts[1].trim() );
                
                //put name, age in HashMap if they are not empty
                if( !name.equals("") && !age.equals("") )
                    mapFileContents.put(name, age);
            }
                        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            
            //Always close the BufferedReader
            if(br != null){
                try { 
                    br.close(); 
                }catch(Exception e){};
            }
        }        
        
        return mapFileContents;
        
    }
}