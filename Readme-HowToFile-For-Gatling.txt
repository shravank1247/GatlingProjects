Prerequisites
-------------------
JDK
javac -version

Scala
scala -version

Maven
mvn -v

IDE
any IDE that supports Scala programming
Eclipse, IntelliJ, Netbeans etc

Step 1 - Check JDK and Scala are installed on the system
Step 2 - Open IntelliJ and add Scala plugin
Step 3 - Create a Gatling Project
Option 1 - Create a new Project using Maven
Option 2 - Clone existing Project

Create Gatling Project
----------------------------------
Option 1 - Create a new Project using Maven
1.  Create a folder ＞ Open CMD ＞ navigate to folder ＞ run command mvn archetype:generate
2. Provide archetype gatling
3. Choose latest version
4. Define a groupId e.g. com.gatling.tests
5. Provide an artifactId e.g. GatlingProject
6. Open/Import project in IntelliJ IDEA and browse .pom file

Option 2 - Clone existing Project
1. Goto https://github.com/gatling/gatling-tu...
2. Clone or download
3. Open/Import project in IntelliJ IDEA

Step 4 - If you see message No Scala SDK in module ＞ Setup Scala SDK
If you have already installed Scala Binaries on your system then browser the folder
OR
Download and install Scala first on your system https://www.scala-lang.org/download/s...
Step 5 - In project Rt click on scala folder and Mark Directory as Test Sources Root
Step 6 - Can also mark src folder as Sources Root
Step 7 - Open the Engine class and rt click ＞ Run Engine
Check - There is no simulation script. Please check that your scripts are in user-files/simulations

Gatling - Record Test
-------------------------------
Step 1 - Demo of the scenario to test
Step 2 - On Chrome browser - More Tools ＞ Developer Tools - Goto Network tab
Step 3 - Clear any earlier logs, check Preserve log, start Recording network logs
Step 4 - Record the scenario
Step 5 - Save or Export HAR file
Step 6 - Goto Gatling Project ＞ Recorder class ＞ Rt click ＞ Run Recorder
  Recorder window opens
Change recorder mode to HAR Convertor
Browser and add HAR file
Provide package and class name
Click No Static Resources 
Start
Step 7 - Check the script in IDE 

Gatling Script Preparation
----------------------------------------
Step 1 - Open Gatling script in IDE
Step 2 - Remove Header Maps if any and their references in script
Step 3 - Update request names ＞ Save
Step 4 - Understand Gatling Script
Protocol Setup
Scenario Definition
Load Simulation Design

Gatling Script Execution
-----------------------------------
Step 1 - Goto Engine Class ＞ Rt click ＞ Run Engine 
Step 2 - Provide options as needed on the console
Step 3 - Check the report