package de.frauas.java.projectWS1920.Resources;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class Resource
{
    private static final String filepath = "src\\de\\frauas\\java\\projectWS1920\\resources\\";

    private static final String logoPicture =
        "          _..._\n" +
        "        .'     '.\n" +
        "       ; __   __ ;\n" +
        "       |/  \\ /  \\|\n" +
        "     |\\| -- ' -- |/|\n" +
        "     |(| \\o| |o/ |)|\n" +
        "     _\\|     >   |/_\n" +
        "  .-'  | ,.___., |  '-.\n" +
        "  \\    ;  V'-'V  ;    /\n" +
        "   `\\   \\       /   /`\n" +
        "     `\\  '-...-'  /`\n" +
        "       `\\  / \\  /`\n" +
        "         `\\\\_//`";

    private static final String logoName =
        "";

    /**
     * Converts file path to resource folder based on OS that's used (differentiation between Windows and Unix)
     * @return Converted file path.
     */
    public static String getFilepath()
    {
        return FilenameUtils.separatorsToSystem(filepath);
    }

    public static String getFullLogo() {
        return logoPicture + System.lineSeparator() + logoName;
    }
}
