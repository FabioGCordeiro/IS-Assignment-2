package data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Player
 *
 */
@Entity
public class Item implements Serializable {
 private static final long serialVersionUID = 1L;
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
 int id;
 private String name;
 private String category;
 private String countryOrigin;
 //FALTA FOTOGRAFIA
 @ManyToOne
 private User user;
 
 public Item() {
  super();
 }

 public Item(String name, String category, String countryOrigin) {
  super();
  this.name = name;
  this.category = category;
  this.countryOrigin = countryOrigin;
 }

 public String getName() {
  return this.name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getCategory() {
  return this.category;
 }

 public void setCategory(String category) {
  this.category = category;
 }

 public String getCountryOrigin() {
     return this.countryOrigin;
 }

 public void setCountryOrigin(String countryOrigin) {
     this.countryOrigin = countryOrigin;
 }


 public int getId() {
     return id;
 }

public void setId(int id) {
    this.id = id;
}

 public User getUser() {
     return user;
 }

 public void setUser(User user) {
     this.user = user;
 }



 @Override
 public String toString() {
  return " Name: " + this.name +  " Category: " + this.category +  " Country of Origin: " + this.countryOrigin;
 }
   
}