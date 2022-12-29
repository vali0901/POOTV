package app.pages;

public final class PageHierarchy {
    private PageHierarchy() { }
    private static final Page LOGOUT = PageCreator.buildPage("logout");
    private static final Page MOVIES = PageCreator.buildPage("movies");
    private static final Page SEE_DETAILS = PageCreator.buildPage("see details");
    private static final Page HOMEPAGE_UNAUTH = PageCreator.buildPage("homepageUnauth");
    private static final Page HOMEPAGE_AUTH = PageCreator.buildPage("homepageAuth");
    private static final Page UPGRADES = PageCreator.buildPage("upgrades");
    private static final Page LOGIN = PageCreator.buildPage("login");
    private static final Page REGISTER = PageCreator.buildPage("register");

    /**
     *
     * @param name The name of the page that is needed
     * @return The page corresponding to the given page name
     */
    public static Page getPage(final String name) {
        return switch (name) {
            case "login" -> LOGIN;
            case "logout" -> LOGOUT;
            case "register" -> REGISTER;
            case "movies" -> MOVIES;
            case "upgrades" -> UPGRADES;
            case "see details" -> SEE_DETAILS;
            case "homepageAuth" -> HOMEPAGE_AUTH;
            case "homepageUnauth" -> HOMEPAGE_UNAUTH;
            default -> null;
        };
    }
}
