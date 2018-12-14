package applicationlayer;

import datalayer.DAOProfile;
import domain.Account;
import domain.Episode;
import domain.Film;
import domain.Profile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * This class is responsible for the logic of profiles behind the user interface.
 */
public class ProfileController {

    /**
     * Singleton pattern.
     */
    private static ProfileController instance;

    /**
     * Saves the given profile to the datasource.
     * @param profile
     */
    public void save(Profile profile) {
        throw new NotImplementedException();
    }

    /**
     * Gets all profiles from the datasource.
     * @return all profiles
     */
    public Set<Profile> readAllProfiles() {
        return DAOProfile.getInstance().readAll();
    }

    /**
     * Gets all profiles for the given account.
     * @param account
     * @return all profiles of the given account
     */
    public Set<Profile> readAllProfilesForAccount(Account account) {
        throw new NotImplementedException();
    }

    /**
     * Removed the profile from the datasource.
     */
    public void deleteProfile() {
        throw new NotImplementedException();
    }

    /**
     * Adds the episode to the profiles watchedPrograms and updates this in the datasource.
     * @param profile
     * @param episode
     */
    public void watchEpisode(Profile profile, Episode episode) {
        throw new NotImplementedException();
    }

    /**
     * Adds the film to the profiles watchedEpisodes and updates this in the datasource.
     * @param profile
     * @param film
     */
    public void watchFilm(Profile profile, Film film) {
        throw new NotImplementedException();
    }

    public static ProfileController getInstance() {
        if(instance == null) {
            instance = new ProfileController();
        }

        return instance;
    }
}
