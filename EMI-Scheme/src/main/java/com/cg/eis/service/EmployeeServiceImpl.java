package com.cg.eis.service;

import java.util.HashMap;
import java.util.Map;

import com.cg.eis.bean.Employee;

public class EmployeeServiceImpl implements IEmployeeService {
	
	public Map<Integer,Employee> insertEmployee(Map<Integer,Employee> map ,Employee e) {
		map.put(e.getId(), e);
		return map;
	}
	
	public Map<Integer,Employee> getDetailsSchemeBased(Map<Integer,Employee> map,String str){
		Map<Integer,Employee> filtered = new HashMap<Integer,Employee>();
		for(Map.Entry<Integer,Employee> m : map.entrySet()){ 
			int key = m.getKey();
		    Employee e = m.getValue();
		    if(e.getInsuranceScheme().equals(str)) {
		    	filtered.put(key,e);
		    }else continue;
	    } 
		return filtered;
	}
 
	public boolean deleteEmployee(Map<Integer,Employee> map , int id) {
		if(map.containsKey(id)) {
			map.remove(id);
			return true;
		}else return false;
		
	}
}
