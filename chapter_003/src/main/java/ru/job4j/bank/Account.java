package ru.job4j.bank;

import java.util.List;
import java.util.Objects;

/**
 * This class is describe the user's bank account.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Account {
    /**Field with user money.*/
    private double value;
    /**Field with account requisites.*/
    private final String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Compares requisite from the account list with the specified account.
     * @param accounts - account list.
     * @return - true if requisite is equals.
     */
    public boolean requisitesEquals(List<Account> accounts) {
        boolean result = false;
        for (Account accountUser : accounts) {
            if (this.getRequisites().equals(accountUser.getRequisites())) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0 && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, requisites);
    }
}
