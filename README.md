1. Write a Java program to solve the following problem:
   You are tasked with creating a utility function for a text-processing application. The
   function must generate all possible anagrams from a given group of distinct letters. For
   example, the input {a, b, c} should return the output: abc, acb, bac, bca, cab, cba.
   Additional Requirements:
   1. The program should accept any group of distinct letters as input and produce the
      correct result.
   2. Optimize for readability and clarity.
   3. Include basic validation (e.g., ensure the input is non-empty and contains only
      letters).
   4. Write unit tests to validate your function with at least three different test cases,
      including edge cases (e.g., input with a single letter or empty input).
   5. Document your code clearly, explaining the logic for generating anagrams.
   
   > Example class: [AnagramHelper](src/main/java/org/example/AnagramHelper.java)

2. Provide an example scenario where overriding the equals() method is necessary in Java.
Explain the key considerations when implementing this method, such as ensuring it
aligns with the hashCode() method. Include code examples if possible.

   > Example class: [User](src/main/java/org/example/User.java)

3. Explain how you would use a design pattern to decouple your code from a third-party
   library that might be replaced in the future. Describe the advantages and limitations of
   your chosen approach, and provide a small code snippet illustrating its application.
   
   > The design patter that makes the most sense would be the Adapter. it's basically
   > a wrapper around the actual implementation of a third-party library.
   > 
   > Example class: [ThirdPartyBankAdapter](src/main/java/org/example/ThirdPartyBankAdapter.java)
   > 
   > Main advantage is that if we switch to another 'bank', we only need to create a new adapter
   > that connects to the new bank. 
   > One disadvantage is the extra boilerplate needed.

4. Describe your experience with Angular, including its core features and use cases. Provide
   an example of a practical application where you used Angular and include a code snippet
   demonstrating a key feature, such as component communication, data binding, or
   service integration.

   <dl>
      <dt>2017-2020</dt>
      <dd>I worked with AngularJS and Angular, with my main task being the conversion of interactive courses from Adobe Flash to HTML5 using Angular.</dd>
   
      <dt>2020</dt>
      <dd>I developed web applications for the National Council of Justice using Angular for the front-end and Spring Boot for the back-end.</dd>
   
      <dt>2021 - Present</dt>
      <dd>I have been maintaining and developing new features for Portal 4 at Lojas Renner, a SPA application used by store employees for customer service (sales, exchanges, scanning, etc.).</dd>
   </dl>
   
   ```typescript
   import { Component, Input } from '@angular/core';

   @Component({
     selector: 'app-child',
     template: `<p>Message from Parent: {{ message }}</p>`,
     styleUrls: ['./child.component.css']
   })
   export class ChildComponent {
     @Input() message: string = ''; // property received from parent component
   }

   import { Component } from '@angular/core';

   @Component({
     selector: 'app-parent',
     template: `<app-child [message]="parentMessage"></app-child>`,
     styleUrls: ['./parent.component.css']
   })
   export class ParentComponent {
     parentMessage: string = 'Hello from Parent!';
   }
   ```
5. Discuss the techniques you use to prevent SQL injection attacks in web applications.
   Provide examples of code showing secure implementations, such as using parameterized
   queries or ORMs. Mention any additional measures you take to secure the database
   layer.

   > In the front-end, I like to make use of input sanitization, to clear any strange character that the used typed in the form out of the string.
   > In the back-end, when using Spring Data, the parameters passed to query via Repository methods are natively sanitized by Spring itself.

6. Describe the steps you would take to diagnose and improve the performance of a batch
   process that interacts with a database and an FTP server. Explain how you would identify
   bottlenecks, optimize database queries, improve logic execution, and enhance file
   transfer efficiency. Provide examples of tools or techniques you would use during the
   analysis.
   
   > I would take the following steps
   1. Identify bottlenecks via logging and profiling with tools like JProfiler or APM. 
   2. Optimize queries with indexes, batching, pagination and use `EXPLAIN ANALYZE`. 
   3. Improve execution with multi-threading, streaming, and caching. 
   4. Enhance FTP with compression, parallel uploads. 
   5. Use automated monitoring tools like with Prometheus, Grafana, and Splunk.

