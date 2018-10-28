package com.heylyn.os;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Container<T>  {

	public Container() {
		super();
		// TODO Auto-generated constructor stub
	}
	private List<T> list = new ArrayList<T>();
	private 	ObjectMapper  mapper  = new ObjectMapper ();

	public void put(T obj){
		if(obj!=null) {
			 list.add(obj);
		}
		
		
	}
	public List<T> all(){
		if(list!=null) {
			return list;
		}
		return null;
	}
	public String  fromObject() {
		// TODO Auto-generated method stub
		if(list!=null) {

			try {
				return mapper.writeValueAsString(list);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	
	}
	
}
