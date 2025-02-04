package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final WikiConnection wikipediaConnection = new WikiConnection();
    private final RevisionParser parser = new RevisionParser();
    private final RevisionFormatter revisionFormatter = new RevisionFormatter();
    private final Scanner scnr = new Scanner(System.in);

    public void runMenu() throws IOException {
        System.out.print("Enter a title you would like revisions about:");
        String userInput = scnr.nextLine();
        InputStream wikiResponse = wikipediaConnection.search(userInput);
        List<Revision> revisionList = parser.parse(wikiResponse);
        revisionFormatter.printRevisionList(revisionList);
    }




}
