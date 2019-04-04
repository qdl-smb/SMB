package ch.hearc.sandbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    @Size(min = 1, max = 30)
    private String nom;

    @NotNull
    @Size(min = 1, max = 300)
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Board board;

    public Post(String nom, String contenu, Board board) {
        this.nom = nom;
        this.contenu = contenu;
        this.board = board;
    }

    public Post() {
        this("", "", new Board());
    }

    public Post(Board board) {
        this("", "", board);
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
