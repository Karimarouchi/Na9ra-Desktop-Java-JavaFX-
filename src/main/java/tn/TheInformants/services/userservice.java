package tn.TheInformants.services;
import java.sql.SQLException;
import java.util.List;

public interface userservice <user>{

    void Ajouteruser (user t)throws SQLException;
    void Modiferuser (user t, int id)throws SQLException;
    void Supprimeruser (int user_id )throws SQLException;
    List<tn.TheInformants.entities.user> afficherUser();
}
