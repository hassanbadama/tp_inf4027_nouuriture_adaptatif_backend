package com.vente.systemeVentes.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// const [prenom, setPrenom] = useState("");
// const [email, setEmail] = useState("");
// const [password, setPassword] = useState("");
// const [tel, setTel] = useState("");
// const [sexe, setSexe] = useState("");

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String role;
    private String prenom;
    private String email;
    private String password;
    private String tel;
    private String sexe;
    private int age;
    private double poids;
    private double taille;
    private String objectif;
    private String regime;

    @ElementCollection
    private List<String> allergies;

    

    // public User(String nom, int age, double poids, double taille, String objectif, String regime,
    //         List<String> allergies) {
    //     this.nom = nom;
    //     this.age = age;
    //     this.poids = poids;
    //     this.taille = taille;
    //     this.objectif = objectif;
    //     this.regime = regime;
    //     this.allergies = allergies;
    // }
    

    public Long getId() {
        return id;
    }
    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    // Getters et Setters

    
}
