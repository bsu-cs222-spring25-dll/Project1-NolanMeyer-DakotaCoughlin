package edu.bsu.cs;

public class RevisionFormatter {

    public String formatOutput(Revision revision){
        return String.format("%s  %s\n",revision.timeStampOfRevision,revision.name);
    }
    //print method
}
