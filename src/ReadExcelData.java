import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelData {
	
	public Map <Integer,String> getStudentDetailMap() throws IOException {

		Map<Integer, String> map=new HashMap();
		
		//obtaining input bytes from a file  
				FileInputStream fis = new FileInputStream(new File("D:\\TestData\\StudentData.xls"));
		//creating workbook instance that refers to .xls file  
				HSSFWorkbook wb = new HSSFWorkbook(fis);
		//creating a Sheet object to retrieve the object  
				HSSFSheet sheet = wb.getSheetAt(0);
		//evaluating cell type   
				FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
				for (Row row : sheet) // iteration over row using for each loop
				{
					double key = row.getCell(0).getNumericCellValue();
					String value = row.getCell(1).getStringCellValue();
					map.put((new Double(key)).intValue(), value);
				}
				
				return map;
	}
	
	public List <String> getStudentDetails() throws IOException{
		List<String> list=new ArrayList();
		
		//obtaining input bytes from a file  
				FileInputStream fis = new FileInputStream(new File("D:\\TestData\\StudentData.xls"));
		//creating workbook instance that refers to .xls file  
				HSSFWorkbook wb = new HSSFWorkbook(fis);
		//creating a Sheet object to retrieve the object  
				HSSFSheet sheet = wb.getSheetAt(0);
		//evaluating cell type   
				FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
				for (Row row : sheet) // iteration over row using for each loop
				{
					for (Cell cell : row) // iteration over cell using for each loop
					{
						switch (cell.getCellType()) {
						case NUMERIC: // field that represents numeric cell type
		//getting the value of the cell as a number  
							//System.out.print(cell.getNumericCellValue() + "\t\t");
							break;
						case STRING: // field that represents string cell type
		//getting the value of the cell as a string  
							//System.out.print(cell.getStringCellValue() + "\t\t");
							list.add(cell.getStringCellValue());
							break;
						}
					}
				}
				return list;
	}
	
	public static void main(String args[]) throws IOException {
		ReadExcelData r = new ReadExcelData();
		List<StudentData> list = r.getStudentDataAsList();
		for (StudentData obj:list) {
			System.out.print("StudentId:"+obj.getStudentId());
			System.out.print(", StudentName:"+obj.getStudentName());
			System.out.print(", StudentDOB:"+obj.getMonthOfBirth());
			System.out.println(", StudentBloodGroup:a"+obj.getBloodGroup());
		}
		
		StudentData d = r.getStudentDataById(list, 3);
		if (d != null) {
		  System.out.println("Match found for Student Id:"+d.getStudentId());
		  System.out.println("Student Name="+d.getStudentName());
		  System.out.println("Student Month of Birth="+d.getMonthOfBirth());
		  System.out.println("Student Blood Group="+d.getBloodGroup());
		} else {
			  System.out.println("NO Match found for Student Id, Exiting.");
		}

		//Retrieve map values using entryset
		Map <Integer,String> map = r.getStudentDetailMap();
		for (Map.Entry<Integer,String> entry : map.entrySet()) {
		    System.out.print("Key: " + entry.getKey());
		    System.out.println(", Value: " + entry.getValue());
		}
		
		//Retrieve map values using keyset
		for (Integer i: map.keySet()) {
			System.out.print("Key="+i);
			System.out.println(",Value="+map.get(i));
		}
		
		//get values from HashMap and print
		Map<Integer, StudentData> map2 = r.getStudentDataMap();
		if (map2 !=null) {
			for(Entry<Integer, StudentData> s:map2.entrySet()) {
				System.out.print("StudentId="+s.getKey());
				System.out.print(", StudentName="+s.getValue().getStudentName());
				System.out.print(", StudentMOB="+s.getValue().getMonthOfBirth());
				System.out.println(", StudentBloogroup="+s.getValue().getBloodGroup());
			}
		} else {
			System.out.println("No Data in map, Exiting.");
		}
		
		if (map2.get(1) != null) {
			System.out.print("StudentId="+map2.get(1).getStudentId());
			System.out.print(", StudentName="+map2.get(1).getStudentName());
			System.out.print(", StudentMOB="+map2.get(1).getMonthOfBirth());
			System.out.println(", StudentBloogroup="+map2.get(1).getBloodGroup());
		}
	}
	
	public List<StudentData> getStudentDataAsList() throws IOException {
		List<StudentData> list=new ArrayList();
		//obtaining input bytes from a file  
				FileInputStream fis = new FileInputStream(new File("D:\\TestData\\StudentData.xls"));
		//creating workbook instance that refers to .xls file  
				HSSFWorkbook wb = new HSSFWorkbook(fis);
		//creating a Sheet object to retrieve the object  
				HSSFSheet sheet = wb.getSheetAt(0);
		//evaluating cell type   
				FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
				for (Row row: sheet) // iteration over row using for each loop
				{
					if(row != null) {
					list.add(new StudentData(new Double(row.getCell(0).getNumericCellValue()).intValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue()));
					}
				}
				return list;
	}
	
	public StudentData getStudentDataById(List<StudentData> list, int studentId) {
		ReadExcelData r = new ReadExcelData();
		for(StudentData d: list) {
			if (d.getStudentId() == studentId) {
				return d;
			} 
		}
		return null;
	}
	
	public Map<Integer, StudentData> getStudentDataMap() throws IOException{
		Map<Integer, StudentData> map = new HashMap();
		
		FileInputStream fis = new FileInputStream(new File("D:\\TestData\\StudentData.xls"));
	//creating workbook instance that refers to .xls file  
			HSSFWorkbook wb = new HSSFWorkbook(fis);
	//creating a Sheet object to retrieve the object  
			HSSFSheet sheet = wb.getSheetAt(0);
	//evaluating cell type   
			FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
			for (Row row: sheet) // iteration over row using for each loop
			{
				if(row != null) {
				map.put(new Double(row.getCell(0).getNumericCellValue()).intValue(), new StudentData(new Double(row.getCell(0).getNumericCellValue()).intValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue()));
				}
			}
		return map;
	}
}
