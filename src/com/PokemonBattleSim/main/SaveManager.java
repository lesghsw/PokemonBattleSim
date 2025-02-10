package com.PokemonBattleSim.main;

import java.io.*;

public class SaveManager {
    public static void savePlayerProfile(PlayerProfile profile, int profileSlot) {
        if (profileSlot < 1 || profileSlot > 3) {
            System.out.println("Slot profilo non valido. Usare 1, 2, o 3.");
            return;
        }
        String fileName = "profile" + profileSlot + ".dat";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(profile);
            System.out.println("profilo" + profileSlot + " salvato in " + fileName);
        } catch (IOException e) {
            System.err.println("Errore salvando profilo" + profileSlot);
            e.printStackTrace();
        }
    }

    public static PlayerProfile loadPlayerProfile(int profileSlot) {
        if (profileSlot < 1 || profileSlot > 3) {
            System.out.println("Slot profilo non valido. Usare 1, 2, o 3.");
            return null;
        }
        String fileName = "profile" + profileSlot + ".dat";
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            PlayerProfile profile = (PlayerProfile) inputStream.readObject();
            System.out.println("profilo " + profileSlot + " caricato da " + fileName);
            return profile;
        } catch (FileNotFoundException e) {
            System.out.println("profilo " + profileSlot + " non trovato. (" + fileName + " non esiste)");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Errore caricando profilo" + profileSlot);
            e.printStackTrace();
        }
        return null;
    }
}