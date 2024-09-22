package stage1.graduation.model;

public class User implements Comparable<User> {
    private final String name;
    private final String password;
    private final String email;

    // Приватный конструктор для паттерна Builder
    private User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // Статический метод для вызова билдера
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Вложенный класс Builder
    public static class UserBuilder {
        private String name;
        private String password;
        private String email;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        // Метод build() для создания объекта User
        public User build() {
            return new User(name, password, email);
        }
    }

    // Геттеры для доступа к полям
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // Реализация метода compareTo для сортировки по полям
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

    // Переопределение метода toString для удобного вывода объекта
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
