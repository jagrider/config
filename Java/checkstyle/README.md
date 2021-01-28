# Vocareum Checkstyle guide

The following is a short guide on how to get Purdue's CS18000 Java Checkstyle working in Vocareum. A brief outline of the coding standards can be found in `_CodingStandards.pdf`


## Getting the libraries prepared

Copy the following files into the `LIB` folder in Vocareum:
* `checkstyle-vocareum.jar`
* `grading-tools-vocareum.jar`
* `checkstyle.xml`


## Setting up the RUN script
To allow students to run the Checkstyle before submitting their code, put the following code in the `run.sh` script in the `scripts` folder:

```
java -jar $LIB/checkstyle-vocareum.jar -c $LIB/checkstyle.xml *.java
```

This will allow students to run the Checkstyle see where they may be counted off for improper code style.


## Setting up for Grading

To use the Checkstyle as a part of the Students' grade for an assignment, there are a number of steps you need to take:
1. Under the `Assignments` tab in Vocareum, select your assignment. If you have not created your assignment yet, do this now.
2. After your assignment is selected, select the part you would like to configure. If there isn't a part already, click `Add part` and name it.
3. After selecting your part, a window will pop up. Click on the `Grading` tab. At the bottom underneath `Grading Rubric`, you should create 2 parts:
    * `JUnit`, with the total score out of 95
    * `Coding Style`, with the total score out of 5.
4. After adding the two grading sections, scroll to the bottom of the window that popped up and Save your changes. Then, close the popup.
5. Next, click the yellow `Configure Workspace` button.
6. Open up the `ASNLIB` folder. In here, you should add your Test Case file that will be used to evaluate the students' work.
7. With the `ASNLIB` folder selected, navigate towards the top of the file viewer and click `New -> File`. Name it `<AssignmentName>Grader.java`.
8. After creating the file, select it in the file viewer on the left hand side of the interface. Using the text editor in the middle of the interface, copy in the code from `GraderTest.java`, which can be found in the same directory as this README.
9. After doing this, there are two changes you need to make. They are as follows:
    * Change the class name to represent the name of the file (I.e., `MatrixGrader.java` --> `public class MatrixGrader`)
    * On `Line 20`: `double scoreWeight ... (<AssignmentTest>.class);`
        * Change `<AssignmentName>.class` to represent the name of your Test file. (I.e., `MatrixTest.class`)
        * You need to use the `.class` extension because the `.java` file will be compiled in the `Grading` and `Submission` scripts.
10. After you've done this, click on the `scripts` folder, then click on `grade.sh`. Type the following code, replacing all <---...---> sections with the files relevant to your assignment:
```
# OPTIONAL: Add code here to check for existence of all the required files

# Compile the assignment code. This is the (are) file (or files) that students will submit.
javac -d . <---AssignmentName--->.java

# Set up Vocareum library files.
javac -d . -cp .:$VLIB/java/voc-grader.jar:$VLIB/java/junit-4.12.jar:$VLIB/java/hamcrest-core-1.3.jar:$LIB/grading-tools-vocareum.jar $ASNLIB/<---AssignmentTest--->.java $ASNLIB/<---AssignmentGrader--->.java

# Run the Grader
java  -cp .:$VLIB/java/voc-grader.jar:$VLIB/java/junit-4.12.jar:$VLIB/java/hamcrest-core-1.3.jar:$LIB/grading-tools-vocareum.jar <---AssignmentGrader--->

# Clean .class files
rm *.class
```

11. After making the necessary changes for the above script, you should copy it and place the same code in the `submit.sh` script. This is the file that will get run when students submit their code.
