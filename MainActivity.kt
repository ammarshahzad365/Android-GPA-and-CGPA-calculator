package com.example.gpaandcgpacalculator

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var grades = arrayOf<String>("A","B+","B","C+","C","D+","D","F")
    var creditHoursList = arrayOf<Int>(0,1,2,3,4,5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        displayGradesList()
        displayCRsList()

        val calcButton: Button = findViewById(R.id.calcButton)
        calcButton.setOnClickListener{
            val gpaResult = findViewById<TextView>(R.id.gpaResult)
            var prevCGPA = findViewById<EditText>(R.id.prevCGPA).text.toString()
            var prevCRs = findViewById<EditText>(R.id.prevTotalCRs).text.toString()
            val gpa:Double = calcGPA()
            val currentCRs:Int = calcTotalCRs()

            if(prevCGPA.trim().length == 0 || prevCRs.trim().length==0){
                prevCGPA = "0"
                prevCRs = "0"
            }

            val prevCgpa = prevCGPA.toDouble()
            val prevCrs = prevCRs.toInt()

            if (prevCgpa>4 || prevCgpa<0){ gpaResult.text = "Enter a value for previous CGPA between 0 and 4" }
            else if(prevCrs==0){gpaResult.text = gpa.toString()}
            else{
                val cgpa = ((prevCgpa*prevCrs)+(gpa*currentCRs))/(currentCRs+prevCrs) //calc total cgpa
                gpaResult.text = ((cgpa*100).roundToInt().toDouble()/100).toString() //round cgpa to 2 dp
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun gradeToGPA(grade: String, creditHours: Int): Double {
        var gradePoints = 0.0
        if (grade == "A") {
            gradePoints = 4.0
        }
        if (grade == "B+") {
            gradePoints = 3.5
        }
        if (grade == "B") {
            gradePoints = 3.0
        }
        if (grade == "C+") {
            gradePoints = 2.5
        }
        if (grade == "C") {
            gradePoints = 2.0
        }
        if (grade == "D+") {
            gradePoints = 1.5
        }
        if (grade == "D") {
            gradePoints = 1.0
        }
        if (grade == "F") {
            gradePoints = 0.0
        }
        return gradePoints * creditHours
    }

    private fun displayGradesList(){
        val gradeSpin1:Spinner = findViewById<Spinner>(R.id.gradeSpinner1)
        val gradeSpin2:Spinner = findViewById<Spinner>(R.id.gradeSpinner2)
        val gradeSpin3:Spinner = findViewById<Spinner>(R.id.gradeSpinner3)
        val gradeSpin4:Spinner = findViewById<Spinner>(R.id.gradeSpinner4)
        val gradeSpin5:Spinner = findViewById<Spinner>(R.id.gradeSpinner5)
        val gradeSpin6:Spinner = findViewById<Spinner>(R.id.gradeSpinner6)
        val gradeSpin7:Spinner = findViewById<Spinner>(R.id.gradeSpinner7)
        val gradeSpin8:Spinner = findViewById<Spinner>(R.id.gradeSpinner8)
        val gradeSpin9:Spinner = findViewById<Spinner>(R.id.gradeSpinner9)

        val spins: List<Spinner> = listOf(gradeSpin1, gradeSpin2, gradeSpin3, gradeSpin4,gradeSpin5,
            gradeSpin6,gradeSpin7,gradeSpin8,gradeSpin9)
        for(spin in spins){ spin.onItemSelectedListener = this }

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(this,android.R.layout.simple_spinner_item,grades)

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        for(spin in spins){spin.adapter = ad}
    }

    private fun displayCRsList(){
        val crSpin1:Spinner = findViewById<Spinner>(R.id.crSpinner1)
        val crSpin2:Spinner = findViewById<Spinner>(R.id.crSpinner2)
        val crSpin3:Spinner = findViewById<Spinner>(R.id.crSpinner3)
        val crSpin4:Spinner = findViewById<Spinner>(R.id.crSpinner4)
        val crSpin5:Spinner = findViewById<Spinner>(R.id.crSpinner5)
        val crSpin6:Spinner = findViewById<Spinner>(R.id.crSpinner6)
        val crSpin7:Spinner = findViewById<Spinner>(R.id.crSpinner7)
        val crSpin8:Spinner = findViewById<Spinner>(R.id.crSpinner8)
        val crSpin9:Spinner = findViewById<Spinner>(R.id.crSpinner9)

        val spins: List<Spinner> = listOf(crSpin1, crSpin2, crSpin3, crSpin4, crSpin5,
            crSpin6,crSpin7,crSpin8,crSpin9)
        for(spin in spins){ spin.onItemSelectedListener = this }

        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(this,android.R.layout.simple_spinner_item,creditHoursList)

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        for(spin in spins){spin.adapter = ad}
    }

    private fun calcGPA(): Double {
        val gradeSpin1: Spinner = findViewById<Spinner>(R.id.gradeSpinner1)
        val gradeSpin2: Spinner = findViewById<Spinner>(R.id.gradeSpinner2)
        val gradeSpin3: Spinner = findViewById<Spinner>(R.id.gradeSpinner3)
        val gradeSpin4: Spinner = findViewById<Spinner>(R.id.gradeSpinner4)
        val gradeSpin5: Spinner = findViewById<Spinner>(R.id.gradeSpinner5)
        val gradeSpin6: Spinner = findViewById<Spinner>(R.id.gradeSpinner6)
        val gradeSpin7: Spinner = findViewById<Spinner>(R.id.gradeSpinner7)
        val gradeSpin8: Spinner = findViewById<Spinner>(R.id.gradeSpinner8)
        val gradeSpin9: Spinner = findViewById<Spinner>(R.id.gradeSpinner9)

        val crSpin1 = findViewById<Spinner>(R.id.crSpinner1).selectedItem as Int
        val crSpin2 = findViewById<Spinner>(R.id.crSpinner2).selectedItem as Int
        val crSpin3 = findViewById<Spinner>(R.id.crSpinner3).selectedItem as Int
        val crSpin4 = findViewById<Spinner>(R.id.crSpinner4).selectedItem as Int
        val crSpin5 = findViewById<Spinner>(R.id.crSpinner5).selectedItem as Int
        val crSpin6 = findViewById<Spinner>(R.id.crSpinner6).selectedItem as Int
        val crSpin7 = findViewById<Spinner>(R.id.crSpinner7).selectedItem as Int
        val crSpin8 = findViewById<Spinner>(R.id.crSpinner8).selectedItem as Int
        val crSpin9 = findViewById<Spinner>(R.id.crSpinner9).selectedItem as Int

        val grade1 = gradeToGPA(gradeSpin1.selectedItem.toString(), crSpin1)
        val grade2 = gradeToGPA(gradeSpin2.selectedItem.toString(), crSpin2)
        val grade3 = gradeToGPA(gradeSpin3.selectedItem.toString(), crSpin3)
        val grade4 = gradeToGPA(gradeSpin4.selectedItem.toString(), crSpin4)
        val grade5 = gradeToGPA(gradeSpin5.selectedItem.toString(), crSpin5)
        val grade6 = gradeToGPA(gradeSpin6.selectedItem.toString(), crSpin6)
        val grade7 = gradeToGPA(gradeSpin7.selectedItem.toString(), crSpin7)
        val grade8 = gradeToGPA(gradeSpin8.selectedItem.toString(), crSpin8)
        val grade9 = gradeToGPA(gradeSpin9.selectedItem.toString(), crSpin9)

        val totalScore: Double =
            grade1 + grade2 + grade3 + grade4 + grade5 + grade6 + grade7 + grade8 + grade9
        val totalCRs = calcTotalCRs()
        if (totalCRs == 0) {
            return 0.0
        }

        return ((totalScore / totalCRs) * 100).roundToInt().toDouble() / 100
    }

    private fun calcTotalCRs(): Int {
        val crSpin1 = findViewById<Spinner>(R.id.crSpinner1).selectedItem as Int
        val crSpin2 = findViewById<Spinner>(R.id.crSpinner2).selectedItem as Int
        val crSpin3 = findViewById<Spinner>(R.id.crSpinner3).selectedItem as Int
        val crSpin4 = findViewById<Spinner>(R.id.crSpinner4).selectedItem as Int
        val crSpin5 = findViewById<Spinner>(R.id.crSpinner5).selectedItem as Int
        val crSpin6 = findViewById<Spinner>(R.id.crSpinner6).selectedItem as Int
        val crSpin7 = findViewById<Spinner>(R.id.crSpinner7).selectedItem as Int
        val crSpin8 = findViewById<Spinner>(R.id.crSpinner8).selectedItem as Int
        val crSpin9 = findViewById<Spinner>(R.id.crSpinner9).selectedItem as Int

        return crSpin1 + crSpin2 + crSpin3 + crSpin4 + crSpin5 + crSpin6 + crSpin7 + crSpin8 + crSpin9
    }



}

