/*
  Titre      : ProjetLibrairie
  Auteur     : Crepin Vardin Fouelefack
  Date       : 20/10/2023
  Description: Utilisation de la librairie JSerial pour lire les donnees d'un uC
  Version    : 0.0.1
*/

// Source code modifier de:
//       https://github.com/Fazecast/jSerialComm/wiki/Nonblocking-Reading-Usage-Example

import com.fazecast.jSerialComm.*; // Importation la bibliotheque jSerialComm, qui permet de gerer les ports serie

public class App {
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[0]; // Obtient une reference au premier port serie disponible
        comPort.openPort();                               // Ouvre le port serie pour la communication

        try {
            while (true) {                             // Boucle pour lire en continu depuis le port serie
                while (comPort.bytesAvailable() == 0)  // Attend que des donnees soient disponibles sur le port serie
                    Thread.sleep(20); // Met en pause l'execution de ce thread pendant 20 milisec

                byte[] readBuffer = new byte[comPort.(bytesAvailable)];         // Cree un tampon pour stocker les donnees lues
                int numRead = comPort.readBytes(readBuffer, readBuffer.length); // Lit les donnees depuis le port serie

                String receivedData = new String(readBuffer, 0, numRead); // Converti les donnees lues en une chaine de caracteres
                System.out.println("Received: " + receivedData);                 // Affiche les donnees recues
            }
        } catch (Exception e) {       // Gere les exceptions qui pourraient se produire lors de la communication
            e.printStackTrace();     // Affiche la trace de la pile en cas d'erreur
        } finally {
            comPort.closePort();     // Ferme le port serie a la fin de l'execution ou en cas d'erreur
        }
    }
}
