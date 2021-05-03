### lab-challenge

A timed code challenge for an unnamed company. This code snippet was setup inside a web browser and I had an hour and a half to complete and run against opaque tests using a "run now" button.

There are several data objects (User, Team, Lab) and several mapping objects (UserTeam, LabRole). Static data was provided.

I didn't save the original instructions but basically had to fill in these methods:

```java
public UserLabRoles(Integer userId)
```
Find a user by id, throw an exception if not found.

```java
public User getUser()
```
Return the user.

```java
public List<Lab> getLabs()
```
Return list of labs for said user.

```java
public List<LabRole> getLabRoles(Integer labId)
```
Return list of LabRoles for said user and param labId.

```java
public String getHighestLabRole(Integer labId)
```
Return highest role for user and labId. Role order is [OWNER, ADMIN, MEMBER, GUEST].

```java
public Boolean hasLabRole(Integer labId, String role)
```
Bonus method to return a role should a user have one for a lab, null if not.

### `./gradlew run`
There wasn't a way to test my code since it was inside a doofy web browser coding app, so I pulled it out, made a quick project and did some basic testing myself.

Enjoy!
