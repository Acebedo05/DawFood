package daw.tpv;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author acebedo
 */
public class FuncionesTPV {
    
    private String ubicacion;
    private LocalDateTime fechahoraSistema;
    public UUID id = UUID.randomUUID();
    // sout(uuid);

    public FuncionesTPV(String ubicacion, LocalDateTime fechahoraSistema, UUID id) {
        this.ubicacion = ubicacion;
        this.fechahoraSistema = fechahoraSistema;
        this.id = id;
    }

    public FuncionesTPV() {
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFechahoraSistema() {
        return fechahoraSistema;
    }

    public void setFechahoraSistema(LocalDateTime fechahoraSistema) {
        this.fechahoraSistema = fechahoraSistema;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.ubicacion);
        hash = 53 * hash + Objects.hashCode(this.fechahoraSistema);
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FuncionesTPV other = (FuncionesTPV) obj;
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.fechahoraSistema, other.fechahoraSistema)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FuncionesTPV{");
        sb.append("ubicacion=").append(ubicacion);
        sb.append(", fechahoraSistema=").append(fechahoraSistema);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
    
}