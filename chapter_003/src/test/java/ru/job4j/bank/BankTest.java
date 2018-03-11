package ru.job4j.bank;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private Bank bank;

    @Before
    public void setUsers() {
        this.bank = new Bank();
        this.bank.addUser(new User("Andrew", "1234567"));
        this.bank.addUser(new User("Dima", "7654321"));
        this.bank.addAccountToUser("1234567", new Account(1.0d, "4444"));
        this.bank.addAccountToUser("1234567", new Account(50.0d, "2222"));
        this.bank.addAccountToUser("7654321", new Account(2.0d, "3333"));
        this.bank.addAccountToUser("7654321", new Account(10.5d, "1111"));
    }

    @Test
    public void whenAddUserThenAmountUsersIsThree() {
        this.bank.addUser(new User("Kolia", "67"));
        assertThat(this.bank.amountUsers(), is(3));
    }

    @Test
    public void whenAddUserThenNewUserNameIsKolia() {
        this.bank.addUser(new User("Kolia", "67"));
        List<User> list = new ArrayList<>(this.bank.getUsers());
        boolean nameEquals = false;
        for (User user : list) {
            if ("Kolia".equals(user.getName())) {
                nameEquals = true;
            }
        }
        assertThat(nameEquals, is(true));
    }

    @Test
    public void whenAddExistUserThenNotAddUserToMap() {
        this.bank.addUser(new User("Andrew", "1234567"));
        assertThat(this.bank.amountUsers(), is(2));
        assertThat(this.bank.getUserAccounts("1234567"), is(new ArrayList<>(
                Arrays.asList(
                        new Account(1.0d, "4444"),
                        new Account(50.0d, "2222")
                )
        )));
    }

    @Test
    public void whenDeleteUserThenAmountUsersIsOne() {
        this.bank.deleteUser(new User("Andrew", "1234567"));
        assertThat(this.bank.amountUsers(), is(1));
    }
    @Test
    public void whenDeleteNoExistUserThenAmountUsersIsTwo() {
        this.bank.deleteUser(new User("A", "12"));
        assertThat(this.bank.amountUsers(), is(2));
    }

    @Test
    public void whenAddNewAccountToUserThenAccountsSizeIsThree() {
        this.bank.addAccountToUser("1234567", new Account(0.1d, "4321"));
        assertThat(this.bank.getUserAccounts("1234567").size(), is(3));
    }

    @Test
    public void whenAddExistAccountToUserThenAccountsSizeIsTwo() {
        this.bank.addAccountToUser("1234567", new Account(0.1d, "4444"));
        assertThat(this.bank.getUserAccounts("1234567").size(), is(2));
    }

    @Test
    public void whenAddExistAccountToUserThenValueIsOneDotZero() {
        this.bank.addAccountToUser("1234567", new Account(0.1d, "4444"));
        assertThat(this.bank.getUserAccounts("1234567").get(0).getValue(), is(1.0d));
    }

    @Test
    public void whenDeleteAccountFromUserThenAccountsSizeIsOne() {
        this.bank.deleteAccountFromUser("7654321", new Account(10.5d, "1111"));
        assertThat(this.bank.getUserAccounts("7654321").size(), is(1));
    }

    @Test
    public void whenDeleteNoExistAccountFormUserThenAccountsSizeIsTwo() {
        this.bank.deleteAccountFromUser("7654321", new Account(10.5d, "11112"));
        assertThat(this.bank.getUserAccounts("7654321").size(), is(2));
    }

    @Test
    public void whenGetUserAccountsThenGetUserAccounts() {
        List<Account> result = this.bank.getUserAccounts("7654321");
        assertThat(result, is(Arrays.asList(
                new Account(2.0d, "3333"),
                new Account(10.5d, "1111")
                ))
        );
    }

    @Test
    public void whenGetUserAccountsFromNotExistUserThenIsNull() {
        List<Account> result = this.bank.getUserAccounts("0000");
        assertThat(result, is(new ArrayList<Account>()));
    }

    @Test
    public void whenTransferMoneyFromNoEmptyAccountThenMoneyTransferred() {
        boolean result = this.bank.transferMoney("1234567", "2222",
                "7654321", "3333", 50.0d);
        double valueSrc = this.bank.getUserAccounts("1234567").get(1).getValue();
        double valueDest = this.bank.getUserAccounts("7654321").get(0).getValue();
        assertThat(result, is(true));
        assertThat(valueSrc, is(0.0d));
        assertThat(valueDest, is(52.0d));
    }

    @Test
    public void whenTransferMoneyFromEmptyAccountThenMoneyNotTransferred() {
        boolean result = this.bank.transferMoney("1234567", "4444",
                "7654321", "3333", 50.0d);
        double valueSrc = this.bank.getUserAccounts("1234567").get(0).getValue();
        double valueDest = this.bank.getUserAccounts("7654321").get(0).getValue();
        assertThat(result, is(false));
        assertThat(valueSrc, is(1.0d));
        assertThat(valueDest, is(2.0d));
    }

    @Test
    public void whenTransferMoneyFromNotExistAccountThenMoneyNotTransferred() {
        boolean result = this.bank.transferMoney("1234567", "42",
                "7654321", "3333", 50.0d);
        double valueDest = this.bank.getUserAccounts("7654321").get(0).getValue();
        assertThat(result, is(false));
        assertThat(valueDest, is(2.0d));
    }
}
