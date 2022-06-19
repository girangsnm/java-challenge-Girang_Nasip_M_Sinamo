package ist.challenge.Girang_Nasip_M_Sinamo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import ist.challenge.Girang_Nasip_M_Sinamo.model.UserModel;
import ist.challenge.Girang_Nasip_M_Sinamo.repository.UserRepository;
import ist.challenge.Girang_Nasip_M_Sinamo.response.UserResponse;

@Service
@Transactional

public class UserService {
	
	
	
		@Autowired
	    UserRepository userRepository;

	    public ResponseEntity<Object> save(UserModel user) {
	        Optional<UserModel> userOptional = userRepository.findByUsername(user.getUsername());

	        if (userOptional.isPresent()) {
	            return UserResponse.generateResponse("username sudah terpakai!", HttpStatus.CONFLICT, null);
	        } else {
	            userRepository.save(user);
	            try {
	                return UserResponse.generateResponse("sukses", HttpStatus.CREATED, user);
	            } catch (Exception e) {
	                return UserResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	            }
	        }
	    }

	    public ResponseEntity<Object> login(UserModel user) {
	        Optional<UserModel> userOptional = userRepository.findByUsername(user.getUsername());
	        String password = userRepository.getPasswordByUsername(user.getUsername());

	        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
	            return UserResponse.generateResponse("username dan/atau password kosong", HttpStatus.BAD_REQUEST, null);
	        } else if (userOptional.isPresent() && user.getPassword().equals(password)){
	            return UserResponse.generateResponse("sukses login", HttpStatus.OK, user);
	        } else if (userOptional.isPresent() && !user.getPassword().equals(password)) {
	            return UserResponse.generateResponse("password salah", HttpStatus.BAD_REQUEST, null);
	        } else {
	            return UserResponse.generateResponse("username tidak ada di database", HttpStatus.UNAUTHORIZED, null);
	        }
	    }

	    public ResponseEntity<Object> listUsers() {
	        try {
	            java.util.List<UserModel> result = userRepository.findAll();
	            return UserResponse.generateResponse("sukses", HttpStatus.OK, result);
	        } catch (Exception e) {
	            return UserResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	        }
	    }

	    public ResponseEntity<Object> findUserById(Long id) {
	        try {
	            UserModel result = userRepository.findById(id).get();
	            return UserResponse.generateResponse("sukses", HttpStatus.OK, result);
	        } catch (Exception e) {
	            return UserResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
	        }
	    }

	    public ResponseEntity<Object> updateUser(UserModel user) {
	        Optional<UserModel> userOptional = userRepository.findByUsername(user.getUsername());
	        String exitingPassword = userRepository.getPasswordById(user.getId());

	        if (userOptional.isPresent()) {
	            return UserResponse.generateResponse("username sudah terpakai", HttpStatus.CONFLICT, null);
	        } else {
	            if (exitingPassword.equals(user.getPassword())) {
	                return UserResponse.generateResponse("password tidak boleh sama dengan sebelumnya", HttpStatus.BAD_REQUEST, null);
	            } else {
	                userRepository.save(user);
	                try {
	                    return UserResponse.generateResponse("sukses", HttpStatus.CREATED, user);
	                } catch (Exception e) {
	                    return UserResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	                }
	            }
	        }
	    }

	    public void deleteUserById(Long id) {
	        userRepository.deleteById(id);
	    }

}
