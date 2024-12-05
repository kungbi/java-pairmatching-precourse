package pairmatching;

import pairmatching.config.DependencyInjector;
import pairmatching.controller.Controller;

public class Application {
    public static void main(String[] args) {
        DependencyInjector dependencyInjector = new DependencyInjector();
        Controller controller = dependencyInjector.getController();

        controller.run();
    }
}
