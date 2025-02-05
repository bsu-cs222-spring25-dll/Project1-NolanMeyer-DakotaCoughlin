package edu.bsu.cs;

import java.util.List;

public class RevisionFormatter {

    public void printRevisionList(List<Revision> revisionList){

        int lineNumber = 1;

        for(Revision revision:revisionList){
            System.out.printf("%d  %s",lineNumber,formatOutput(revision));
            lineNumber++;
        }

    }

    protected String formatOutput(Revision revision){
        return String.format("%s  %s\n",revision.timeStampOfRevision,revision.name);
    }

}
