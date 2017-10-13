/*
 * il faut faire run clean and build project avy eo
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.nadia.database.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * cette classe retourne une connexion a une base de données MySQL elle
 * implemente le pattern Singleton(pour avoir une seule instance ça permet aussi
 * a un class d echanger un formation
 *
 * @author Administrateur
 */
public class DatabaseConnection {

    /**
     * variable destinée a stocker l'instance de la connexion
     */
    private static Connection instance;
    private static FileInputStream fis;

    /**
     * constructeur prive poue eviter de pouvoir instancier la classe depuis l
     * exterieur
     */
    public DatabaseConnection() {
    }///fin de constructeur

    public static Connection getIntance() throws SQLException {

        FileInputStream fis = null;
        try {
             //instanciantion d un objet properties qui contiendra la configuration

            Properties config = new Properties();
            //ouverture du fichier qui contient les info
            fis = new FileInputStream("./config/app.properties");
            //chargement des donnee du fichier dans l objet properties
            config.load(fis);
            fis.close();
            //recuperation des information de configuration dans les variables
            String dbHost = config.getProperty("db.host", "localhost");
            String dbName = config.getProperty("db.name", "bibliotheque");
            String dbUser = config.getProperty("db.user", "root");
            String dbPass = config.getProperty("db.pass", "");

//si l instance est nulle on instancie une nouvelle connexion
            if (instance == null) {
                instance = DriverManager.getConnection(
                        "jdbc:mysql://" + dbHost + "/" + dbName,
                        dbUser,
                        dbPass
                );

            }
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return instance;

    }

}/// fin de la class
