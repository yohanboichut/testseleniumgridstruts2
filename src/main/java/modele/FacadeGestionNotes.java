package modele;

import java.util.Collection;

public interface FacadeGestionNotes {
    /**
     * Permet à un professeur connu du SI de se connecter à l'application
     * @param email
     * @param motDePasse
     * @return clé d'authentification aléatoire
     * @throws IdentifiantsIncorrectsException
     */
    String connexion(String email, String motDePasse)
            throws IdentifiantsIncorrectsException;

    /**
     * Retourne la liste des libellés des enseignements où le professeur est impliqué
     * @param cle : clé de connexion du professeur
     * @return
     * @throws CleInexistanteException : la clé d'authentification n'est pas valide
     */
    Collection<Enseignement> getEnseignements(String cle)
            throws CleInexistanteException;

    /**
     * Permet de saisir une note d'un étudiant
     * @param cle            : clé de l'enseignant connecté
     * @param nomEnseignement      : nom de l'enseignement concerné
     * @param numeroEtudiant : numéro de l'étudiant concerné
     * @param note           : note de l'étudiant
     * @throws CleInexistanteException     : la clé d'authentification n'est pas valide
     * @throws EtudiantInexistantException : le numéro d'étudiant n'est pas connu
     */

    void saisirNote(String cle, String nomEnseignement,
                    String numeroEtudiant, double note)
            throws CleInexistanteException, EtudiantInexistantException;

    /**
     * Permet de récupérer les notes saisies pour un enseignement
     *
     * @param cle       : clé de l'enseignant connecté
     * @param nomEnseignement : nom de l'enseignement concerné
     * @return
     * @throws CleInexistanteException : la clé d'authentification n'est pas valide
     */

    Collection<Note> getNotesEnseignement(String cle, String nomEnseignement)
            throws CleInexistanteException;

    /**
     * Permet de récupérer la liste des étudiants suivant un enseignement
     *
     * @param cle
     * @param nomEnseignement
     * @return
     * @throws CleInexistanteException : la clé d'authentification n'est pas valide
     */
    Collection<Etudiant> getEtudiantsEnseignement(String cle, String nomEnseignement) throws CleInexistanteException;

    /**
     * Permet de déconnecter l'utilisateur concerné par la clé
     *
     * @param cle : clé d'authentification
     */
    void deconnexion(String cle);
}
