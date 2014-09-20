/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.interpretor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Command {
    String commandName;
    List<String> groupOptions;
    private List<String> finalArgs;
    public List<String> getFinalArgs(){
        return finalArgs;
    }

    public void setFinalArgs(List<String> finalArgs) {
        this.finalArgs = finalArgs;
    }
    

    public List<String> getStartArgs() {
        return groupOptions;
    }

    public void setGroupOptions(List<String> groupOptions) {
        this.groupOptions = groupOptions;
    }
    List<CommandEntry<String, String>> entry;
    public Command(){
        entry = new ArrayList<>(1);
        finalArgs = new ArrayList<>(2);
        groupOptions= new ArrayList<>(1);
    }
    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public List<CommandEntry<String, String>> getEntry() {
        return entry;
    }

    public void setEntry(List<CommandEntry<String, String>> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "Command{" + "commandName=" + commandName + ", groupOptions=" + groupOptions + ", finalArgs=" + finalArgs + ", entry=" + entry + '}';
    }

    
    
    
    
}
