package ru.ifmo.ctddev.solutions.bank;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private final static int PORT = 8888;

    public static void main(String[] args) {
        Bank bank = new BankImpl(PORT);
        try {
            UnicastRemoteObject.exportObject(bank, PORT);
            Naming.rebind("//localhost/bank", bank);
        } catch (RemoteException e) {
            System.out.println("Cannot export object: " + e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        }
        System.out.println("Server started");
    }
}
