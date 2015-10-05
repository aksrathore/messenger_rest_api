package org.abhinav.rest.messenger.dao.profile;

import java.util.Map;

import org.abhinav.rest.messenger.model.Profile;

public interface ProfileDAO {

	Map<String, Profile> getProfiles();

	Profile createProfile(Profile profile);

	Profile updateProfile(Profile profile);

	void deleteProfile(String profileName);
}
