package com.example.myquizapp

object Constants {
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String ="total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions():ArrayList<Questions>{
        val questionList = ArrayList<Questions>()

       // 1
        val ques1 = Questions(
            1,"Can you identify the cricketer picture? He has the most Test wickets.",
            R.drawable.image1question,"Rangana Herath","Anil Kumble","Muttiah Muralitharan",
            "Ajantha Mendis",3

        )
        questionList.add(ques1)

        // 2
        val ques2 = Questions(
            2,"Can you identify the cricketer picture? He's an England legend.",
            R.drawable.image2question,"Stuart Broad","Jimmy Anderson","Joe Root",
            "Ben Stokes",2

        )
        questionList.add(ques2)

        // 3
        val ques3 = Questions(
            3,"Can you identify the cricketer picture? An iconic image from a World Cup.",
            R.drawable.image3question,"AB de Villiers","Faf du Plessis","Shaun Pollock",
            "Dale Steyn",4

        )
        questionList.add(ques3)

        // 4
        val ques4 = Questions(
            4,"Can you identify the cricketer picture? Who is hugging Sachin Tendulkar?.",
            R.drawable.image4question,"Yuvraj Singh","Virender Sehwag","Gautam Gambhir",
            "Rahul Dravid",1

        )
        questionList.add(ques4)

        // 5
        val ques5 = Questions(
            5,"Can you identify the cricketer picture?.",
            R.drawable.image5question,"Andrew Flintoff","Joe Root","Stuart Board",
            "Kevin Pietersen",4

        )
        questionList.add(ques5)

        // 6
        val ques6 = Questions(
            6,"Can you identify the cricketer picture?.The biggest name in the game, at the moment.",
            R.drawable.image6question,"MS Dhoni","Virat Kohli","Rohit Sharma",
            "Ishan Kishan",2

        )
        questionList.add(ques6)

        // 7
        val ques7 = Questions(
            7,"Can you identify the cricketer picture?.He's scored the highest individual Test score.",
            R.drawable.image7question,"Kapil Dev","Chris Gayle","Vivian Richads",
            "Brian Lara",4

        )
        questionList.add(ques7)

        // 8
        val ques8 = Questions(
            8,"Can you identify the cricketer picture?.He was an active cricketer till last year.",
            R.drawable.image8question,"Marcus Trescothick","Ian Bell","Alastair Cook",
            "Shane Warne",1

        )
        questionList.add(ques8)

        // 9
        val ques9 = Questions(
            9,"Can you identify the cricketer picture?.The Sultan of Swing.",
            R.drawable.image9questions,"Mohammad Amir","Wasim Akram","Imran Khan",
            "Waqar Younis",2

        )
        questionList.add(ques9)


        // 10
        val ques10 = Questions(
            10,"Can you identify the cricketer picture?.The Greatest Right-arm leg spin bowler of all times.",
            R.drawable.image10question,"Kuldeep Yadav","Rashid Khan","Anil Kumble",
            "Shane Warne",4

        )
        questionList.add(ques10)

        return questionList
    }

}