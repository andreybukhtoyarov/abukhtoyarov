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
        Map<User, List<Account>> copy = new HashMap<>(this.users);
        for (Map.Entry<User, List<Account>> pair : copy.entrySet()) {
            if (user.equals(pair.getKey())) {
                this.users.remove(user);
                break;
            }
        }
    }

    /**
     * Add account to User.
     * @param passport - Users passport.
     * @param account - new account.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> pair : this.users.entrySet()) {
            if (passport.equals(pair.getKey().getPassport())) {
                if (pair.getValue().isEmpty()) {
                    pair.setValue(Arrays.asList(account));
                    break;
                }
                List<Account> accounts = new ArrayList<>(pair.getValue());
                if (!account.requisitesEquals(accounts)) {
                    accounts.add(account);
                    pair.setValue(accounts);
                    break;
                }
            }
        }
    }

    /**
     * Delete account from User.
     * @param passport - Users passport.
     * @param account - Users account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> entry : this.users.entrySet()) {
            if (passport.equals(entry.getKey().getPassport()) && !entry.getValue().isEmpty()) {
                List<Account> accounts = new ArrayList<>(this.users.get(entry.getKey()));
                if (accounts.indexOf(account) >= 0) {
                    accounts.remove(accounts.indexOf(account));
                    entry.setValue(accounts);
                    break;
                }
            }
        }
    }

    /**
     * Get a list of accounts for the user.
     * @param passport - Users passport.
     * @return - list of accounts for the user.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = null;
        for (Map.Entry<User, List<Account>> entry : this.users.entrySet()) {
            if (passport.equals(entry.getKey().getPassport()) && !entry.getValue().isEmpty()) {
                accounts = new ArrayList<>(entry.getValue());
                break;
            }
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
        if (amount > 0) {
            Map.Entry<User, List<Account>> pairSrc = null;
            Map.Entry<User, List<Account>> pairDest = null;
            List<Account> accountsSrc = null;
            List<Account> accountsDest = null;
            int indexScr = -1;
            int indexDest = -1;
            for (Map.Entry<User, List<Account>> entry : this.users.entrySet()) {
                if (srcPassport.equals(entry.getKey().getPassport())) {
                    pairSrc = entry;
                } else if (destPassport.equals(entry.getKey().getPassport())) {
                    pairDest = entry;
                }
            }
            if (pairSrc != null && pairDest != null) {
                accountsSrc = new ArrayList<>(pairSrc.getValue());
                accountsDest = new ArrayList<>(pairDest.getValue());
                for (int index = 0; index < accountsSrc.size(); ++index) {
                    if (srcRequisite.equals(accountsSrc.get(index).getRequisites())
                            && accountsSrc.get(index).getValue() >= amount) {
                        indexScr = index;
                        break;
                    }
                }
                for (int index = 0; index < accountsDest.size(); ++index) {
                    if (destRequisite.equals(accountsDest.get(index).getRequisites())) {
                        indexDest = index;
                        break;
                    }
                }
            }
            if (indexScr >= 0 && indexDest >= 0) {
                accountsSrc.get(indexScr).setValue(accountsSrc.get(indexScr).getValue() - amount);
                accountsDest.get(indexDest).setValue(accountsDest.get(indexDest).getValue() + amount);
                pairSrc.setValue(accountsSrc);
                pairDest.setValue(accountsDest);
                transferred = true;
            }
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
}
