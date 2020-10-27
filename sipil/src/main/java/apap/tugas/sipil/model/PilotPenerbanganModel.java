package apap.tugas.sipil.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pilotPenerbangan")
public class PilotPenerbanganModel implements Serializable{
    @Id
    @Max(20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="tanggalPenugasan", nullable = false)
    private LocalDate tanggalPenugasan;

    @ManyToOne
    @JoinColumn(name="idPilot")
    private PilotModel pilot;

    @ManyToOne
    @JoinColumn(name="idPenerbangan")
    private PenerbanganModel penerbangan;

    public PenerbanganModel getPenerbangan() {
        return penerbangan;
    }

    public void setPenerbangan(PenerbanganModel penerbangan) {
        this.penerbangan = penerbangan;
    }

    public PilotModel getPilot() {
        return pilot;
    }

    public void setPilot(PilotModel pilot) {
        this.pilot = pilot;
    }

    public LocalDate getTanggalPenugasan() {
        return tanggalPenugasan;
    }

    public void setTanggalPenugasan(LocalDate tanggalPenugasan) {
        this.tanggalPenugasan = tanggalPenugasan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}