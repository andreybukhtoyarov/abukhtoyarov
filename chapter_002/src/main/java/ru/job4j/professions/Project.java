package ru.job4j.professions;
/**
 * This class describes the project for Engineer.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Project {
	/**Field wellDone - project readiness.*/
    public boolean wellDone;

    /**
     * This is setter for field wellDone.
     * @param wellDone - project is done? true or false.
     */
    public void setWellDone(boolean wellDone) {
        this.wellDone = wellDone;
    }

    /**
     * This is getter for field wellDone.
     * @return project readiness. true or false.
     */
    public boolean isWellDone() {
        return wellDone;
    }
}
