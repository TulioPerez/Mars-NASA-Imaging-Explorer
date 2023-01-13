# **Mars: NASA Imaging Explorer**

#### **Overview:**
###### This application displays images of Mars utilizing NASA's APOD API. It was built in Kotlin, uses Retrofit and Glide libraries to fetch & display images and has error handling functions to present error messages and allow re-fetching of data if an error occurs while fetching the data.

#### **Design Decisions:**
###### Retrofit & GsonConverterFactory are used for network requests and JSON data parsing. Glide was chosen for image parsing due to its efficiency and performance (almost twice as fast as Picasso) and for its built-in image caching abilities. Zoomage was used in the detail activity allowing pinch-to-zoom functionality as well as image translation from left to right. The application uses swipe-to-refresh functionality to allow user to re-fetch data when network or server errors occurs while fetching data. The application user AndroidX libraries.

#### **Classes and Methods:**
###### The application’s main classes include MainActivity, Adapter.class, ViewHolder.class, ApiInterface, DetailActivity and JsonData.class. The MainActivity is responsible for handling the UI and fetching the data using the ApiInterface class. The Adapter & ViewHolder classes are responsible for displaying the data in the RecyclerView and loading the images using Glide. The ApiInterface class is an interface used by Retrofit to define the endpoints for the API calls. The DetailActivity displays a zoom-able and translate-able image to the user along with a detailed description.

#### **Error Handling:**
###### The application uses a basic error handling mechanism to show a user-friendly error message when an error occurs while fetching the data. At this time, the error’s corresponding image view is displayed, and the user can swipe down to refresh the data. Currently, the application’s onFailure() override function handles fetching errors that occur when MainActivity initializes. 

#### **Testing:**
###### The application was tested on multiple devices and emulators with different screen sizes and resolutions to ensure compatibility and proper functionality. Unit tests were written to test the individual components and methods within the codebase using JUnit, Mockito & Mockk.

#### **API Documentation:**
###### This application uses the NASA's “APOD” public, RESTful API. The images displayed are the result of a search on the API’s data. A JSON object is returned containing data about images and metadata. The base URL for the API is "https://images-api.nasa.gov" and requires an API key for authentication, which is included in the retrofit call’s “URL” value. 

#### **Deployment:**
###### The application is built using Android Studio and can be deployed to any Android device running Android 4.4 or higher (min SDK 23). The application requests “INTERNET” permission to fetch data from the API as well as “ACCESS_NETWORK_STATE” for error checking during data fetching functionality.

#### **Screenshots:**
###### Screenshots of the application can be found in the screenshots folder of the repository. They demonstrate the main features of the application such as displaying images in a grid, swipe to refresh and error handling.

#### **Installation & Usage Instructions:**
###### To use the application, you will need to have Android Studio installed on your computer. Once you have cloned the repository, open the project in Android Studio and run the application on an emulator or an Android device.

#### **Future Features:**
###### Implement search feature to filter images by keywords
###### Implement feature to save images to the device
###### Implement RecyclerView on DetailActivity to allow for scrolling through detailed images 

#### **Changelog:**
###### v1.0: Initial release
