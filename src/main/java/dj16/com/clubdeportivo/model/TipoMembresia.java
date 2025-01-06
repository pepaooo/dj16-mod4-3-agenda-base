package dj16.com.clubdeportivo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_membresia")
public class TipoMembresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_membresia")
    private Integer id;

    @Column(name = "nombre_tipo", length = 50)
    private String nombre;

    @Column(name = "tarifa")
    private Double tarifa;

    @Column(name = "duracion_dias")
    private Integer duracionDias;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    public Integer getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(Integer duracionDias) {
        this.duracionDias = duracionDias;
    }

    @Override
    public String toString() {
        return "TipoMembresia [id=" + id + ", nombre=" + nombre +
                ", tarifa=" + tarifa + ", duracionDias=" + duracionDias
                + "]";
    }
}
