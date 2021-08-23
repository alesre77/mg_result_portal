package cz.cmgs.mgor.entity;

import cz.cmgs.mgor.utils.Utils;
import lombok.Data;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "registred_player")
public @Data class RegistredPlayer {

    @Id
    @SequenceGenerator(name = "seqRegistredPlayer", sequenceName = "seq_registred_player")
    private Long id;

    @Column(name = "REG_NR")
    private int regNr;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "REG_DATE")
    private Date regDate;

    public String getFormattedBirthDate() {
        return Utils.formatDate(birthDate);
    }

    public static Comparator<RegistredPlayer> getComparator(String column, String sortDir) {
        if (column.equalsIgnoreCase("lastName")) {
            if (sortDir.equalsIgnoreCase("desc")) {
                return Comparator.comparing(RegistredPlayer::getLastName).reversed();
            } else {
                return Comparator.comparing(RegistredPlayer::getLastName);
            }
        }

        if (column.equalsIgnoreCase("regNr")) {
            if (sortDir.equalsIgnoreCase("desc")) {
                return Comparator.comparing(RegistredPlayer::getRegNr).reversed();
            } else {
                return Comparator.comparing(RegistredPlayer::getRegNr);
            }
        }

        return Comparator.comparing(RegistredPlayer::getRegNr);
    }
}
