package app.pages;

import java.util.ArrayList;

public final class Page {
    private String name;
    private ArrayList<String> children;
    private ArrayList<String> availableActions;

    Page(final String name,
         final ArrayList<String> children,
         final ArrayList<String> availableActions) {
        this.name = name;
        this.children = children;
        this.availableActions = availableActions;
    }

    /**
     *
     * @param page The page being searched for
     * @return True if the current page has a link (can go) to the searched page, False otherwise
     */
    public boolean hasChild(final String page) {
        if (children == null) {
            return false;
        }

        for (String child : children) {
            if (child.equals(page)) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param action The action being searched for
     * @return True if action can be done on this page, False otherwise
     */
    public boolean hasAction(final String action) {
        if (availableActions == null) {
            return false;
        }

        for (String child : availableActions) {
            if (child.equals(action)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<String> children) {
        this.children = children;
    }

    public ArrayList<String> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(final ArrayList<String> availableActions) {
        this.availableActions = availableActions;
    }
}
