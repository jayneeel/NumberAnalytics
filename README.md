# NumberAnalytics
The Number Analytics App is an Android application that allows users to enter and analyze lists of comma-separated numbers. The app provides a user-friendly interface with three input text boxes and a "Calculate" button.
Features

Input Validation: The app validates the user input to ensure that only numbers and commas are entered in the text boxes. Empty fields are not allowed.
Intersection Calculation: Upon clicking the "Calculate" button, the app calculates the intersection of the numbers present in all three text boxes and displays the result.
Union Calculation: The app also calculates the union of all the numbers from the three text boxes and displays the result.
Maximum Number Identification: The app identifies and displays the highest number among all the numbers entered in the three text boxes.

## Implementation
The app has two branches:

**master**: In this branch, the app utilizes built-in Kotlin functions to perform the calculations and validations.<br><br>
**jayneel**: This branch implements the same functionality without using any built-in functions. Instead, it uses a HashMap data structure to store and analyze the numbers.

Both branches achieve the same goal but with different implementation approaches, allowing you to explore and understand the code from different perspectives.

## App Screenshots
<table>
  <tr>
    <td>
      <img src="https://github.com/jayneeel/NumberAnalytics/blob/master/app/src/main/res/drawable/ui.jpg" height="600" width="250" />
    </td>
    <td>
     <img src="https://github.com/jayneeel/NumberAnalytics/blob/master/app/src/main/res/drawable/validation.jpg" height="600" width="250"/> 
    </td>
    <td>
      <img src="https://github.com/jayneeel/NumberAnalytics/blob/master/app/src/main/res/drawable/final_output.jpg" height="600" width="250"/>
    </td>
  </tr>
</table>
