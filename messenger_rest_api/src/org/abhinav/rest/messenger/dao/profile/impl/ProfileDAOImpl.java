package org.abhinav.rest.messenger.dao.profile.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.abhinav.rest.messenger.dao.base.BaseDAO;
import org.abhinav.rest.messenger.dao.profile.ProfileDAO;
import org.abhinav.rest.messenger.model.Profile;

public class ProfileDAOImpl implements ProfileDAO {

	private Connection connection = BaseDAO.getConnection();

	public ProfileDAOImpl() {
	}

	@Override
	public Map<String, Profile> getProfiles() {

		Map<String, Profile> profiles = new HashMap<String, Profile>();

		try (Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM PROFILES")) {
			while (rs.next()) {
				Profile profile = new Profile();
				String profileName = rs.getString("PROFILENAME");
				profile.setId(rs.getInt("PROFILE_ID"));
				profile.setProfileName(profileName);
				profile.setFirstName(rs.getString("FIRSTNAME"));
				profile.setLastName(rs.getString("LASTNAME"));
				profile.setCreated(rs.getTimestamp("CREATED"));
				profile.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
				profiles.put(profileName, profile);
			}

			return profiles;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Profile createProfile(Profile profile) {

		String profileName = profile.getProfileName();
		String insertProfile = "INSERT INTO PROFILES(PROFILENAME,FIRSTNAME,LASTNAME,LAST_UPDATED) VALUES(?, ?,?,NOW())";

		try (PreparedStatement ps = connection.prepareStatement(insertProfile)) {
			ps.setString(1, profileName);
			ps.setString(2, profile.getFirstName());
			ps.setString(3, profile.getLastName());
			ps.executeUpdate();
			selectProfile(profile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profile;
	}

	@Override
	public void deleteProfile(String profileName) {

		String deleteProfile = "DELETE FROM PROFILES WHERE PROFILENAME=?";

		try (PreparedStatement ps = connection.prepareStatement(deleteProfile)) {
			ps.setString(1, profileName);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Profile updateProfile(Profile profile) {

		String deleteProfile = "UPDATE PROFILES SET FIRSTNAME=?,LASTNAME=?,LAST_UPDATED=NOW() WHERE PROFILENAME=?";

		try (PreparedStatement ps = connection.prepareStatement(deleteProfile)) {
			ps.setString(1, profile.getFirstName());
			ps.setString(2, profile.getLastName());
			ps.setString(3, profile.getProfileName());
			ps.executeUpdate();
			selectProfile(profile);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profile;
	}

	private void selectProfile(Profile profile) {

		String selectProfile = "SELECT * FROM PROFILES WHERE PROFILENAME=?";

		try (PreparedStatement ps = connection.prepareStatement(selectProfile)) {
			ps.setString(1, profile.getProfileName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				profile.setId(rs.getInt("PROFILE_ID"));
				profile.setCreated(rs.getTimestamp("CREATED"));
				profile.setLastUpdated(rs.getTimestamp("LAST_UPDATED"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
