import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        System.out.println("in app!");

        try {

            LabChallenge.UserLabRoles userLabRoles = new LabChallenge.UserLabRoles(1);

            System.out.println("username: " + userLabRoles.getUser().name);
            System.out.println("labs: " + userLabRoles.getLabs().stream().map( lab -> lab.name ).collect(Collectors.joining(",")));
            System.out.println("labRoles: " + userLabRoles.getLabRoles(1).stream().map( labRole -> labRole.role ).collect(Collectors.joining(",")));
            System.out.println("highestLabRole: " + userLabRoles.getHighestLabRole(1));
            System.out.println("hasLabRole: " + userLabRoles.hasLabRole(1, "OWNER"));

        } catch (Exception e) {
            System.out.println("Unable to lookup user, exiting.");
        }

    }


}
