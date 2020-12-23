import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Keshab Gautam and Tej Patel
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        tokens.dequeue(); //is INSTRUCTION
        String toBeIdentifier = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(toBeIdentifier),
                "Expected identifier, but found: " + toBeIdentifier);
        Reporter.assertElseFatalError(
                !toBeIdentifier.equals("move")
                        && !toBeIdentifier.equals("infect")
                        && !toBeIdentifier.equals("turnleft")
                        && !toBeIdentifier.equals("turnright")
                        && !toBeIdentifier.equals("skip"),
                "Instruction " + toBeIdentifier
                        + " should not be a name of primitive instruction.");
        Reporter.assertElseFatalError(tokens.front().equals("IS"),
                "Expected IS, but found: " + tokens.front());
        tokens.dequeue(); //is IS
        body.parseBlock(tokens);
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "Expected END");
        String name = tokens.dequeue();
        Reporter.assertElseFatalError(name.equals(toBeIdentifier),
                "Identifier at the beginning " + toBeIdentifier
                        + " should be same as identifier at the end " + name);

        return name;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        Reporter.assertElseFatalError(tokens.dequeue().equals("PROGRAM"),
                "Violation of: PROGRAM is a prefix of tokens");
        String toBeIdentifier = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(toBeIdentifier),
                "Expected identifier, but found: " + toBeIdentifier);
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "Invalid token IS");
        Map<String, Statement> conTxt = this.newContext();
        while (tokens.front().equals("INSTRUCTION")) {
            Statement bdy = this.newBody();
            String instr = parseInstruction(tokens, bdy);
            Reporter.assertElseFatalError(!Tokenizer.isKeyword(instr),
                    "Instruction " + instr + "can't be keyword");
            Reporter.assertElseFatalError(!conTxt.hasKey(instr),
                    "Instruction " + instr + "already used");
            conTxt.add(instr, bdy);
        }
        Reporter.assertElseFatalError(tokens.dequeue().equals("BEGIN"),
                "Invalid token BEGIN");
        Statement body = this.newBody();
        body.parseBlock(tokens);
        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "Expected END");
        String name = tokens.dequeue();
        Reporter.assertElseFatalError(name.equals(toBeIdentifier),
                "Identifier at the beginning " + toBeIdentifier
                        + " should be same as identifier at the end " + name);
        String endInput = tokens.dequeue();
        Reporter.assertElseFatalError(endInput.equals(Tokenizer.END_OF_INPUT),
                "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens");
        this.setName(toBeIdentifier);
        this.swapContext(conTxt);
        this.swapBody(body);

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
