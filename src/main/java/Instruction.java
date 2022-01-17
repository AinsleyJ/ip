import java.util.ArrayList;

/**
 * A task represents an instruction inputted to Duke by a user.
 * At this stage, a task has minimally a name (description).
 */
abstract class Instruction {

    private String description;
    private static final String TERMINATE_INSTRUCTION = "bye";

    protected static String getTerminateInstruction() {
        return TERMINATE_INSTRUCTION;
    }

    /**
     * Factory method. Create an instance of subclass of instructions according to the string inputted, and returns it.
     * The instruction must have at least one word.
     *
     * @param instruction The line of command.
     * @return A corresponding instance of instruction.
     */
    protected static Instruction of(String instruction) throws IllegalArgumentException {

        // Extract the words in the instruction. The first word should determine the type of instruction to be returned.
        String[] words = instruction.split(" ", 2);

        if (words.length == 0) {
            throw new IllegalArgumentException("Oops, I don't know what empty instruction means.");
        }

        String type = words[0];

        switch (type) {
            case "list":
                return new ListTasks();
            case Instruction.TERMINATE_INSTRUCTION:
                return new Quit(Instruction.TERMINATE_INSTRUCTION);
            case "mark":
                // Mark the task as done. If the second parameter is not an integer, or if the task does not exit, throw
                // an exception. (To be implemented later)
                return new MarkAsDone(instruction);
            case "unmark":
                // Mark the task as not done. If the second parameter is not an integer, or if the task does not exit,
                // throw an exception. (To be implemented later)
                return new UnmarkAsDone(instruction);
            case "todo":
            case "event":
            case "deadline":
                // These three cases are used to add tasks of different types.
                return new Add(Task.of(instruction));
            default:
                throw new IllegalArgumentException("Oops, I'm not sure what you mean.");
        }
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the description of the instruction.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Performs the associated action of the task. By default, there is no action associated to a task.
     *
     * @return The message once the instruction is executed.
     */
    protected abstract String act();
}
