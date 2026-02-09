package org.example.hotel;

import org.json.JSONObject;

/**
 * User
 */
public class User {
    String username;
    String password;
    Boolean admin;
    String name;
    String lastName;
    String email;
    String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User() {}

    public static User fromJSON(JSONObject json) {
        User user = new User();
        user.username = json.getString("username");
        user.password = json.getString("password");
        user.admin = json.getBoolean("admin");
        user.name = json.optString("name", "No name");
        user.lastName = json.optString("lastName", "No last name");
        user.email = json.optString("email", "No email");
        user.phone = json.optString("phone", "No phone");
        return user;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("password", password);
        json.put("admin", admin);
        json.put("name", name);
        json.put("lastName", lastName);
        json.put("email", email);
        json.put("phone", phone);
        return json;
    }
}

