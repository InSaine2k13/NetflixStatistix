package applicationlayer;

import datalayer.DAOEpisode;
import domain.Episode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * This class is responsible for the logic of episodes behind the user interface
 */
public class EpisodeController {

    private static EpisodeController instance;

    public static EpisodeController getInstance() {
        if(instance == null) {
            instance = new EpisodeController();
        }
        return instance;
    }

    /**
     * Gets all Episodes from the datasource
     * @return all episodes
     */
    public Set<Episode> readAllEpisodes() {
        return DAOEpisode.getInstance().readAll();
    }
}
