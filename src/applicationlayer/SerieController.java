package applicationlayer;

import datalayer.DAOSerie;
import domain.Account;
import domain.Episode;
import domain.Serie;
import java.util.Map;
import java.util.Set;

/**
 * This class is responsible for the logic of series behind the user interface.
 */
public class SerieController {

    /**
     * Singleton pattern.
     */
    private static SerieController instance;

    /**
     * Gets all Series from the datasource.
     * @return all series
     */
    public Set<Serie> readAllSeries() {
        return DAOSerie.getInstance().readAll();
    }

    /**
     * Gets the serie with the given title from the datasource.
     * @param serieTitle
     * @return serie
     */
    public Serie readSerie(String serieTitle) {
        return DAOSerie.getInstance().read(serieTitle);
    }

    /**
     * Get the average watch time per episode of a serie.
     * @param serie
     * @return map with the episode and the average watch time.
     */
    public Map<Episode, Integer> readAverageWatchTime(Serie serie) {
        return DAOSerie.getInstance().readAverageWatchTime(serie);
    }

    /**
     * Gets the average watch time per episode of a serie for the given account.
     * @param account
     * @param serie
     * @return
     */
    public Map<Episode, Integer> readAverageWatchTimeForAccount(Account account, Serie serie) {
        return DAOSerie.getInstance().readAverageWatchTimeForAccount(account, serie);
    }

    public static SerieController getInstance() {
        if(instance == null) {
            instance = new SerieController();
        }
        return instance;
    }

}
