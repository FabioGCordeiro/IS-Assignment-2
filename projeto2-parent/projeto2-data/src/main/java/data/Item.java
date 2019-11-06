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
 private Float price;
 //FALTA FOTOGRAFIA
 @ManyToOne
 private User user;
 
 public Item() {
  super();
 }

 public Item(String name, String category, String countryOrigin, Float price) {
  super();
  this.name = name;
  this.category = category;
  this.countryOrigin = countryOrigin;
  this.price = price;
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
     return this.id;
 }

public void setId(int id) {
    this.id = id;
}

 public User getUser() {
     return this.user;
 }

 public void setUser(User user) {
     this.user = user;
 }

 /**
  * @return the price
  */
 public Float getPrice() {
     return price;
 }

 /**
  * @param price the price to set
  */
 public void setPrice(Float price) {
     this.price = price;
 }



 @Override
 public String toString() {
  return "Name: " + this.getName() + "\n" + "Category: " + this.getCategory() +  "\n"+ "Country of Origin: " + this.getCountryOrigin() + "\n"+ "Price: " + this.getPrice();
 }
   
}