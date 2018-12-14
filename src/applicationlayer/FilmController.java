package applicationlayer;

import domain.Account;
import domain.Film;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

/**
 * This class is responsible for the logic of films behind the user interface.
 */
public class FilmController {

    /**
     * Gets all films from the datasource.
     * @return all films.
     */
    public Set<Film> readAllFilms() {
        throw new NotImplementedException();
    }

    /**
     * gets the film that is watched to longest on average.
     * and the age indication is below 16 years
     * @return longest watched children film
     */
    public Film readLongestWatchedChildrenFilm() {
        throw new NotImplementedException();
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
