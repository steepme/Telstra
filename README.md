# App Name: Telstra
This Android application is developed as a part of Telstra Assignment to assess proficiency, coding-style, library-awareness and approach.  This app displays a RecyclerView with all the list items populated from Json data received from a web service.

This app displays a Home screen in which a RecyclerView is embeded which displays the items in a list. The list item has a Title, Description and Image </br>

To feed this list, app communicates with the webservice and json data is fetched from https://dl.dropboxusercontent.com/u/746330/facts.json
 
For the attribute which is not available in the Json data, Title is displayed as "Title is not available", Description is displayed as "Description is not available" and default image with "Image not available" messages are shown 

App provides a refresh option when this option is selected from the menu, the app re-fetches the Json data from the webservice and refreshes the Screen.

This demo app supports both portrait and landscape mode and adapts to the display accordingly based on the current orientation of the android phone

# Libraries Used:
gson-2.2.4.jar </br>
volley.jar  </br>
picasso-2.5.2.jar </br>

# Supported Android Devices & Versions:
<b>Devices</b>: Android Phones & Tablets  
<b>Versions</b>: Kitkat, API Level 20 onwards
