package org.fasttackit.onlineshop.transfer;

import javax.validation.constraints.NotBlank;

public class SaveCustomerRequest {

    @NotBlank
    private String fisrtName;
    @NotBlank
    private String lastName;


    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "SaveCustomerRequest{" +
                "fisrtName='" + fisrtName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
