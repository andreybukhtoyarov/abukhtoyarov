package ru.job4j.professions;

/**
 * This class describe profession.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Profession {
    /**Field profession name, and diploma.*/
    public String professionName, professionDiplom;
    /**Field size of salary.*/
    public int salary;

    /**
     * This is constructor of class Profession.
     * @param professionDiplom - the name of the university.
     * @param salary - size of salary.
     */
    public Profession(String professionDiplom, int salary) {
        this.professionDiplom = professionDiplom;
        this.salary = salary;
    }
	
	/**
     * This is constructor of class Profession.
     * @param professionDiplom - the name of the university.
     * @param salary - size of salary.
     */
    public Profession(String professionName, String professionDiplom, int salary) {
        this.professionName = professionName;
        this.professionDiplom = professionDiplom;
        this.salary = salary;
    }

    /**
     * This is getter for field profession.
     * @return profession.
     */
    public String getProfessionName() {
        return this.professionName;
    }

    /**
     * This is getter for field salary.
     * @return salary.
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * This is getter for field professionDiplom.
     * @return professionDiplom.
     */
    public String getProfessionDiplom() {
        return this.professionDiplom;
    }
}
