package com.utkal.supply.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class TestClass {

	
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/users")
	public String getMessage() {
		
		return "Hello Controller";

	}
	
	
	
	 public static void main(String[] args) throws Exception {
	   /*     //String date_s = "2011-01-18 00:00:00";
	        String date_s ="2016-11-04T18:30:00.000Z";
	        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
	        //SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:sssZ");
	        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        
	        Date date = dt.parse(date_s);
System.out.println(date);
	        // *** same for the format String below
	        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
	        System.out.println(dt1.format(date));*/
		 
		 System.out.println(getValues());
		
	 }
	 
	 public static int getMax(){
		 
		 int arr[] = {3,4,5};//5,6,7,8,9};
		 int temp[]={};
		 int result = -1;
		 List<Integer> list = new ArrayList<Integer>();
		  for (int i = 0; i < arr.length; i++) {
		    list.add(arr[i]);
		  }
		 int maxNum = Collections.max(list);
	    int sum=0;
		  for (int i = 0; i < arr.length; i++) {
			     for(int count=0;count <arr.length;count++){
			    	 if( i !=count){
			  sum = arr[i] + arr[count];
			  if(maxNum == sum){
				return sum;  
			  }else{
				 
				 
				  
			  }
			    	 }
			
			     }
			    
			  }
	 
	 
	 
	 return result;
	 }

	
	
	
	
	public static int getValues(){
		
		 int arr[] = {9,3,4,5};//5,6,7,8,9};
		 int temp[]={};
		 int result = -1;
		  int highest = 0;
		  int next=0;
		  int sum =0;
		  for (int i = 0; i < arr.length; i++) {
			 
			 if(highest <arr[i]){
				  highest =arr[i];
				  for(int count=0;count <arr.length;count++){
				    	 if( i !=count){
				  sum = arr[i] + arr[count];
				  System.out.println("I is "+ i+" count is "+ count +"  sum is "+ sum+"  hihest is "+ highest);
				  if(highest == sum){
					return sum;  
				  }
				    	 }
				
				     }
			 }else {
				 
				 for(int n : arr){
					 if(highest != n){
						 if(next <n){
							 highest=next;
						 }
					 }
				 }
			 }
			 
			 
		 }
		 
		 
		 
		 
		 
		
		
		return result;
	}

}
