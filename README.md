Summary group project. Web API whit connection to Android app. 

This allow to monitor daily user sport activity and quantity of consumed dishes.
On web site, user, after registration and login, can lead diary of own activity and consumed dishes. All activities and dishes user can choose from the menu. Application is showing information about the calories of each one and calculate the daily balance. 

Android part supply by other team member.

Who can use:
- admin
- registered and logged user 

- appUser data storage in database:
  userId, login, password, email, gender, height, dateOfBirth,lifestyle, collections: weight, nutritionHistory, activityHistory
- food examples storage in database 
    id, name, caloriesPerUnit, unit;
- activity examples storage in database
    id, activityType, caloriesPerHour, caloriesPerRep;

Technology: Java, Spring + Thymeleaf, MySQL (Workbench),

TODO:
- choose language: english / polish
- make daily/weakly/monthly user balance 
- ? confection with external API with data 
- admin can add food and activity position to database  
