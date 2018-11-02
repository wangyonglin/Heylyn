package com.heylyn.result;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ResultUtil {
	public static String success(Object value) {
		ObjectMapper  mapper  = new ObjectMapper ();
		Result result = new Result();
		result.setErrcode(0);
		result.setSuccess(true);
		result.setReason("SUCCESS");
		result.setResult(value);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
		result.setTimestamp(dateFormat.format(new Date()));
		try {
			return mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}

	public static Result failure(Integer code, String message) {
		Result result = new Result();
		result.setErrcode(code);
		result.setReason(message);
		result.setSuccess(false);
		result.setResult(null);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
		result.setTimestamp(dateFormat.format(new Date()));
		return result;
	}
}
