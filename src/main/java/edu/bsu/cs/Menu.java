package edu.bsu.cs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final WikiConnection wikipediaConnection = new WikiConnection();
    private RevisionParser parser;
    private final RevisionFormatter revisionFormatter = new RevisionFormatter();
    private final Scanner scnr = new Scanner(System.in);

    public void runMenu() throws IOException {
        System.out.print("Enter a title you would like revisions about:");
        String userInput = scnr.nextLine();
        inputSearch(userInput);

    }
    public void inputSearch(String userInput) throws IOException {
        InputStream wikiResponse = wikipediaConnection.search(userInput);
        parser = new RevisionParser(new RevisionInputStream(wikiResponse));
        List<Revision> revisionList = parser.parse();
        System.out.printf("Redirected to %s\n",parser.extractRedirect(new ByteArrayInputStream(parser.inputStream.inputStream)));
        revisionFormatter.printRevisionList(revisionList);
    }
}
