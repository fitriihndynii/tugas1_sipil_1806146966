package apap.tugas.sipil.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="penerbangan")
public class PenerbanganModel implements Serializable{
    @Id
    @Max(20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=16)
    @Column(name="kode", nullable = false)
    private String kode;

    @NotNull
    @Size(max=255)
    @Column(name="kotaAsal", nullable = false)
    private String kotaAsal;

    @NotNull
    @Size(max=255)
    @Column(name="kotaTujuan", nullable = false)
    private String kotaTujuan;

    @NotNull
    @Column(name="waktu", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm a")
    private LocalDateTime waktu;

    @OneToMany(mappedBy = "penerbangan")
    private Set<PilotPenerbanganModel> listPilotPenerbangan;

    public Set<PilotPenerbanganModel> getListPilotPenerbangan() {
        return listPilotPenerbangan;
    }

    public void setListPilotPenerbangan(Set<PilotPenerbanganModel> listPilotPenerbangan) {
        this.listPilotPenerbangan = listPilotPenerbangan;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDateTime waktu) {
        this.waktu = waktu;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
