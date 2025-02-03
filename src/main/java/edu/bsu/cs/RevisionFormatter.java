package edu.bsu.cs;

import java.util.List;

public class RevisionFormatter {

    public void printRevisionList(List<Revision> revisionList){

        int count = 1;

        for(Revision revision:revisionList){
            System.out.printf("%d  %s",count,formatOutput(revision));
        }

    }

    protected String formatOutput(Revision revision){
        return String.format("%s  %s\n",revision.timeStampOfRevision,revision.name);
    }

}
