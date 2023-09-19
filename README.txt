There is 2 main projects in this repository and some other things I tried in the OtherProjects folder, (effectively archived).

They are both in here because they both use Selenium Chrome Webdriver (https://www.selenium.dev/documentation/webdriver/) and it was easier to combine them then to reinstall the necessary jars for both.

These are both Magic The Gathering Related and automate some process:


MoxfieldLinkGeneration automates something some online tournament organizers wanted automated, so I created this project to automate it.

It opens each link in Original Moxfield Link.txt, makes a copy, and names the copy based on the corresponding item in userNames.txt, deckNames.txt, and hashes.txt. Then pastes the new link (with a slight change to embed it) into embeds.txt.

The main method for the program is in the same file (MoxfieldLinkGeneration), although I should relocate it next time I update it. Everything else should happen automatically, although I don't think it works if you switch screens.

___________________________________________________________________________________

MagicGeneratorProgram