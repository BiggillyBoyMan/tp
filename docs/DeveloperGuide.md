---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project from [SE-EDU initiative](https://se-education.org), but has evolved into **BizBook**, a desktop app for managing internship applications.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams are in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same companyName as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g. `CommandBox`, `ResultDisplay`, `ApplicationListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Application` objects residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a internshipApplication).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command companyName e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the internship application data i.e., all `Application` objects (which are contained in a `UniqueApplicationList` object).
* stores the currently 'selected' `Application` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Application>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user's preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, such as internship applications, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in BizBook, which `Application` references. This allows BizBook to only require one `Tag` object per unique tag, instead of each `Application` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/description/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both BizBook data (internship applications) and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `BizBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.description.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedBizBook`. It extends `BizBook` with an undo/redo history, stored internally as an `bizBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedBizBook#commit()` — Saves the current BizBook state in its history.
* `VersionedBizBook#undo()` — Restores the previous BizBook state from its history.
* `VersionedBizBook#redo()` — Restores a previously undone BizBook state from its history.

These operations are exposed in the `Model` interface as `Model#commitBizBook()`, `Model#undoBizBook()` and `Model#redoBizBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches BizBook for the first time. The `VersionedBizBook` will be initialized with the initial internship application state, and the `currentStatePointer` pointing to that single BizBook state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th internship application in BizBook. The `delete` command calls `Model#commitBizBook()`, causing the modified state of BizBook after the `delete 5` command executes to be saved in the `bizBookStateList`, and the `currentStatePointer` is shifted to the newly inserted BizBook state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David i/Technology a/SWE Intern t/Backend e/david@example.com s/Saved d/2024-12-31 …​` to add a new internship application. The `add` command also calls `Model#commitBizBook()`, causing another modified BizBook state to be saved into the `bizBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitBizBook()`, so the BizBook state will not be saved into the `bizBookStateList`.

</div>

Step 4. The user now decides that adding the internship application was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoBizBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous BizBook state, and restores BizBook to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Logic.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Similarly, how an undo operation goes through the `Model` component is shown below:

![UndoSequenceDiagram](images/UndoSequenceDiagram-Model.png)

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest description book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify BizBook, such as `list`, will usually not call `Model#commitBizBook()`, `Model#undoBizBook()` or `Model#redoBizBook()`. Thus, the `bizBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitBizBook()`. Since the `currentStatePointer` is not pointing at the end of the `bizBookStateList`, all BizBook states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David i/Technology a/SWE Intern t/Backend e/david@example.com s/Saved d/2024-12-31 …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire BizBook state.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the internship application being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* University students actively applying for multiple internships
* Need to manage a significant number of internship applications simultaneously
* Prefer desktop apps over web-based or mobile applications
* Can type fast
* Prefer typing to mouse interactions
* Reasonably comfortable using CLI apps
* Tired of messy spreadsheets and scattered application tracking
* Want a centralized system to track application progress and deadlines

**Value proposition**:

BizBook helps students cut through the clutter of spreadsheets and web apps by providing a streamlined CLI-based internship tracker. Students can manage their entire application pipeline efficiently, staying organized and in control of their internship hunt, and accomplish application management tasks faster than with traditional GUI-driven tools.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                                                  | So that I can…​                                                                                  |
|----------|-------------------------------------------|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| `* * *`  | student                                   | add a new internship application with company name and job title                              | quickly start tracking a new opportunity                                                         |
| `* * *`  | student                                   | list all my tracked applications in a formatted table                                         | get a clear overview of my entire pipeline                                                       |
| `* * *`  | student                                   | view all details of a specific application using its ID                                       | instantly recall information like job description link or my notes                               |
| `* * *`  | student                                   | update the status of an application                                                           | keep my tracker current with my progress                                                         |
| `* * *`  | student                                   | delete an application from my active list                                                     | remove roles I'm no longer pursuing                                                              |
| `* * *`  | student                                   | see a unique ID for each application in the list view                                         | easily reference them in update, view and delete commands                                        |
| `* * *`  | new user                                  | see usage instructions and available commands                                                 | learn how to use BizBook effectively                                                             |
| `* * *`  | student                                   | find applications by company name                                                             | quickly locate specific companies without scrolling through the entire list                      |
| `* *`    | student                                   | add a deadline date when creating a new application                                           | don't miss the submission cutoff                                                                 |
| `* *`    | student                                   | add a URL to the original job posting                                                         | easily refer back to the full description and requirements                                       |
| `* *`    | student                                   | add multi-line notes to an application                                                        | record details like interviewer names, key discussion points, or follow-up actions               |
| `* *`    | student                                   | view all notes associated with a specific application                                         | quickly prepare for an interview                                                                 |
| `* *`    | student                                   | add a contact person's name and email for an application                                      | know who to address my follow-up emails to                                                       |
| `* *`    | student                                   | store the file path to the resume version I submitted                                         | remember which resume I used for each application                                                |
| `* *`    | student                                   | edit any field of an existing application                                                     | correct typos or update information like a deadline                                              |
| `* *`    | student                                   | filter my application list by status                                                          | focus only on the jobs I still need to apply for                                                 |
| `* *`    | student                                   | filter my application list by company                                                         | see all the roles I've applied to at a specific organization                                     |
| `* *`    | student                                   | sort my applications by deadline                                                              | prioritize my work on what's most urgent                                                         |
| `* *`    | student                                   | view a statistical summary of my applications                                                 | see my progress at a glance                                                                      |
| `* *`    | student                                   | see applications with deadlines in the next 7 days                                            | get an immediate view of my urgent tasks                                                         |
| `* *`    | student                                   | search for companies using keywords in their description                                      | discover companies that align with my interests and studies                                      |
| `* *`    | student                                   | filter search results by industry category                                                    | focus my search on sectors I want to work in                                                     |
| `* *`    | student                                   | filter opportunities by job type                                                              | find roles that match my skills                                                                  |
| `* *`    | student                                   | combine multiple filters                                                                      | find highly specific and relevant opportunities                                                  |
| `* *`    | student                                   | sort search results by company name or application deadline                                   | organize opportunities and prioritize my applications                                            |
| `* *`    | student                                   | clear all active filters with a single command                                                | easily start a new search                                                                        |
| `*`      | student                                   | export my application data to a CSV file                                                      | create a backup or analyze it further in a spreadsheet                                           |
| `*`      | student                                   | import applications from a CSV file                                                           | easily migrate my existing data from a spreadsheet into BizBook                                  |
| `*`      | student                                   | archive a completed application                                                               | hide it from my active view while still keeping it for my records                                |
| `*`      | student                                   | view my archived applications                                                                 | look back at my complete application history for a given season                                  |
| `*`      | student                                   | search through all my notes for a specific keyword                                            | find which interview I discussed a particular topic in                                           |
| `*`      | student                                   | clear all application data after a confirmation prompt                                        | easily start fresh for a new internship hunt season                                              |
| `*`      | user                                      | receive clear confirmation messages after successfully adding or updating an application      | know my command worked                                                                           |
| `*`      | user                                      | see helpful error messages if I use a command incorrectly                                     | learn how to use the tool properly                                                               |
| `*`      | user                                      | see application statuses color-coded in the list view                                         | visually distinguish between different stages at a glance                                        |
| `*`      | user                                      | set a default sort order in a configuration file                                              | don't have to type the sort flag every time I list my applications                               |
| `*`      | user                                      | cancel a multi-step command midway through                                                    | easily back out if I make a mistake                                                              |
| `*`      | user                                      | use CLI autocomplete for company names I've already entered                                   | save time and avoid typos                                                                        |

### Use cases

(For all use cases below, the **System** is `BizBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use case: UC01 - Add an internship application

**MSS**

1. User requests to add a new internship application with all required fields (company name, industry, job type, description, email, status, and deadline).
2. BizBook validates the input.
3. BizBook adds the application to the list.
4. BizBook shows a success message with the application details.

   Use case ends.

**Extensions**

* 1a. Required fields are missing.

  * 1a1. BizBook shows an error message indicating which fields are missing.

    Use case ends.

* 2a. Company name format is invalid.

  * 2a1. BizBook shows an error message about invalid format.

    Use case ends.

* 2b. Industry is not from the predefined list.

  * 2b1. BizBook shows an error message with valid industry options.

    Use case ends.

* 2c. Status is not from the predefined list.

  * 2c1. BizBook shows an error message with valid status options.

    Use case ends.

* 2d. Deadline format is invalid.

  * 2d1. BizBook shows an error message about the required YYYY-MM-DD format.

    Use case ends.

* 2e. An application for this company already exists.

  * 2e1. BizBook shows a duplicate company error message.

    Use case ends.

#### Use case: UC02 - Update application status

**MSS**

1. User requests to list all applications.
2. BizBook shows a numbered list of applications.
3. User requests to update the status of a specific application by index.
4. BizBook validates the index and new status.
5. BizBook updates the application status.
6. BizBook shows a success message with updated details.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  * 2a1. BizBook shows a message indicating no applications exist.

    Use case ends.

* 4a. The given index is invalid.

  * 4a1. BizBook shows an error message.

    Use case resumes at step 3.

* 4b. The given status is not valid.

  * 4b1. BizBook shows an error message with valid status options.

    Use case resumes at step 3.

#### Use case: UC03 - Filter applications by status

**MSS**

1. User requests to filter applications by a specific status.
2. BizBook validates the status.
3. BizBook displays all applications matching the status.
4. BizBook shows the count of filtered applications.

   Use case ends.

**Extensions**

* 2a. The given status is invalid.

  * 2a1. BizBook shows an error message with valid status options.

    Use case ends.

* 3a. No applications match the given status.

  * 3a1. BizBook shows a message indicating no matches found.

    Use case ends.

#### Use case: UC04 - Add notes to an application

**MSS**

1. User requests to list all applications.
2. BizBook shows a numbered list of applications.
3. User requests to view details of a specific application by index.
4. BizBook shows full details of the application.
5. User requests to add notes to this application.
6. BizBook validates the index and note content.
7. BizBook adds the note to the application.
8. BizBook shows a success message.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  * 2a1. BizBook shows a message indicating no applications exist.

    Use case ends.

* 4a. The given index is invalid.

  * 4a1. BizBook shows an error message.

    Use case resumes at step 3.

* 6a. The note exceeds maximum length.

  * 6a1. BizBook shows an error message about note length limit.

    Use case resumes at step 5.

#### Use case: UC05 - Delete an application

**MSS**

1. User requests to list all applications.
2. BizBook shows a numbered list of applications.
3. User requests to delete a specific application by index.
4. BizBook validates the index.
5. BizBook deletes the application.
6. BizBook shows a success message with deleted application details.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  * 2a1. BizBook shows a message indicating no applications exist.

    Use case ends.

* 4a. The given index is invalid.

  * 4a1. BizBook shows an error message.

    Use case resumes at step 3.

### Non-Functional Requirements

1. **Platform Compatibility**: Should work on any mainstream OS (Windows, Linux, macOS) as long as it has Java 17 or above installed.

2. **Performance**: Should be able to hold up to 1000 internship applications without noticeable sluggishness in performance for typical usage.

3. **Usability - Typing Efficiency**: A user with above-average typing speed for regular English text should be able to accomplish most tasks faster using commands than using the mouse.

4. **Usability - Learning Curve**: A new user should be able to use all basic features (add, list, update, delete) after reading the User Guide for 15 minutes.

5. **Usability - Error Messages**: Error messages should be clear and actionable, guiding users on how to correct their input.

6. **Data Persistence**: Application data should be stored locally in a human-editable text file format (JSON).

7. **Portability**: Should work without requiring an installer, allowing students to run it from a USB drive or cloud storage folder.

8. **Reliability**: Should not lose data during normal operation. All data changes should be saved immediately.

9. **Response Time**: All commands should execute and display results within 2 seconds under normal load.

10. **Display Compatibility**: The GUI should be usable for standard screen resolutions of 1920x1080 and higher at 100% and 125% screen scales.

11. **Display Compatibility - Minimum**: The GUI should remain functional (though not optimal) for resolutions of 1280x720 and higher at 150% scale.

12. **Extensibility**: The command structure should be designed to easily accommodate new fields and commands in future versions.

13. **Data Privacy**: Application data should remain local and private to the user, with no external data transmission.

14. **Command Flexibility**: Commands should be case-insensitive and tolerant of minor spacing variations to improve user experience.

15. **Scalability**: Should handle increasing numbers of applications efficiently as students apply to more positions throughout the internship season.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, macOS

* **Application**: A record of an internship opportunity that a student is tracking in BizBook, including company name, industry, job role, description, email, status, and deadline

* **Status**: The current stage of an internship application in the pipeline (e.g., Saved, Applied, Interviewing, Offer, Rejected)

* **Index**: A positive integer representing the position of an application in the displayed list, used for referencing specific applications in commands

* **Industry Category**: A predefined classification of companies by their primary business sector (e.g., Tech, Finance, Consulting, Healthcare)

* **Job Type**: The specific internship role title or position (e.g., "Software Engineer Intern", "Data Analyst Intern")

* **Pipeline**: The complete collection of internship applications a student is tracking in BizBook

* **CLI**: Command Line Interface - a text-based interface for interacting with the application

* **Filter**: A command operation that displays only applications matching specific criteria

* **Archive**: A feature to hide completed applications from the active view while preserving them for historical reference

* **Deadline**: The submission cutoff date for an internship application

* **Contact Person**: The recruiter or HR representative associated with a specific internship application

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a internshipApplication

1. Deleting a internshipApplication while all internshipApplications are being shown

   1. Prerequisites: List all internshipApplications using the `list` command. Multiple internshipApplications in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No internshipApplication is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
