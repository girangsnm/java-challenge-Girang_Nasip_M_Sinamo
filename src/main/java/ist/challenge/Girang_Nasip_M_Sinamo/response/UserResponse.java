package ist.challenge.Girang_Nasip_M_Sinamo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserResponse {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObject ){
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("message", message);
	map.put("status", status.value());
	map.put("Data", responseObject);
	
	return new ResponseEntity<Object>(map, status);
	
	}
}
