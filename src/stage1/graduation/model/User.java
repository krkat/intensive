package stage1.graduation.model;

public class User implements Comparable<User> {
    private String name;
    private String password;
    private String email;

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

    public static class UserBuilder {
        private String name;
        private String password;
        private String email;

        public UserBuilder(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public User build() {
            return new User(this);
        }
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
}
