package ru.job4j.professions;

/**
 * This class describe profession Engineer.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Engineer extends Profession {
    /**Field override profession name*/
    protected String professionName = "Engineer";
    /**
     * This is constructor of class Engineer.
     * @param professionDiplom - the name of the university.
     * @param salary - size of salary.
     */
    Engineer(String professionDiplom, int salary) {
        super(professionDiplom, salary);
    }
	
	/**
     * This method describe develop process.
     * @param project - project for develop.
     */
    void develop(Project project) {
        if (!project.isWellDone()) {
            project.setWellDone(true);
        }
    }

    public String getProfessionName() {
        return this.professionName;
    }
}
