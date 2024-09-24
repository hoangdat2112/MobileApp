package com.example.bt1.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserList {
    private static List<User> userList = new ArrayList<>();

    static {
        try {
            // Sử dụng SimpleDateFormat để chuyển đổi chuỗi thành Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date birthDate1 = formatter.parse("20/2/2002");
            Date birthDate2 = formatter.parse("15/5/2003");

            User user1 = new User("hoangdat", "12345", "hoang van dat", "hoangdat@gmail.com", birthDate1, "Male");
            User user2 = new User("dodat", "12345", "do tien dat", "dodat2003@gmail.com", birthDate2, "Male");

            userList.add(user1);
            userList.add(user2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUserList() {
        return new ArrayList<>(userList);
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    public static User findUserByUsername(String username) {
        Predicate<User> predicate = u -> u.getUsername().equals(username);
        Optional<User> user = userList.stream()
                .filter(predicate)
                .findFirst();
        return user.orElse(null);
    }

    public static List<User> findAllUsersByUsername(String query) {
        Predicate<User> predicate = u -> u.getUsername().contains(query);
        return userList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static List<User> findAllUsersByEmail(String query) {
        Predicate<User> predicate = u -> u.getEmail().toLowerCase().contains(query.toLowerCase());
        return userList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public static void updateUser(String username, String newPassword, String newFullname, String newEmail, String newDateOfBirth, String newGender) {
        User user = findUserByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            user.setFullname(newFullname);
            user.setEmail(newEmail);
            user.setGender(newGender);
            try {
                // Chuyển đổi chuỗi ngày tháng về kiểu Date
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = formatter.parse(newDateOfBirth);
                user.setDateOfBirth(date);  // Cập nhật ngày sinh
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(User user) {
        userList.remove(user);
    }
    public static List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

}
