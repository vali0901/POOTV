package app.pages;

import app.actions.Action;

import java.util.ArrayList;

public class Page {
    private String name;
    private ArrayList<String> children;
    private ArrayList<String> availableActions;

    public Page(String name, ArrayList<String> children, ArrayList<String> availableActions) {
        this.name = name;
        this.children = children;
        this.availableActions = availableActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }

    public ArrayList<String> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(ArrayList<String> availableActions) {
        this.availableActions = availableActions;
    }

    @Override
    public String toString() {
        return "Page{" +
                "name='" + name + '\'' +
                ", children=" + children +
                ", availableActions=" + availableActions +
                '}';
    }

    public boolean hasChild(String name) {
        if(children == null)
            return false;

        for(String child : children) {
            if(child.equals(name))
                return true;
        }
        return false;
    }

    public boolean hasAction(String name) {
        if(availableActions == null)
            return false;

        for(String child : availableActions) {
            if(child.equals(name))
                return true;
        }
        return false;
    }
}
//
//class HomepageUnlogged extends Page {
//
//}
//
//class LoginPage extends Page {
//
//}
//
//class RegisterPage extends Page {
//
//}
//
//
//class HomepageLogged extends Page {
//
//}
//
//class MoviesPage extends Page {
//
//}
//
//class SeeDetailsPage extends Page {
//
//}
//
//class UpgradesPage extends Page {
//
//}
//
//class LogoutPage extends Page {
//
//}

