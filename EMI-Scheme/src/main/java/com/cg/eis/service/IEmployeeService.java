package com.cg.eis.service;

import java.util.*;
import com.cg.eis.bean.Employee;

public interface IEmployeeService {
     public Map<Integer,Employee> insertEmployee(Map<Integer,Employee> map , Employee e);
     public Map<Integer,Employee> getDetailsSchemeBased(Map<Integer,Employee> map,String str);
     public boolean deleteEmployee(Map<Integer,Employee> map ,int id);
     
}
