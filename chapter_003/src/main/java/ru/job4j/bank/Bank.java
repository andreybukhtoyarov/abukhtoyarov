package ru.job4j.bank;

import java.util.*;

/**
 * This class is wrapper over an Map.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class Bank {
    /**Field with map of Users.*/
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Add new user in a map.
     * @param user - new User.
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Delete User from a map.
     * @param user - User.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Find user by passport.
     * @param passport - Users passport.
     * @return user.
     */
    private User findUser(String passport) {
        User user = null;
        for (Map.Entry<User, List<Account>> pair : this.users.entrySet()) {
            if (Objects.equals(passport, pair.getKey().getPassport())) {
                user = pair.getKey();
                break;
            }
        }
        return user;
    }

    /**
     * Add account to User.
     * @param passport - Users passport.
     * @param account - new account.
     */
    public void addAccountToUser(String passport, Account account) {
        if (findUser(passport) != null) {
            List<Account> userAccounts = this.users.get(findUser(passport));
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Delete account from User.
     * @param passport - Users passport.
     * @param account - Users account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (findUser(passport) != null) {
            List<Account> userAccounts = this.users.get(findUser(passport));
            if (userAccounts.contains(account)) {
                userAccounts.remove(account);
            }
        }
    }

    /**
     * Get a list of accounts for the user.
     * @param passport - Users passport.
     * @return - list of accounts for the user.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        if (findUser(passport) != null) {
            accounts = this.users.get(findUser(passport));
        }
        return accounts;
    }

    /**
     * Method for transferring money from one account to another account.
     * @param srcPassport - the passport of the user whose account debits money.
     * @param srcRequisite - the requisite of the account whose debits money.
     * @param destPassport - the passport of the user whose account the money is transferred.
     * @param destRequisite - the requisite of the account whose the money is transferred.
     * @param amount - transfer amount.
     * @return - true if operation is done.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean transferred = false;
        Account srcAccount = getAccountByRequisites(getUserAccounts(srcPassport), srcRequisite);
        Account destAccount = getAccountByRequisites(getUserAccounts(destPassport), destRequisite);
        if (amount > 0 && srcAccount != null && destAccount != null && srcAccount.getValue() >= amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                destAccount.setValue(destAccount.getValue() + amount);
                transferred = true;
        }
        return transferred;
    }

    /**
     * Get amount users.
     * @return amount users.
     */
    public int amountUsers() {
        return this.users.size();
    }

    /**
     * Get Users list.
     * @return - Users list.
     */
    public List<User> getUsers() {
        return new ArrayList<>(this.users.keySet());
    }

    /**
     * Get account by requisites.
     * @param accounts - list of accounts.
     * @param requisites - the requisite of the account.
     * @return account.
     */
    private Account getAccountByRequisites(List<Account> accounts, String requisites) {
        Account result = null;
        for (Account account : accounts) {
            if (Objects.equals(account.getRequisites(), requisites)) {
                result = account;
            }
        }
        return result;
    }
}
