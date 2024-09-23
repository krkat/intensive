package stage1.graduation.model;

import java.util.Comparator;

public class User implements Comparable<User> {
    private final String name;
    private final String password;
    private final String email;

    private User(UserBuilder userBuilder) {
        name = userBuilder.name;
        password = userBuilder.password;
        email = userBuilder.email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int compareTo(User other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int passwordComparison = this.password.compareTo(other.password);
        if (passwordComparison != 0) {
            return passwordComparison;
        }

        return this.email.compareTo(other.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // Компараторы для сортировки по отдельным полям
    public static Comparator<User> compareByName() {
        return Comparator.comparing(User::getName);
    }

    public static Comparator<User> compareByPassword() {
        return Comparator.comparing(User::getPassword);
    }

    public static Comparator<User> compareByEmail() {
        return Comparator.comparing(User::getEmail);
    }

    //Реализация паттерна Builder
    public static class UserBuilder {
        private final String name;
        private String password;
        private String email;

        public UserBuilder(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Имя не может быть null или пустым");
            }
            this.name = name;
        }

        public UserBuilder setPassword(String password) {
            if (password == null || password.isEmpty()) {
                throw new IllegalArgumentException("Пароль не может быть null или пустым");
            }
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Неверный формат адреса электронной почты");
            }
            this.email = email;
            return this;
        }

        public User build() {
            if (password == null) {
                throw new IllegalStateException("Необходимо указать пароль");
            }
            if (email == null) {
                throw new IllegalStateException("Необходимо указать адрес электронной почты");
            }
            return new User(this);
        }
    }
}
