
## MAPS
propertyname={key1:'value1',key2:'value2',....}
In your code.

@Value("#{${propertyname}}")  private Map<String,String> propertyname;
Note the hashtag as part of the annotation.



## STRINGS
Using Spring EL:

@Value("#{'${my.list.of.strings}'.split(',')}") 
private List<String> myList;
Assuming your properties file is loaded correctly with the following:

my.list.of.strings=ABC,CDE,EFG


## PLACEHOLDERS
https://www.logicbig.com/tutorials/spring-framework/spring-boot/properties-place-holders.html