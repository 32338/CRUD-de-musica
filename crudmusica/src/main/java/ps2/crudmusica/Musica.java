package ps2.crudmusica;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String compositor;
    private int ano;

    public Musica() {}

    public Musica(String titulo, String compositor, int ano) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getCompositor() { return compositor; }
    public int getAno() { return ano; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setCompositor(String compositor) { this.compositor = compositor; }
    public void setAno(int ano) { this.ano = ano; }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + compositor + ", " + ano + ")";
    }
}