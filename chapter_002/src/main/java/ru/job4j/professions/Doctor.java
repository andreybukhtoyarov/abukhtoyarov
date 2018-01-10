package ru.job4j.professions;

/**
 * This class describe profession Doctor.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Doctor extends Profession {
    /**Field override profession name*/
    protected String professionName = "Doctor";
    /**
     * This is constructor of class Doctor.
     * @param professionDiplom - the name of the university.
     * @param salary - size of salary.
     */
    Doctor(String professionDiplom, int salary) {
        super(professionDiplom, salary);
    }

    /**
     * This method describe diagnostic process.
     * @param people - patient person.
     * @return - diagnosis.
     */
    String diagnostic(People people) {
        String diagnosis = "";
        if (people.getIsSick()) {
            if (people.getProfession().getProfessionName().equals("Doctor")) {
                diagnosis = "Хроническая усталость";
            } else if (people.getProfession().getProfessionName().equals("Engineer")) {
                diagnosis = "Хроническая усатость";
            } else if (people.getProfession().getProfessionName().equals("Teacher")) {
                diagnosis = "Хроническая крикливость";
            }
        } else {
            diagnosis = "Пациент здоров!";
        }
        return diagnosis;
    }

    /**
     * This method describe heal process.
     * @param people - patient person.
     */
    void heal(People people) {
        if (!diagnostic(people).equals("Пациент здоров!")) {
            people.setSick(false);
        }
    }

    public String getProfessionName() {
        return this.professionName;
    }
}
