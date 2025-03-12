package hw9;

import java.util.Stack;

/* Alphabet is ASCII characters 48-57, 65-90, and 97-122 for now 
 * TODO: Add escape functionality to handle special chars in regex
 * TODO: Add more operators (currently only |, ., *)
 * TODO: Implement infix -> postfix conversion
 * TODO: Implement NFA to DFA 
 */

public class Validate {

    private String regex;
    private String validate;
    private NFA automata;
    private long startBuild;
    private long finishBuild;
    private long startValidate;
    private long finishValidate;

    public Validate() {

    }

    public Validate(String regex, String validate) {
        this.regex = regex;
        this.validate = validate;
        startBuild = System.currentTimeMillis();
        build();
        finishBuild = System.currentTimeMillis();
    }

    public void setRegex(String regex) {
        this.regex = regex;
        build();
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    private void build() {
        Stack<NFA> stack = new Stack<>();
        for (char c : regex.toCharArray()) {
            switch (c) {
                case '.':
                    assert stack.size() >= 2 : "Stack too short to .";
                    NFA a = stack.pop();
                    NFA b = stack.pop();
                    a.concat(b);
                    stack.push(a);
                    break;
                case '|':
                    assert stack.size() >= 2 : "Stack too short to |";
                    a = stack.pop();
                    b = stack.pop();
                    a.alternation(b);
                    stack.push(a);
                    break;
                case '*':
                    assert stack.size() >= 1 : "Stack too short to *";
                    a = stack.pop();
                    a.kleeneStar();
                    stack.push(a);
                    break;
                case '?':
                    assert stack.size() >= 1 : "Stack too short to ?";
                    a = stack.pop();
                    a.optional();
                    stack.push(a);
                    break;
                default:
                    State start = new State();
                    State end = new State();
                    start.addNextState(c, end);
                    a = new NFA(start, end);
                    stack.push(a);
                    break;
            }
        }

        if (stack.size() > 1) {
            throw new Error("Stack bigger than one, regex missing operator");
        }

        automata = stack.pop();
    }

    public boolean matches() {
        startValidate = System.currentTimeMillis();
        boolean ret = this.automata.matches(validate);
        finishValidate = System.currentTimeMillis();
        return ret;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorect usage of Validate");
            System.out.println("Correct usage is: Validate {postfix regex} {string to validate}");
            return;
        }

        Validate val = new Validate(args[0], args[1]);
        System.out.println(val.matches());
    }
}
