package mp.cariaso.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private Environment environment;

    @PreAuthorize("#request.getRemoteAddr().equals(#request.getLocalAddr())")
    @RequestMapping(path = "/active", method = {RequestMethod.GET})
    public ResponseEntity<?> getActiveProfile() {

        String responseBody = String.format("Spring Active Profile: %s", environment.getActiveProfiles());

        return new ResponseEntity<String>(responseBody, HttpStatus.OK);

    }
}
