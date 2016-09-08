# guice-property

Injecting environment variables and properties with Guice

## Why?

Using environment variables is a must for any meaningful project. Using Guice, we can actually directly inject the values of the
variables into our classes, without any clumsy `Configuration` class and alike.

This project does just that.

## How?

1. Add your variables into the `Property` enum, as many as you want:

        SERVER("MY_SERVER", "my.server", "localhost", "The server"),
        PORT("", "my.port", "1234", "The port");
    
    Basically this says the `Property.SERVER` property will be filled using:
    
    - First, the value of the `MY_SERVER` environment variable, if it exists,
    - second, the `my.server` property (passed with the `-D` flag), if it exists,
    - and finally, the default value `localhost` if none of the above is available.
         
    Feel free to customize this logic in `Property.getValue()`.
 
2. Directly inject each variable into your class using the `@Prop` annotation:

        import com.google.inject.Inject
        import phvu.prop.{Prop, Property}
        
        class PropertyUser @Inject()
        (@Prop(Property.SERVER) val server: String, @Prop(Property.PORT) val port: Int) {
        
          def run() = {
            println(s"I am on $server:$port")
          }
        }
    
    This is written in Scala, but using it in Java is very similar.
    
3. Use Guice to create the injector:

        Guice.createInjector(new PropertyModule).getInstance(classOf[PropertyUser]).run()
    
    Don't worry about binding. Those are handled for you.

## How to test?

    $ sbt clean compile package assembly
    $ MY_SERVER="fooooooooo" java -Dmy.server="mystupidserver" -Dmy.port=342456456 -jar target/scala-2.11/guice-properties-assembly-1.0.jar
    
    I am on fooooooooo:342456456

# References

Inspired by [this](http://beust.com/weblog/2013/07/12/flexible-configuration-with-guice/), I wanted to re-make the whole project in Scala, but there are some limitation in the way Scala handle annotations, so I had to resorted back to Java.
  
I also used `bindConstant()` instead of `bind()` to have Guice automatically do type-casting.
