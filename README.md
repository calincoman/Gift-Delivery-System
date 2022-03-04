#Gift Delivery System

## Package Structure and Class Description

* checker
    * program checker files
* common
    * **Constants** - class containing constant values used in the program.
    * **Utils** - utility class containing static methods used in the program
* database
    * **Database** - *singleton* class which stores the data used in the program.
    * **DatabaseLoader** - class that loads and unloads the database.
    * **DatabaseSearch** - class used for searching data (gifts, children etc.) from the database.
    * **DatabaseUpdate** - class used for updating data from the database (adding new gifts, 
      updating children data etc.)
* distribution
    * recipient
        * **Person** - *abstract* class which represents the receiver of the gifts
        * **Child** - extends Person and defines a child, uses the *Builder* pattern for creating
                        an object of type Child and is also a *Visitable* (it is visited by elves)
    * shipment
        * **Product** - *abstract* class which represents the product that is delivered
        * **Gift** - extends Product and defines a gift
* enums
    * **AgeCategory** - enum representing the age categories of a child
    * **Category** - enum representing the gift categories
    * **Cities** - enum representing the cities where gifts are being delivered
    * **GiftStrategyType** - enum representing the gift assigning strategies
    * **ElvesType** - enum representing the types of elves (yellow, white etc.)
    * **ScoreStrategyType** - enum representing the types of score strategies
* factory
    * **ElvesFactory** - factory class used for creating Elf objects
    * **GiftStrategyFactory** - factory class used for creating gift assigning strategy objects
    * **ScoreStrategyFactory** - factory class used for creating score strategy objects
* fileio
    * contains file input / output logic (JSON parsing, data read and write etc).
    * input
        * **AnnualChangeInputData**, **ChildInputData** etc. - classes containing input data
            if various objects
        * **InputFormat** - class in which the data is first stored when reading from the JSON
                  files, is built to allow input parsing with Jackson library. After reading, the
                  data is extracted to the InputData class
        * **InputData** - class containing the entire input data from the input files (gift input
            data, children input data etc.).
        * **InputLoader** - class which reads the input file and extracts the data to the
                  InputData class
        * **InputParser** - class which handles the JSON input parsing using Jackson library
    * output
        * **AnnualListOutputFormat**, **OutputFormat** - output format classes used only for
               printing to JSON files using Jackson library
        * **OutputData** - class which contains all output data, which is transferred to the
              OutputFormat class for printing
        * **OutputWriter** - class which handles the JSON output writing using Jackson library
* main
    * **Main** - the entry point of the program (the main method).
* solve
    * contains the main logic which combines all classes
    * **Calculator** - class which handles math calculations (calculates the budget assigned
            to each child, the average score sum etc.) 
    * **OutputDataBuilder** - class used for creating the output data
    * **Solver** - class which runs through all the tests and calls all helper classes (input
               /output, database loading/unloading etc.)
    * **YearCounter** - *singleton* class which keeps the evidence of the current year
* status
    * change
        * **AnnualChange** - class which holds data for an annual change
    * update
        * **ChildUpdate** - class which holds data for a child update
* strategy
    * contains the strategy classes used in the Strategy design pattern and comparator classes
        used for sorting
    * comparator
      * contains classes which implement the Comparator interface and are used for comparing Child
            objects
      * **CityNameComparator** - compares two children by their city names
      * **CityScoreComparator** - compares two children by the score of their city
      * **IdComparator** - compares two children by their ID
      * **NiceScoreComparator** - compares two children by their nice score
      * **MultiComparator** - generic comparator class which combines multiple comparators
                            in order to sort after multiple criteria
    * delivery
      * **GiftAssigningStrategy** - interface for the gift assigning strategy, is implemented
                                 by the concrete gift assigning strategy classes
      * **ChildNiceScoreStrategy** - class for the child nice score gift assigning strategy
      * **CityNiceScoreStrategy** - class for the city nice score gift assigning strategy
      * **IdGiftStrategy** - class for the child id gift assigning strategy
    * score
      * **ScoreStrategy** - interface which is implemented by the strategy classes
      * **BabyScoreStrategy**, **KidScoreStrategy** etc. - strategy classes which override
          the getAverageScore method of the interface and each use the formula specific
          to the age group (baby, kid etc.)
* visitor
    * contains the Visitor Elf classes and other classes used for applying the *Visitor* pattern
    * **Visitor** - interface defining the Visitor classes
    * **Visitable** - interface defining the Visitable classes
    * **Elf** - interface defining an Elf
    * **BudgetElf** - abstract class defining elves which modify the child budget
    * **GiftElf** - abstract class defining elves which offer gifts to children
    * **ElvesOperations** - class used for managing the elves operations
    * type
      * **PinkElf**, **BlackElf**, **YellowElf** - classes representing the elves

## Design Patterns and Implementation Details

* The Database is using the *Singleton* pattern with lazy instantiation, only one instance
  is created throughout the execution of the program. It holds all data used throughout
  the program. YearCounter is also a singleton class.

* The *Strategy* pattern is used for calculating the average score of a child, with one strategy
  for each age group, since each age group has a different formula of calculating it. It is also
  used for the gift assignment methods.

* The *Factory* pattern is used for facilitating the interaction with the strategy classes,
  since it creates strategy class objects based on the strategy type given as parameter.

* The *Builder* pattern is used for creating **ChildOutputData** objects, since the child
  output data contains in addition to the base info which is mandatory (name, age etc) some
  additional info (nice score history, received gifts etc). This helps for greater extensibility
  of the code if we want to output other info or leave some fields out. It is also used for 
  creating **Child** objects.

* The *Visitor* pattern is used in the relationship between the **Elf** and **Child** objects, i.e.
  the Elves are *Visitor* Objects which visit the children (*Visitable*), either modifying their
  budget or offering them gifts. This makes the code more extensible and simplifies the addition
  of new elves.

* Streams are used in most parts of the code to reduce the size and simplify the implementation.

* Input and output reading/printing from JSON files is done using Jackson library. InputFormat
  and OutputFormat are helper classes in which the input/output is done, with the data being
  transferred to the InputData / from the OutputData classes.

## Flow of the Program

Entry point:
* Main class - runs the program

How it works:
* The Solver goes through all tests and executes the following steps for each one of them:

* Read the input using the *InputLoader*.
* Load the input data in the database using the *DatabaseLoader*.
* Simulate the gift assignment for the first year (round 0) and the next ones.
* For each year starting from year 1, use the *DatabaseUpdate* class methods to update the
  database.
* For each year including year 0, calculate the child budgets, send the budget elves, then use the
  *OutputDataBuilder* class to create the output and assign the gifts by the strategy specified
  in that year. Then, send the gift elves to offer gifts to children who have not received any.
* In the process of "sending" the elves, the *Visitor* pattern is used, the *Child* objects being
  visited by the *Elf* objects.
* After the output data for all years was loaded in the database, use the OutputWriter to write
  in the JSON output file.
* Unload the data from the database.

