package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;

import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
@RequestMapping("profile")
public class ProfileController {

    private UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public ProfileController(UserDao userDao, ProfileDao profileDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile(Principal principal) {
        String username = principal.getName();
        int userId = userDao.getIdByUsername(username);
        return ResponseEntity.ok(profileDao.getProfile(userId));
    }

    @PutMapping
    public ResponseEntity<Profile> editProfile(@RequestBody Profile profile, Principal principal) {
        String username = principal.getName();
        int userId = userDao.getIdByUsername(username);
        profile.setUserId(userId);
        Profile adjustments = profileDao.editProfile(profile);
        return ResponseEntity.ok(profileDao.editProfile(adjustments));
    }
}
