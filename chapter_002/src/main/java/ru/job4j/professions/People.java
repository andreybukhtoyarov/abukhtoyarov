package ru.job4j.professions;

/**
 * This class describes a person.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class People {
    /**Fields name of person, and address of person*/
    public String name, address;
    /**Field age of person*/
    public int age;
    /**Field profession of person*/
    public Profession profession;
    /**Fields health of person, and trained of person*/
    public boolean isSick = true, isTrained;
	
	/**
     * This is constructor of class People.
     * @param name - name of person.
     * @param address - address of person.
     * @param age - age of person.
     */
    public People(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
	
    /**
     * This is constructor of class People.
     * @param name - name of person.
     * @param address - address of person.
     * @param age - age of person.
     * @param profession - profession of person.
     */
    public People(String name, String address, int age, Profession profession) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.profession = profession;
    }

    /**
     * This is getter for field name.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * This is getter for field address.
     * @return address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * This is getter for field age.
     * @return age.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * This is getter for field profession.
     * @return profession.
     */
    public Profession getProfession() {
        return this.profession;
    }

    /**
     * This is getter for field isSick.
     * @return isSick.
     */
    public boolean getIsSick() {
        return this.isSick;
    }

    /**
     * This is getter for field isTrained.
     * @return isTrained.
     */
    public boolean getIsTrained() {
        return this.isTrained;
    }

    /**
     * This is setter for field isSick.
     * @param sick - true/false.
     */
    public void setSick(boolean sick) {
        this.isSick = sick;
    }

    /**
     * This is setter for field isTrained.
     * @param trained - true/false.
     */
    public void setTrained(boolean trained) {
        this.isTrained = trained;
    }
}
