Start page with welcome and input of user name.
![Screenshot_1](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/138614ff-ca9f-4df3-b05e-b7dcf29a9ac1)
Username is required.
![Screenshot_2](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/e2e63dec-e8ba-4b31-91a5-99c64223255b)
![Screenshot_3](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/28d8e4c1-6230-4c7c-8674-bad6031f2ac7)
After entering the name, the text game starts. Question and two answer options.
![Screenshot_4](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/60c275a4-73f7-4656-aef8-f944d47aa618)
Choose an answer option and click the send button.
![Screenshot_5](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/3cc838f6-6657-463b-98e3-3a4dfa56c861)
![Screenshot_7](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/83ae120c-92bd-4e8d-b963-c151a11cce84)
If all the answers are correct, we get a window with congratulations on the victory on behalf of the user and with a button to start again.
![Screenshot_8](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/b70cf0db-8ebd-4afb-b674-33dc00fe5b32)
The start again button returns to the start page.
![Screenshot_9](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/68392ded-7b94-4da8-a772-6f5178bd9cb1)
Giving an incorrect answer, the user gets to the page of the end of the game.
![Screenshot_10](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/4ff34eed-6da8-4a28-8b8e-8b7cce0f2bc9)
![Screenshot_11](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/f9ac1aab-cebf-436b-a7c0-a372103eb381)
![image](https://github.com/DenisZarnitsyn/TextQuestGame/assets/140094236/270b6e67-dfa1-4cac-a66b-58cfdfbdb8f0)
"C:\Program Files\Java\jdk-19\bin\java.exe" -Dmaven.multiModuleProjectDirectory=C:\MyFiles\JavaProject\TextQuestGame -Djansi.passthrough=true "-Dmaven.home=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\plugins\maven\lib\maven3" "-Dclassworlds.conf=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\plugins\maven\lib\maven3\bin\m2.conf" "-Dmaven.ext.class.path=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\plugins\maven\lib\maven-event-listener.jar" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\lib\idea_rt.jar=50510:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\plugins\maven\lib\maven3\boot\plexus-classworlds-2.7.0.jar;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.1\plugins\maven\lib\maven3\boot\plexus-classworlds.license" org.codehaus.classworlds.Launcher -Didea.version=2023.3.2 test
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------< com.textquestgame:TextQuestGame >-------------------
[INFO] Building TextQuestGame 1.0
[INFO]   from pom.xml
[INFO] --------------------------------[ war ]---------------------------------
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ TextQuestGame ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 1 resource from src\main\resources to target\classes
[INFO] 
[INFO] --- compiler:3.8.1:compile (default-compile) @ TextQuestGame ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- resources:3.3.1:testResources (default-testResources) @ TextQuestGame ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 0 resource from src\test\resources to target\test-classes
[INFO] 
[INFO] --- compiler:3.8.1:testCompile (default-testCompile) @ TextQuestGame ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- surefire:3.1.2:test (default-test) @ TextQuestGame ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running game.GameProcessorTest
18:27:11.907 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:11.910 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
18:27:11.911 [main] INFO game.GameProcessor -- Congratulations! You won the game.
18:27:11.920 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:11.921 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
18:27:11.921 [main] INFO game.GameProcessor -- Congratulations! You won the game.
18:27:11.924 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:11.924 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
18:27:11.926 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:11.926 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
18:27:11.930 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:11.930 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
18:27:11.934 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:11.935 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.222 s -- in game.GameProcessorTest
[INFO] Running loading.QuestionLoaderTest
18:27:11.948 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.009 s -- in loading.QuestionLoaderTest
[INFO] Running models.OptionTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.007 s -- in models.OptionTest
[INFO] Running models.QuestionTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 s -- in models.QuestionTest
[INFO] Running servlets.GameOverServletTest
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.910 s -- in servlets.GameOverServletTest
[INFO] Running servlets.GameServletTest
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.031 s -- in servlets.GameServletTest
[INFO] Running servlets.StartGameServletTest
18:27:12.932 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:12.934 [main] INFO loading.QuestionLoader -- Successfully loaded the questions.json file.
18:27:12.934 [main] INFO game.GameProcessor -- GameProcessor initialized successfully.
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.013 s -- in servlets.StartGameServletTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 25, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.693 s
[INFO] Finished at: 2024-01-29T18:27:13+02:00
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0
