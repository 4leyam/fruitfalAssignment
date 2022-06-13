# fruitfalAssignment


- Use Kotlin/ MVVM architecture for this project
- Use Github API V3 (https://docs.github.com/en/rest/reference/commits​) as a reference for the api needed for the task
- You will have to use two separate APIs for fetching the details, but you will need only a few of the fields returned in the response.

# Task


<br>



Screen-1

Create an endless recyclerView, using Jetpack Paging Library, that displays the recent commits of the retrofit repository (https://github.com/square/retrofit) by author in a view.
Author
Short SHA-1 hash (Only the first 8 chars)
Message of the commit
It could look something like this (https://github.com/donnfelker/example-android-challenge/blob/master/example.jpg).
You are free to use any other design of your liking.

Screen-2

On clicking a single item of the list, using Jetpack Navigation Library, navigate to a second screen to show a few more details of the commit:
Author
Date (in IST)
SHA-1 hash (full hash)
Message
The number of files changed
Total number of changes

<br>

-----------
