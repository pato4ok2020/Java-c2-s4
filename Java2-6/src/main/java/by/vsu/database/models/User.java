package by.vsu.database.models;

public class User {
    public static final Integer MAX_LOGIN_LENGTH = 50;
    public static final Integer MAX_STRING_LENGTH = 255;

    private Integer id;
    private String login;
    private String password;
    private String role;

    public User(Integer id, String login, String password, String role) {
        setId(id);
        setLogin(login);
        setPassword(password);
        setRole(role);
    }

    public User(String login, String password, String role) {
        setLogin(login);
        setPassword(password);
        setRole(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id должен быть положительным числом!");
        }

        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Логин не может быть пустым!");
        }

        if (login.length() > MAX_LOGIN_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина логина: " + MAX_LOGIN_LENGTH);
        }

        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Пароль не может быть пустым!");
        }

        if (password.length() > MAX_STRING_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина пароля: " + MAX_STRING_LENGTH);
        }

        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Роль не может быть пустой!");
        }

        if (role.length() > MAX_STRING_LENGTH) {
            throw new IllegalArgumentException("Максимальная длина роли: " + MAX_STRING_LENGTH);
        }

        if (!role.equals("common") && !role.equals("dean") && !role.equals("admin")) {
            throw new IllegalArgumentException("Роль должна быть 'common', 'dean' или 'admin'");
        }

        this.role = role;
    }
}