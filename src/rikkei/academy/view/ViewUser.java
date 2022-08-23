package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.requet.SignInDTO;
import rikkei.academy.dto.requet.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Role;
import rikkei.academy.model.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ViewUser {
    UserController userController = new UserController();
    List<User> userList = userController.showListUser();
    public ViewUser(){
    }
    public  void formRegister(){
        System.out.println("************FORM REGISTER****************");
        int id;
        if(userList.size()==0){
            id = 1;
        } else {
            id = userList.get(userList.size()-1).getId()+1;
        }
        //NAME
        String name;
        boolean validateName;
        while (true){
            System.out.println("Enter the name: ");
            name = Config.scanner().nextLine();
            validateName = Pattern.matches("[A-Z][a-zA-Z[\\s]]{1,10}",name);
            if(validateName){
                break;
            } else {
                System.err.println("The name failed! Please try again!");
            }
        }
        //USERNAME
        String username;
        boolean validateUsername;
        while (true){
            System.out.println("Enter the username: ");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("[a-zA-Z0-9]{1,40}",username);
            if(validateUsername){
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }
        //EMAIL
        String email;
        boolean validateEmail;
        while (true){
            System.out.println("Enter the email: ");
            email = Config.scanner().nextLine();
            validateEmail = Pattern.matches("^(.+)@(.+)$",email);
            if(validateEmail){
                break;
            } else {
                System.err.println("The email failed! Please try again!");
            }
        }
        //PASSWORD
        String password;
        boolean validatePassword;
        while (true){
            System.out.println("Enter the password: ");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}",password);
            if(validatePassword){
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }
        System.out.println("Nhập vào Role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRoles = new HashSet<>();
        strRoles.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, username,email,password,strRoles);
        //Lấy đối tượng message từ Controller đổ về để check validate các trường hợp trùng lặp trong database
        ResponseMessenger check_existed = userController.register(signUpDTO);
        //IN RA MÀU CHO System.out -> màu vàng a e tìm hiểu thêm in màu khác nhé
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        if(check_existed.getMessage().equals("username_existed")){
            System.err.println("The username is exited! Please try again!");
            formRegister();
        } else  if(check_existed.getMessage().equals("email_existed")){
            System.err.println("The email is exited! Please try again!");
            formRegister();
        } else if(check_existed.getMessage().equals("success")){
            System.out.println(ANSI_YELLOW+"CREATE USER SUCCESS!!!!!"+ANSI_RESET);
            System.out.println("CHECK LIST => "+userController.showListUser());
//            new Main();
        }
    }
    public void showListUser(){
        System.out.println("=======ID====NAME=====USERNAME====EMAIL=======AVATAR=======STATUS=====ROLE=====");
        for (int i = 0; i < userList.size(); i++){

            System.out.println("======"+userList.get(i).getId()+ "=======" + userList.get(i).getName() + "======" + userList.get(i).getUsername() + "=====" + userList.get(i).getEmail() + "=====" + userList.get(i).getAvatar()+ "=====" + userList.get(i).isStatus() + "=====" + userList.get(i).getRoles() + "password: " + userList.get(i).getPassword());


        }
    }
    public void formLogin(){
        String username;
        boolean validateUsername;
        while (true){
            System.out.println("Enter the username: ");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("[a-zA-Z0-9]{1,40}",username);
            if(validateUsername){
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }

        String password;
        boolean validatePassword;
        while (true){
            System.out.println("Enter the password: ");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}",password);
            if(validatePassword){
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }
        ResponseMessenger messenger = userController.login(new SignInDTO(username, password));
        if(messenger.getMessage().equals("login_failed")){
            System.err.println("Login Filed");
            formLogin();
        } else {
            profile();

        }
    }
    public void profile(){
        System.out.println("============YOUR PROFILE============");
        User userLogin = userController.getCurrentUser();
        System.out.println("Wellcome: " + userLogin.getName());
        System.out.println("ROLE" + userLogin.getRoles());
        String roleUser =null;
        Iterator<Role> iterator = userLogin.getRoles().iterator();
        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getName());
            roleUser = String.valueOf(iterator.next().getName());
        }
        if (roleUser.equals("ADMIN")){
            System.out.println("1: Chức năng dành cho ADMIN");

        }
        System.out.println("2: LogOut");
        System.out.println("3: back");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 2:
                new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL, null);
                formLogin();
                break;
            case 3:
                new Main();
                break;

        }


    }

}
