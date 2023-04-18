package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    final String emailRegex = "^(.+)@(.+).com";

    public Customer(String firstName, String lastName, String email){
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1);
        lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
        this.firstName = firstName;
        this.lastName = lastName;
        Pattern pattern = Pattern.compile(emailRegex);
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email Format");
        }
        else{
            this.email = email;
        }
    }
    public String getEmail(){
        return email;
    }
    @Override
    public String toString(){
        return "Name: " + firstName + " " + lastName + ", Email: " + email;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Customer customer= (Customer) o;
        return firstName.equals(customer.firstName);
    }
    @Override
    public int hashCode(){
        return Objects.hash(firstName);
    }
}
