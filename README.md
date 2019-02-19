# Social Media Service

### Test task.

The application is a microservice for a social network that maximize number of pairs based on shared interests.

### Contents
- [About](#about)
- [How it works](#how-it-works)
- [How to use](#how-to-use)


#### About
The application is a microservice that accepts as input JSON file with users and their interests. Example of input file can be found in the [test.json](https://github.com/vareyko-itechart/social-media-service/blob/master/test.json). Additionally a demo page with primitive interface is implemented in the app, that gives an ability to choose input file and to see results after submit.

#### How it works

Main algorithm that searches pairs:
- At first application will parse JSON.
In case if there is any syntax error in the provided JSON file exception will be thrown, which will be handled in [ControllerAdvice](https://github.com/vareyko-itechart/social-media-service/blob/master/src/main/java/com/example/social/controller/AdviceController.java)
- Then a map with possible pairs for each user will be generated.
Pairs will be generated only for users with common interests.
Pairs will be sorted inside of `TreeSet`<[PairDto](https://github.com/vareyko-itechart/social-media-service/blob/master/src/main/java/com/example/social/dto/PairDto.java)> by maximum number of common interests.
[PairDto](https://github.com/vareyko-itechart/social-media-service/blob/master/src/main/java/com/example/social/dto/PairDto.java) implements interface `Comparable`, but there is implemented custom `compareTo` method, it has the following difference: the method never returns 'equal' result, so all the elements will be added.
Map with pairs collected for each user will be converted to `TreeSet`<[PairEntryDto](https://github.com/vareyko-itechart/social-media-service/blob/master/src/main/java/com/example/social/dto/PairDto.java)>, which has the same comparator as explained above, but sorted by number of pairs for users (ascending).
- Finally, iterate over the all entries and collected pairs, excluding users that already participate in other pairs.

Algorithm is built to collect maximum number of pairs and only then to choose pairs with largest number of common interests.
There is not implemented `hashCode()` and `equals()` method for `UserDto`, which is used as key in some collections, because we work with instance created once and it will be enough to compare the links, otherwise these methods will be required.

#### How to use

Project is built with SpringBoot & Maven. There is excluded part for working with external storages such as databases.
Written with Java 8 (required Stream API).

Main logic placed in the [PairServiceImpl](https://github.com/vareyko-itechart/social-media-service/blob/master/src/main/java/com/example/social/service/impl/PairServiceImpl.java).
URL for sending your JSON file: `/upload`, it's expects multipart file.
URL with demo form and results: `/`

To run application use 
```
$ mvn spring-boot:run
```
or other tools (even IDE) for running spring boot applications.
