package ist.challenge.Girang_Nasip_M_Sinamo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ist.challenge.Girang_Nasip_M_Sinamo.model.UserModel;
import ist.challenge.Girang_Nasip_M_Sinamo.service.UserService;

	@RestController
	@RequestMapping(path = "/api")
	public class UserController {
	@Autowired
    UserService userService;

    //register
    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody UserModel user) {
        return userService.save(user);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserModel user) {
        return userService.login(user);
    }

    //list user
    @GetMapping
    public ResponseEntity<Object> listUser() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserModel user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }


}
