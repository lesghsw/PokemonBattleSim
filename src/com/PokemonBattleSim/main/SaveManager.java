package com.PokemonBattleSim.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe per gestire i salvataggi dei profili, contiene SAVE_FOLDER (Cartella dove verranno salvati i profili) e i metodi necessari per la gestione di questi ultimi.
 * <p>
 * I metodi sono statici, per cui possono essere chiamati senza creare un'istanza della classe
 * 
 * @author Aloisi2107981
 * 
 * @see File
 * @see ObjectOutputStream
 * @see ObjectInputStream
 * @see PlayerProfile
 */
public class SaveManager {
    private static final String SAVE_FOLDER = "ProfileSaves";

    /**
     * Metodo per salvare un profilo (Crea la cartella SAVE_FOLDER se non esiste)
     * 
     * @param profile Profilo da salvare
     * @param profileName Nome del profilo (Indicherà il nome del file di destinazione)
     */
    public static void savePlayerProfile(PlayerProfile profile, String profileName) {
        File folder = new File(SAVE_FOLDER);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Cartella '" + SAVE_FOLDER + "' creata.");
            } else {
                System.err.println("Impossibile creare la cartella di salvataggio '" + SAVE_FOLDER + "'.");
                return;
            }
        }

        String fileName = SAVE_FOLDER + File.separator + profileName + ".dat";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(profile);
            System.out.println("Profilo " + profileName + " salvato in " + fileName);
        } catch (IOException e) {
            System.err.println("Errore salvando profilo " + profileName);
            e.printStackTrace();
        }
    }

    /**
     * Metodo per caricare un profilo
     * 
     * @param profileName Nome del profilo (Indicherà il nome del file da caricare)
     * 
     * @return null se non è stato possibile caricare il profilo, altrimenti un'istanza di PlayerProfile contenente i valori del salvataggio caricato.
     * 
     * @see PlayerProfile
     */
    public static PlayerProfile loadPlayerProfile(String profileName) {
        String fileName = SAVE_FOLDER + File.separator + profileName + ".dat";
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            PlayerProfile profile = (PlayerProfile) inputStream.readObject();
            System.out.println("Profilo " + profileName + " caricato da " + fileName);
            return profile;
        } catch (FileNotFoundException e) {
            System.out.println("Profilo " + profileName + " non trovato. (" + fileName + " non esiste)");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Errore caricando profilo " + profileName);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Getter per ottenere tutti i file di salvataggio esistenti in SAVE_FOLDER
     * 
     * @return List contenente tutti i nomi dei file dei salvataggi
     * 
     * @see List
     */
    public static List<String> getAllProfileFileNames() {
        List<String> datFileNames = new ArrayList<>();
        File folder = new File(SAVE_FOLDER);
        
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("La cartella '" + SAVE_FOLDER + "' non esiste o non è una directory.");
            return datFileNames;
        }
        
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".dat"));
        if (files != null) {
            for (File file : files) {
                String name = file.getName();
                if (name.endsWith(".dat")) {
                    name = name.substring(0, name.length() - 4);
                }
                datFileNames.add(name);
            }
        }
        return datFileNames;
    }

    /**
     * Metodo per eliminare un profilo
     * 
     * @param profileName Nome del profilo da eliminare
     * @return True se il profilo è stato eliminato, False altrimenti
     */
    public static boolean deleteProfile(String profileName) {
        String fileName = SAVE_FOLDER + File.separator + profileName + ".dat";
        File file = new File(fileName);
        
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Profilo " + profileName + " eliminato da " + fileName);
                return true;
            } else {
                System.err.println("Errore eliminando il profilo " + profileName + " in " + fileName);
                return false;
            }
        } else {
            System.out.println("Profilo " + profileName + " non trovato. (" + fileName + " non esiste)");
            return false;
        }
    }
    
    /**
     * Metodo per controllare se un profilo esiste nella cartella SAVE_FOLDER
     * 
     * @param profileName Nome del profilo di cui verificare l'esistenza.
     * @return True se esiste, False altrimenti
     */
    public static boolean profileExists(String profileName) {
        String fileName = SAVE_FOLDER + File.separator + profileName + ".dat";
        File file = new File(fileName);
        
        return file.exists();
    }
}