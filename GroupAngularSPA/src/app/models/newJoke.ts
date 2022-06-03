
export class newJoke{
    private answer: string;
    private question: string;

    constructor(theQuestion:string,theAnswer:string){
        this.answer = theAnswer;
        this.question=theQuestion;
    }

    public get _answer(){
        return this.answer;
    }

    public get _question(){
        return this.question;
    }

    public set _answer(theAnswer: string){
        this.answer = theAnswer;
    }

    public set _question(theQuestion: string){
        this.question = theQuestion;
    }
}