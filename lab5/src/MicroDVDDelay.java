import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MicroDVDDelay {

    public String delay(String in, int delay, int fps) throws MicroException{
        StringBuilder result = new StringBuilder();
        String[] lines = in.split("\n");
        Pattern pattern = Pattern.compile("\\{(\\d+)}\\{(\\d+)}(.*)");         // {startFrame}{endFrame}tekst

        for(int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            Matcher matcher = pattern.matcher(line);

            if(!matcher.matches()) {
                throw new InvalidFormatException("Invalid line format, ", i + 1);
            }

            try {
                int startFrame = Integer.parseInt(matcher.group(1));
                int endFrame = Integer.parseInt(matcher.group(2));
                String text = matcher.group(3);

                if (startFrame < 0 || endFrame < 0) {
                    throw new NegativeFrameException("Frame numbers must be non-negative", i + 1);
                }

                if (startFrame > endFrame) {
                    throw new FrameSequenceException("Start frame mustn't be greater thand end frame", i + 1);
                }

                int frameDelay = (delay * fps) / 1000;
                startFrame += frameDelay;
                endFrame += frameDelay;

                result.append(String.format("{%d}{%d}%s%n", startFrame, endFrame, text));
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("Invalid frame number format", i + 1);
            }
        }
        return result.toString();
    }
}
