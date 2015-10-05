package org.abhinav.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.abhinav.rest.messenger.dao.profile.ProfileDAO;
import org.abhinav.rest.messenger.dao.profile.impl.ProfileDAOImpl;
import org.abhinav.rest.messenger.model.Profile;

public class ProfileService {

	private ProfileDAO profileDAO = null;

	private ProfileDAO getInstance() {
		if (profileDAO == null) {
			profileDAO = new ProfileDAOImpl();
		}
		return profileDAO;
	}

	private Map<String, Profile> profiles = getInstance().getProfiles();

	public ProfileService() {
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile createProfile(Profile profile) {
		profile = profileDAO.createProfile(profile);
		return profile;
	}

	public void deleteProfile(String profileName) {
		profiles.remove(profileName);
		profileDAO.deleteProfile(profileName);
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profileDAO.updateProfile(profile);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
}
