import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LabChallenge {

    // Data classes. Please do not change this code.
    public static class User {
        public Integer id;
        public String name;

        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Team {
        public Integer id;
        public String name;

        public Team(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Lab {
        public Integer id;
        public String name;

        public Lab(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class UserTeam {
        public Integer id;
        public Integer user;
        public Integer team;

        public UserTeam(Integer id, Integer user, Integer team) {
            this.id = id;
            this.user = user;
            this.team = team;
        }
    }

    public static class LabRole {
        public Integer id;
        public Integer team;
        public Integer user;
        public Integer lab;
        public String role;

        public LabRole(Integer id, Integer user, Integer team, Integer lab, String role) {
            this.id = id;
            this.team = team;
            this.user = user;
            this.lab = lab;
            this.role = role;
        }
    }

    // Example Data. Please do not change this code.
    public static List<User> USERS = Arrays.asList(
            new User(1, "John Doe")
    );

    public static List<Team> TEAMS = Arrays.asList(
            new Team(1, "Curiosity"),
            new Team(2, "Perseverance")
    );

    public static List<Lab> LABS = Arrays.asList(
            new Lab(1, "NASA"),
            new Lab(2, "JPL"),
            new Lab(3, "SpaceX"),
            new Lab(4, "Blue Origin")
    );

    public static List<UserTeam> USER_TEAMS = Arrays.asList(
            new UserTeam(1, 1, 2)
    );

    public static List<LabRole> LAB_ROLES = Arrays.asList(
            new LabRole(1, 1, null, 1, "OWNER"),
            new LabRole(2, 1, null, 2, "MEMBER"),
            new LabRole(3, null, 1, 2, "GUEST"),
            new LabRole(4, null, 1, 3, "MEMBER"),
            new LabRole(5, null, 2, 1, "ADMIN"),
            new LabRole(6, null, 2, 2, "ADMIN"),
            new LabRole(7, null, 2, 4, "GUEST")
    );

    // Implement this class without changing the function prototypes.
    public static class UserLabRoles {
        private User user;

        public UserLabRoles(Integer userId) throws Exception {
            user = USERS.stream().filter( u -> u.id == userId ).findAny().get();
            if (user == null)
                throw new Exception("User " + userId + " not found.");
        }

        public User getUser() {
            return user;
        }

        public List<Lab> getLabs() {
            ArrayList<Lab> labs = new ArrayList();

            // get lab roles for user
            Set<Integer> labIds = LAB_ROLES
                    .stream()
                    .filter( lr -> lr.user == user.id )
                    .map( lr -> lr.lab )
                    .collect(Collectors.toSet());

            // get all labs for labIds
            labs.addAll( LABS.stream().filter( l -> labIds.contains(l.id) ).collect(Collectors.toList()) );

            return labs;
        }

        public List<LabRole> getLabRoles(Integer labId) {
            ArrayList<LabRole> labRoles = new ArrayList();

            labRoles.addAll( LAB_ROLES
                    .stream()
                    .filter( labRole -> (labRole.user == user.id && labRole.lab == labId) )
                    .collect(Collectors.toList()));

            return labRoles;
        }

        public String getHighestLabRole(Integer labId) {
            HashMap<String, Integer> roleWeight = new HashMap<>();
            roleWeight.put("OWNER", 4);
            roleWeight.put("ADMIN", 3);
            roleWeight.put("MEMBER", 2);
            roleWeight.put("GUEST", 1);

            Integer highestWeight = 0;
            for (LabRole labRole : getLabRoles(labId)) {
                if (roleWeight.get(labRole.role) > highestWeight)
                    highestWeight = roleWeight.get(labRole.role);
            }

            String highestRole = null;
            for (Map.Entry<String, Integer> entry : roleWeight.entrySet()) {
                if (entry.getValue() == highestWeight)
                    highestRole = entry.getKey();
            }

            return highestRole;
        }

        // Bonus method
        // Only implement hasLabRole if time permits.
        public Boolean hasLabRole(Integer labId, String role) {
            return getLabRoles(labId)
                    .stream()
                    .filter(labRole -> labRole.role.equals(role))
                    .count() > 0;
        }
    }
}