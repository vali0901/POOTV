package app.pages;

public class PageHierarchy {
    private static final Page logout = PageFactory.buildPage("logout");
    private static final Page movies = PageFactory.buildPage("movies");
    private static final Page seeDetails = PageFactory.buildPage("see details");
    private static final Page homepageUnauth = PageFactory.buildPage("homepageUnauth");
    private static final Page homepageAuth = PageFactory.buildPage("homepageAuth");
    private static final Page upgrades = PageFactory.buildPage("upgrades");
    private static final Page login = PageFactory.buildPage("login");
    private static final Page register = PageFactory.buildPage("register");

    public static Page getPage(String name) {
        return switch (name) {
            case "login" -> login;
            case "logout" -> logout;
            case "register" -> register;
            case "movies" -> movies;
            case "upgrades" -> upgrades;
            case "see details" -> seeDetails;
            case "homepageAuth" -> homepageAuth;
            case "homepageUnauth" -> homepageUnauth;
            default -> null;
        };
    }
}
