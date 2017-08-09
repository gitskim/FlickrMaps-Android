# FlickrMaps-Android

Search for photos and display them on Google Maps by where they were taken!


## Built With

* [Flickr4Java](https://github.com/boncey/Flickr4Java)

* [Google Maps API for Android](https://developers.google.com/maps/documentation/android-api/)


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

#### Test Design

* There is typically one test file per class

  * Interfaces don't get tested.
 
  * Inner classes USUALLY don't get tested if the outer class is tested.
 
  * If you break up an outer class into seperate classes, you DON'T have to write new test UNLESS it's being used outside original context.
 
* Your tests shouldn't know everything!

* You should create tests that implement the SOLID principles to avoid fragile tests.
 
  * SRP: Every test, just like every class, should only have __ONE__ responsibility.
 
  * OCP: Production code should be open for extension but tests should be __closed__ from modification.
 
  * LSP: Don't make derivatives of classes that override certain things in orer to make your tests pass
 
  * ISP: If your tests use interfaces for methods they do not call, they have too much knowledge. 
 
  * DIP: The test code ALWAYS depends on the production code, never the other way around.
 
  * Don't test private functions - your tests would have too much knowledge.
 
  * If you change something in one test and that breaks another test(s), your tests are __fragile__.
 
  * If you change something in your production code and multiple tests break, your tests are __fragile__.
 
* You write the tests that forces you to write the code you already know you want to write

* TDD __does not__ mean no planning

* One test can possibly create more tests.

* The test naming convention should include the unit of test (GIVEN), state under test (WHEN), and expected output/behavior (THEN).

  * With multiple 'GIVENs,'you typically want to specify this before the actual testing.
 
#### Mocking (The Test Doubles)

* There are no 'pure' test doubles, they are abstract

  * Dummies: An interface that does nothing (most often used to test a function takes arguments but doesn't care for the output).
 
  * Stubs: Does nothing BUT returns something needed for the test (a fixed value).
 
  * Spies: Does nothing, returns something needed for the test, AND 'spies' on the way it was called to report its interactions with other objects such as number of calls, arguments passed, etc. We don't expect any certain outcome.
 
  * (True) Mocks: Similar to spies, except mocks check what it 'spied' on with what it expects to see.
 
  * Fake: A simulator of the real thing. Usually you want a suite of tests to test the fake.
 
* The 'Uncertainty Principle' of Unit Testing:

  * If you ignore the implementation of tests, you won't know if the system will behave the same for every input
 
  * If you ignore the outputs (spies) you can't refactor the algorithm used.
 
  * Mockists put emphasis on the use of spies to verify the implmentation of the alogirthms (Best for things that cross the dependency boundary).
 
  * Statists put emphasis on values returned by the functions (Best for non-crossing).
 
* Mocking patterns:

  * Test-specific subclass: Useful for testing a function in a class but need to modify the bheavior of other functions within that class
 
  * Self Shunt: The test becomes the mock.
 
  * Humble Object: 'Extracts' the contents of the boundary to reduce the logic close to the boundary (Useful for coundaries that are hard to test).
 

