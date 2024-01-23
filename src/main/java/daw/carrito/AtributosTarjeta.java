package daw.carrito;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author acebedo
 */
public class AtributosTarjeta {

    private int numeroTarjeta;
    private LocalDate fechaVencimiento;
    private int cvv;
    private double saldo;
    private String nombreTitular;

    public AtributosTarjeta(int numeroTarjeta, LocalDate fechaVencimiento, int cvv, double saldo, String nombreTitular) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.saldo = saldo;
        this.nombreTitular = nombreTitular;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.numeroTarjeta;
        hash = 89 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 89 * hash + this.cvv;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.saldo) ^ (Double.doubleToLongBits(this.saldo) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.nombreTitular);
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
        final AtributosTarjeta other = (AtributosTarjeta) obj;
        if (this.numeroTarjeta != other.numeroTarjeta) {
            return false;
        }
        if (this.cvv != other.cvv) {
            return false;
        }
        if (Double.doubleToLongBits(this.saldo) != Double.doubleToLongBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.nombreTitular, other.nombreTitular)) {
            return false;
        }
        return Objects.equals(this.fechaVencimiento, other.fechaVencimiento);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AtributosTarjeta{");
        sb.append("numeroTarjeta=").append(numeroTarjeta);
        sb.append(", fechaVencimiento=").append(fechaVencimiento);
        sb.append(", cvv=").append(cvv);
        sb.append(", saldo=").append(saldo);
        sb.append(", nombreTitular=").append(nombreTitular);
        sb.append('}');
        return sb.toString();
    }

}
