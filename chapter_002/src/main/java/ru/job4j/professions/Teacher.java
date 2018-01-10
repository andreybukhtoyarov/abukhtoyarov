package ru.job4j.professions;

/**
 * This class describe profession Teacher.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Teacher extends Profession {
    /**Field override profession name*/
    protected String professionName = "Teacher";
    /**
     * This is constructor of class Teacher.
     * @param professionDiplom - the name of the university.
     * @param salary - size of salary.
     */
    Teacher(String professionDiplom, int salary) {
        super(professionDiplom, salary);
    }

    /**
     * This method describe learning process.
     * @param people - person student.
     */
    void teach(People people) {
        people.setTrained(true);
    }

    /**
     * This method describe exam.
     * @param people - person student.
     * @return - score.
     */
    int takeExam(People people) {
        int score = 0;

        if (people.getIsTrained()) {
            score = 5;
        } else {
            score = 2;
        }
        return score;
    }

    public String getProfessionName() {
        return this.professionName;
    }
}
