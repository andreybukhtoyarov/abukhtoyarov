package ru.job4j.pool;

/**
 * This class subscribe User.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class User {
    /**
     * Strings Username and e:mail.
     */
    private String username, email;

    public User(String username, String beforeCommercialAt, String domainBeforeDot, String domainAfterDot) {
        this.username = username;
        setEmail(beforeCommercialAt, domainBeforeDot, domainAfterDot);
    }

    /**
     * Set User name.
     * @param username User name.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set User e:mail.
     * @param beforeCommercialAt before commercial at (user@.....)
     * @param domainBeforeDot domain before dot (....@domain. ...)
     * @param domainAfterDot domain after dot (....@.... .com)
     */
    public void setEmail(String beforeCommercialAt, String domainBeforeDot, String domainAfterDot) {
        this.email = String.format("%s@%s.%s", beforeCommercialAt, domainBeforeDot, domainAfterDot);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
