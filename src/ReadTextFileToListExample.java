import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadTextFileToListExample{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
        //read text file to List
        List<String> list = getListFromTextFile();
        for(String s: list){
            System.out.println(s);}
           
        Set<String> set = setExample();
        System.out.println("\nPrinting Set Elements");
        for (String s : set) {
        	System.out.println(s);
        }
            
		/*
		 * List<String> list2 = getListFromTextFile2(); for(String s: list2){
		 * System.out.println(s);}
		 */
}
	
	  private static Set<String> setExample() throws IOException {
		  Set<String> set = new HashSet<String>();
		  
		  File fip = new File("C:\\Auto\\Java\\text-values.txt");
		  FileReader fr = new FileReader(fip);
		  BufferedReader br = new BufferedReader(fr);
		  String line = null;
		  while( (line = br.readLine()) != null) {
			  //list.add(line);
			  set.add(line);
		  }
		  return set; 
	}

	private static List<String> getListFromTextFile2() throws IOException { 
	  // TODO Auto-generated method stub 
	  List<String> list = new ArrayList<>();
	  
	  File fip = new File("C:\\Auto\\Java\\text-values.txt");
	  FileReader fr = new FileReader(fip);
	  BufferedReader br = new BufferedReader(fr);
	  String line = null;
	  while( (line = br.readLine()) != null) {
		  list.add(line);
	  }
	  
	  return list; }
	 

	private static List<String> getListFromTextFile() throws IOException {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		
		Files.lines(Paths.get("C:/Auto/Java/text-values.txt")).forEach(line -> list.add(line));
		
		return list;
	}
}
