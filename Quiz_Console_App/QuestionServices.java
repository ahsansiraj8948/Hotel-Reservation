package Quiz_Console_App;
import java.util.Scanner;
public class QuestionServices {

    Question[] question =new Question[4];//to store question in array
    String[] option=new String[4];
    public QuestionServices(){
        question[0]=new Question(1,"In which year Java invented","1997","1996","1990","1950","1997");
        question[1]=new Question(2,"In which year Python invented","1997","1996","1990","1950","1997");
        question[2]=new Question(3,"In which year C invented","1997","1996","1990","1950","1997");
        question[3]=new Question(4,"In which year HTML invented","1997","1996","1990","1950","1997");
    }

    public void play(){
        int i=1;
        for(Question q:question)
        {
            System.out.println("question number"+q.getId());
            System.out.println(q.getQuestion());
            System.out.println(q.getOp1());
            System.out.println(q.getOp2());
            System.out.println(q.getOp3());
            System.out.println(q.getOp4());
            Scanner sc=new Scanner(System.in);
            option[i]=sc.nextLine();
            i++;
        }

        for(String s:option)
        {
            System.out.println(s);
        }
    }
    public void calculate() {
        int score = 0;
        for (int i = 0; i < question.length; i++) {
            Question que = question[i];
            String answer = que.getAnswers();
            String userAnswer = option[i];
            if (answer.equals(userAnswer)) {
                score++;
            }
        }
        System.out.println("Your score is : " + score);
    }


    public void print() {
        System.out.println("commited");
    }
}
