To build the application run following command at project root directory

$ ./gradlew build (Linux/Mac)

In case it throws error ./gradlew: command not found, it implies the gradlew script does not have execute permission. To fix the same execute following command

$ chmod +x ./gradlew

After successful build following artifact is generated which is a portable executable of the application and can be run anywhere.

build -> distributions -> onbon-led.zip

To deploy the application copy onbon-led.zip to target location and execute following commands

$ unzip onbon-led.zip

onbon-led folder would be extracted out as a result.

$ onbon-led/bin
$ ls
onbon-led  onbon-led.bat	// Scripts to execute the program

Finally to run the application execute following command

$ ./onbon-led

In case it throws error ./onbon-led: command not found, it implies the onbon-led script does not have execute permission. To fix the same execute following command

$ chmod +x ./onbon-led

Happy coding.....
