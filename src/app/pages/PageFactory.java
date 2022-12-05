package app.pages;

import app.App;
import app.actions.Action;

import java.util.ArrayList;

public class PageFactory {
    public static Page buildPage(String name) {
        ArrayList<String> children;
        ArrayList<String> actions;
        switch (name) {
            case "login":
                actions = new ArrayList<>(1);
                actions.add("login");
                return new Page(name, null, actions);
            case "register":
                actions = new ArrayList<>(1);
                actions.add("register");
                return new Page(name, null, actions);
            case "movies":
                children = new ArrayList<>(3);

                children.add("homepageAuth");
                children.add("see details");
                children.add("logout");

                actions = new ArrayList<>(2);

                actions.add("search");
                actions.add("filter");

                return new Page(name, children, actions);
            case "see details":
                children = new ArrayList<>(4);

                children.add("homepageAuth");
                children.add("movies");
                children.add("upgrades");
                children.add("logout");

                actions = new ArrayList<>(4);

                actions.add("purchase");
                actions.add("watch");
                actions.add("like");
                actions.add("rate");

                return new Page(name, children, actions);
            case "upgrades":
                children = new ArrayList<>(2);

                children.add("movies");
                children.add("homepageAuth");

                actions = new ArrayList<>(2);

                actions.add("buy premium account");
                actions.add("buy tokens");

                return new Page(name, children, actions);
            case "logout":
                return new Page(name, null, null);
            case "homepageAuth":
                children = new ArrayList<>(3);

                children.add("movies");
                children.add("upgrades");
                children.add("logout");

                return new Page(name, children, null);
            case "homepageUnauth":
                children = new ArrayList<>(2);

                children.add("login");
                children.add("register");

                return new Page(name, children, null);

            default:
                    return null;
        }
    }
}
