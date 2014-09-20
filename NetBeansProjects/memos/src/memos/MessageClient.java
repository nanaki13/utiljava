/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;

import java.io.Serializable;

/**
 *
 * @author jonathan
 */
public class MessageClient implements Serializable{
    private String command;
    private Object[] args;
    private boolean needReponse;

    public MessageClient(String command, Object[] args) {
        this.command = command;
        this.args = args;
    }

    MessageClient(String commande, Object[] param, boolean needReponse) {
        this(commande, param);
        this.needReponse = needReponse;
    }

    public boolean isNeedReponse() {
        return needReponse;
    }

    public void setNeedReponse(boolean needReponse) {
        this.needReponse = needReponse;
    }

    
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
    
}
