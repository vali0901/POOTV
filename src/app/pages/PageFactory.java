package app.pages;

import java.util.ArrayList;

public final class PageFactory {

    private PageFactory() { }

    /**
     *
     * @param name The name of the page that will be built
     * @return The page object corresponding to the given name (Null if the name is wrong)
     */
    static Page buildPage(final String name) {
        ArrayList<String> children;
        ArrayList<String> actions;
        switch (name) {
            case "login" -> {
                actions = new ArrayList<>(1);
                actions.add("login");
                children = new ArrayList<>(1);
                children.add("login");
                return new Page(name, children, actions);
            }
            case "register" -> {
                actions = new ArrayList<>(1);
                actions.add("register");
                children = new ArrayList<>(1);
                children.add("register");
                return new Page(name, children, actions);
            }
            case "movies" -> {
                children = new ArrayList<>(4);
                children.add("homepageAuth");
                children.add("see details");
                children.add("logout");
                children.add("movies");
                actions = new ArrayList<>(2);
                actions.add("search");
                actions.add("filter");
                return new Page(name, children, actions);
            }
            case "see details" -> {
                children = new ArrayList<>(5);
                children.add("homepageAuth");
                children.add("movies");
                children.add("upgrades");
                children.add("logout");
                children.add("see details");
                actions = new ArrayList<>(4);
                actions.add("purchase");
                actions.add("watch");
                actions.add("like");
                actions.add("rate");
                return new Page(name, children, actions);
            }
            case "upgrades" -> {
                children = new ArrayList<>(3);
                children.add("movies");
                children.add("homepageAuth");
                children.add("upgrades");
                actions = new ArrayList<>(2);
                actions.add("buy premium account");
                actions.add("buy tokens");
                return new Page(name, children, actions);
            }
            case "logout" -> {
                return new Page(name, null, null);
            }
            case "homepageAuth" -> {
                children = new ArrayList<>(4);
                children.add("movies");
                children.add("upgrades");
                children.add("logout");
                children.add("homepageAuth");
                return new Page(name, children, null);
            }
            case "homepageUnauth" -> {
                children = new ArrayList<>(3);
                children.add("login");
                children.add("register");
                children.add("homepageUnauth");
                return new Page(name, children, null);
            }
            default -> {
                return null;
            }
        }
    }
}
