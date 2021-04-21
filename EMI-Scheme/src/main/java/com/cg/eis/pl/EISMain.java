package com.cg.eis.pl;

import java.util.HashMap;
import java.util.Map;

import com.cg.eis.service.*;
import com.cg.eis.bean.Employee;
import java.util.Scanner;

public class EISMain {
	IEmployeeService empService;
	
	public EISMain() {
		empService = new EmployeeServiceImpl();
	}
	
    public static void main(String[] args) {
		EISMain obj = new EISMain();
		Map<Integer,Employee> map = new HashMap<Integer,Employee>();
		Employee e1 = new Employee(101,"Red",50000,"Manager","Scheme A");
		Employee e2 = new Employee(356,"Chuck",10000,"System Associate","Scheme C");
		Employee e3 = new Employee(643,"Stella",35000,"Programmer","Scheme B");
		
		map.put(e3.getId(), e3);
		map.put(e2.getId(), e2);
		map.put(e1.getId(), e1);
		
		/*System.out.println("Initial Employee Details");
		obj.displayDetails(map);*/
		
		boolean status = true;
		while(status) {
			System.out.println("");
			System.out.println("-------Services---------");
			System.out.println("1. Insert new Employee");
			System.out.println("2. Delete Employee record");
			System.out.println("3. Get Employee details by Scheme");
			System.out.println("4. Exit");
			System.out.println("Enter an option");
			Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            if(option == 4) {
            	System.out.println("Thank you for visit");
            	status = false;
            	break;
            }else {
            	switch(option) {
            	case 1:
            		map = obj.insertEmp(map);
            		obj.displayDetails(map);
            		break;
            	case 2:
            		map = obj.deleteEmp(map);
            		obj.displayDetails(map);
            		System.out.println("Deleted!");
            		break;
            	case 3:
            		Map<Integer,Employee> filtered = obj.filteredEmp(map);
            		obj.displayDetails(filtered);
            		break;
            	}
            }
		}
		
	}
    
    public void displayDetails(Map<Integer,Employee> map) {
    	for(Map.Entry<Integer, Employee> entry:map.entrySet()){    
            int key=entry.getKey();  
            Employee e=entry.getValue();
            System.out.println("-------------------------------------------");
            System.out.println( "ID:" + key + " Details:");  
            System.out.println("Name :" +e.getName()+"\nSalary :"+e.getSalary()+"\nDesignation :"+e.getDesignation()+"\nInsurance Scheme :"+e.getInsuranceScheme());   
        }    
    }
    
    public Map<Integer,Employee> insertEmp(Map<Integer,Employee> map) {
    	Scanner sc1 = new Scanner(System.in);
    	System.out.println("Enter the Id :");
    	int id = sc1.nextInt();
    	System.out.println("Enter the Name :");
    	String name = sc1.next();
    	System.out.println("Enter the Salary :");
    	int salary = sc1.nextInt();
    	String designation = new String();
    	String iScheme = new String();
    	if(salary<5000 && salary>20000) {
    		designation = "System Associate";
    		iScheme = "Scheme C";
    	}else if(salary>=20000 && salary<40000) {
    		designation =  "Programmer";
    		iScheme = "Scheme B";
    	}else if(salary >= 40000) {
    		designation =  "Manager";
    		iScheme = "Scheme A";
    	}else {
    		designation =  "Clerk";
    		iScheme = "No Scheme";
    	}
    	
    	Employee e = new Employee(id,name,salary,designation,iScheme);
    	map = empService.insertEmployee(map, e);
        return map;
    	
    }
    
    public Map<Integer,Employee> deleteEmp(Map<Integer,Employee> map){
    	System.out.println("Enter the ID of Employee:");
    	Scanner sc2 = new Scanner(System.in);
    	int id = sc2.nextInt();
    	if(empService.deleteEmployee(map, id)) {
    		return map;
    	}else {
    		System.out.println("No match for ID found!");
    		return map;
    	}
    	
    }
    
    public Map<Integer,Employee> filteredEmp(Map<Integer,Employee> map){
    	
    	System.out.println("----Scheme Options-----");
    	System.out.println("1. A\n2. B\n3. C\n4. None");
    	System.out.println("Enter an option :");
    	Scanner sc3 = new Scanner(System.in);
    	int option = sc3.nextInt();
    	Map<Integer,Employee> result = new HashMap<Integer,Employee>();
    	switch(option) {
    	case 1:
    		result = empService.getDetailsSchemeBased(map,"Scheme A");
    		break;
    	case 2:
    		result = empService.getDetailsSchemeBased(map,"Scheme B");
    		break;
    	case 3:
    		result = empService.getDetailsSchemeBased(map,"Scheme C");
    		break;
    	case 4:
    		result = empService.getDetailsSchemeBased(map,"No Scheme");
    		break;
    	}
    	return result;
    }
}
