package com.taxi.taxista.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends  User {
}
