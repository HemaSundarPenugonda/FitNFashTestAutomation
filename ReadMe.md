## Software required for setting up this project:
	1. Install Java7 on your system and update path variable
	2. Install Eclipse 
	3. if you want to execute from command line: Install Maven on your system and update path variable
	4. if you want to execute from eclipse: Install Eclipse-Maven Plug-in an Install Eclipse-TestNG plug-in
	6. To get source code: Install Git SCM on your system for Git Command line
	
## Test cases available for execution:
	1. testcase1 --> Book a dress with alternative - Success payment
	2. testcase2 --> Book a dress with alternative - Fail payment
	3. testcase3 --> Book a dress without Alternative - Success payment
	4. testcase4 --> Book a dress without Alternative - Fail payment
	5. testcase5 --> Book a dress with demo and with alternative - Success payment
	6. testcase6 --> Book a dress with demo and without Alternative - Success payment
	7. testcase7 --> Book a dress with demo and with alternative - Fail payment
	8. testcase8 --> Book a dress with demo and without Alternative - Fail payment

## How to execute test cases:
	1. From Command line:
		1. Open Terminal and CD to project root directory
		2. To execute all test cases, enter following command and click enter:
			$ mvn clean test -Dtestcase=alltestcase
		3. To execute a specific test case, enter following command and click enter:
			$ mvn clean test -Dtestcase=testcase1
			
## Setup headless browser
	1. Install vnc server on jenkins slaves where you want to execute the headless test cases
	2. Install Xvnc plugin in jenkins
	3. In jenkin's job configure page, select Xvnc option.

## How to see the execution going on in browser?
	1. In Jenkins job's console log page, you can check the display port number alloted for the current build execution.
	2. Connect to the display through VNC viewer.
		syntax: <serverAddress>:59<displayNumber>
