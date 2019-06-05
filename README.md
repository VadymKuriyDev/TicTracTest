# TicTacTest
Test repository

Core tasks:
• Fetch the data from http://media.tictrac.com/tmp/users.json in the app.
• Display the list of users with the name and profile picture for each user.
• Tapping on a row navigate to a details screen that shows the phone number in addition
to the name, profile picture and email of the user.

3.5 hours

Bonus tasks:
• Use a mechanism to persist the data for offline functionality.
• Click to enlarge profile image on details page.
• Add ability to call the telephone number provided.
• Add ability to send an email to the user from the details page with the pre-populated
subject line “Hey! This test is going pretty well”.

3.5 hours

Used retrofit, dagger, gson, RxJava, photoView (for image preview), Timber (for logging).


TODO

Also need implement DiffUtil or sorted list for users list
Also need implement ui states for fragments (when loaded, loaded complete and error)
Also need implement view model states (https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate) (now this logic is impl in fragments)
