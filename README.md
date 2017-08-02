# FlickrMaps-Android

Search for photos and display them on Google Maps by where they were taken!

## Unit Testing

This project was a learning experience with Unit Testing and JUnit

### What I've Learned

* __Arrange__: Set the system in a state to run various tests

* __Action__: Where we call the function(s) we want to test

* __Assertion__: The output of the test

* __Annihilate__: Put system to previous state before testing

#### Arrange
* There are two types of fixtures used by the xUnit architecture - Fresh and Shared

  * Fresh fixtures and initalized in every test
  
  * Transient fresh fixtures are created and destroyed every test (Typically encouraged as teardowns are not used)
  
  * Presistent fresh fixtures survive from test to test (Used to teardown any static variables)
  
  * Presistent shared fixtures are used to share common context used in most test (Destoryed after all tests are complete)


#### Hierarchy

Test functions can be grouped into hieracrchies. Setups are inherited from the outer classes.  The setup from the outer classes run first than the inner before the test function(s) run. 

In J-Unit, to create a hierarchy like this, you must used nested classes that can inherit any outer classes.


#### Action

* If you have multiple action functions, what you're REALLY testing is the results of the changes from those functions.

* Having multiple action functions in a test is not ideal - it's suggested to create a composed action function.

#### Assertion

* All test return ONE boolean value in the end no matter how many assert functions are used

* You may have multiple assertion functions but only one logical assertion is returned

* It is ideal most of the times to composed mulitple assertions into one composed assertion function


## Built With

* [Flickr4Java](https://github.com/boncey/Flickr4Java)

* [Google Maps API for Android](https://developers.google.com/maps/documentation/android-api/)

