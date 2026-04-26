package org.spring.DTO;

import org.spring.enums.Actions;

public class UserEvent {
    private String email;
    private Actions action;

    public UserEvent() {
    }

    public UserEvent(String email, Actions action) {
        this.email = email;
        this.action = action;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAction() {
        return action.toString();
    }

    public void setAction(Actions action) {
        this.action = action;
    }
}