7. Given the tables above, write the SQL query that:
   1. Returns the names of all Salesperson that don’t have any order with Samsonic.
   
      ```sql
      SELECT s.name
        FROM Salesperson s
        WHERE NOT EXISTS (
          SELECT 1
          FROM Orders o
          JOIN Customer c ON o.customer_id = c.id
          WHERE c.name = 'Samsonic' AND o.salesperson_id = s.id
       );
      ```
   2. Updates the names of Salesperson that have 2 or more orders. It’s necessary to add an
       ‘*’ in the end of the name.
      ```sql 
       UPDATE Salesperson s SET name = name || '*' WHERE (SELECT COUNT(*) FROM Orders o where o.salesperson_id = s.id) >= 2; 
      ```
   3. Deletes all Ssalesperson that placed orders to the city of Jackson.
      ```sql
      DELETE CASCADE FROM Salesperson s 
      where EXISTS(
        SELECT 1
        FROM  Orders o
        INNER JOIN Customer c on o.customer_id = c.id AND c.city = 'Jackson'
        WHERE s.id = o.salesperson_id
      );
      ```
   4. The total sales amount for each Salesperson. If the salesperson hasn’t sold anything,
      show zero.
      ```sql
      SELECT s.name, COALESCE(SUM(o.amount), 0) as Total
      FROM Salesperson s
      LEFT JOIN Orders o ON o.salesperson_id = s.id
      GROUP BY s.id, s.name, o.salesperson_id;
      ```
8. Use Case: Plant Management System - Phase 1

## User Story
As an admin user of the XYZ system, I want to manage plant information efficiently, ensuring data integrity and controlled access to plant-related operations.

## Requirements

### Plant Data Entry and Management
- System shall allow creation of new plant entries
- Authorized users can create, update, and search plant records
- Only admin users can delete plant records

### Plant Attributes
#### Plant Code
- Must be numeric
- Mandatory field
- Unique across the system
- Cannot be duplicated

#### Plant Description
- Alphanumeric
- Optional
- Maximum length: 10 characters

## Business Rules and Assumptions
1. User Roles:
   - Admin users have full access to plant management functions
   - Non-admin users can view and update, but cannot delete plant records

2. Data Integrity:
   - System must prevent duplicate plant codes
   - Validation occurs at the point of data entry
   - Existing plant codes cannot be reused

3. Security Considerations:
   - Role-based access control for plant management functions
   - Logging of all plant-related operations (create, update, delete)
   - Authentication required for all plant management actions

## Validation Mechanisms
1. Plant Code Validation:
   - Must be numeric only
   - Cannot be null
   - Must be unique
   - Validation occurs before database insertion

2. Description Validation:
   - Optional field
   - Maximum 10 characters
   - Allows alphanumeric characters

## Test Scenarios and Edge Cases

### Positive Test Cases
1. Create a valid plant with all required information
   - Input: Code = 12345, Description = "Main Plant"
   - Expected: Successfully create plant record

2. Update existing plant description
   - Input: Existing plant code, new description
   - Expected: Description updated successfully

3. Search for existing plant
   - Input: Valid plant code or partial description
   - Expected: Retrieve matching plant records

### Negative/Edge Test Cases
1. Attempt to create plant with duplicate code
   - Input: Plant code already in system
   - Expected: Validation error, prevent duplicate entry

2. Create plant with invalid code
   - Input: Non-numeric code (e.g., "ABC")
   - Expected: Validation error

3. Create plant description exceeding 10 characters
   - Input: Description longer than 10 characters
   - Expected: Truncation or validation error

4. Non-admin user attempting to delete plant
   - Input: Delete request from non-admin user
   - Expected: Access denied, security error logged

# Last question

> Example class: [UserRegistrationTest](src/test/java/org/example/UserRegistrationTest.java)

1. Testing Types:
   1. Unit Tests: Validate individual component behaviors
   2. Integration Tests: Check component interactions
   3. End-to-End Tests: Simulate complete user workflows

2. Edge Cases:
   1. Focused on input validation
   2. Security considerations
   3. Boundary condition handling

3. Example Test Cases:
   1. Demonstrated unique email constraint
   2. Showed admin and non-admin deletion scenarios
   3. Used JUnit with Mockito for implementation

4. The test cases cover critical scenarios like:
   1. Preventing duplicate email registrations
   2. Enforcing admin-only user deletion
   3. Handling various input validations