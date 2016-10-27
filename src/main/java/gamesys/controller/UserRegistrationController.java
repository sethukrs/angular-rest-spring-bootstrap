package gamesys.controller;
 
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import gamesys.exception.DuplicateSSNException;
import gamesys.exception.InvalidDOBException;
import gamesys.exception.UserBlackListedException;
import gamesys.service.ExclusionService;
import gamesys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gamesys.model.User;

@RestController
@RequestMapping(UserRegistrationController.PATH)
public class UserRegistrationController {

    public static final String PATH = "CodingExercise";
    public static final String REGISTER_PATH = "/register";
 
    @Autowired
    UserService userService;

    @Autowired
    ExclusionService exclusionService;

    @ResponseBody
    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> listAllUsers() {
        return new ResponseEntity<Set<User>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        if (userService.isUserRegistered(user)) {
            throw new DuplicateSSNException();
        }
        try {
            ISO8601DateFormat df = new ISO8601DateFormat();
            Date dob = df.parse(user.getDob());
            if (dob.after(new Date())) {
                throw new InvalidDOBException();
            }
        } catch (ParseException e) {
            throw new InvalidDOBException();
        }

        if ( !exclusionService.validate(user.getDob(), user.getSsn())) {
            throw new UserBlackListedException();
        }
        userService.register(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/register/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = REGISTER_PATH+"/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deregister(@PathVariable("id") int id) {

        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deregisterUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.DELETE)
    public HttpEntity<User> deregisterAllUsers() {
        userService.deregisterAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}