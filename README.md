## How to compile and run
- **1.** Download the repository to your computer.
- **2.** Open the console and go to the directory **_console-experience_** of the downloaded repository from the item **1** : **_cd [directory path]_**, in my case: **_cd d:/leverex-java-lab/console-experience_**
- **3.** Write the following command to the console: **_javac -d bin src/by/khmel/*.java_** thereby we compile java files and create a folder **_bin_** (with a key **_-d_**) where the compiled java files go.
- **4** Now, to run our compiled files, write to the console: **_java -cp bin by.khmel.Main_** (**_-cp_** - a key after which you should specify the location of the compiled classes).
## How to build and run jar-file
- **1.** Execute subitems **1-3** from item **How to compile and run**or just continue if you started from item **How to compile and run**.
- **2.** Write the following command to the console: **_jar -c -f application.jar -e by.khmel.Main -C bin ._** where **_-с_ (--create)_** - create jar-file,  **_-f (--file)_** a key followed by the jar-file's name, **_-e (--main-class)_** - it's entry point, after this key, we indicate the entry point to the program, **_-С_** - a key after which we write the path to the compiled files, **.** - the path where the jar-file will be created.
- **3.** To run the created jar-file, write to the console: **_java -jar application.jar_**
