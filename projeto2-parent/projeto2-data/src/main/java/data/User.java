package data;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Player
 *
 */
@Entity
public class User implements Serializable {
 private static final long serialVersionUID = 1L;
 @Id @GeneratedValue(strategy=GenerationType.AUTO)
 private String password;
 private String username;
 private String email;
 private String country;
 
 public User() {
  super();
 }

 public User(String username, String password) {
  super();
  this.username = username;
  this.password = password;
 }

 public String getUsername() {
  return this.username;
 }

 public void setName(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 @Override
 public String toString() {
  return " Name: " + this.username +  " Password: " + this.password;
 }
   
}