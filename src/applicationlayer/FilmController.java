package applicationlayer;

import datalayer.DAOFilm;
import domain.Account;
import domain.Film;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * This class is responsible for the logic of films behind the user interface.
 */
public class FilmController {

    private static FilmController instance;

    public static FilmController getInstance() {
        if(instance == null) {
            instance = new FilmController();
        }
        return instance;
    }

    /**
     * Gets all films from the datasource.
     * @return all films.
     */
    public Set<Film> readAllFilms() {
        return DAOFilm.getInstance().readAll();
    }

    /**
     * gets the film that is watched to longest on average.
     * and the age indication is below 16 years
     * @return longest watched children film
     */
    public Film readLongestDurationChildrenFilm() {
        return DAOFilm.getInstance().readLongestDurationChildFilm();
    }

    /**
     * gets all watched films for the given account.
     * @param account
     * @return all watched films
     */
    public Set<Film> readWatchedFilmsForAccount(Account account) {
        throw new NotImplementedException();
    }
}
