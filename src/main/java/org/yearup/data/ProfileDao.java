package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile create(Profile profile);
    Profile editProfile(Profile profile);
    Profile getProfile(int userId);
}
