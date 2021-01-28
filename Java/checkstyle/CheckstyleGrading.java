import java.io.BufferedReader;

public class CheckstyleGrading {
  public CheckstyleGrading() {}

  public static int getTotalErrors() {
    Runtime localRuntime = Runtime.getRuntime();
    StringBuilder localStringBuilder = new StringBuilder();
    try {
      Process localProcess = localRuntime.exec(new String[] { "/bin/sh", "-c", "java -jar $LIB/checkstyle-vocareum.jar -c $LIB/checkstyle.xml *.java" });
      localProcess.waitFor();
      Object localObject = new BufferedReader(new java.io.InputStreamReader(localProcess.getInputStream()));
      while (((BufferedReader)localObject).ready()) {
        localStringBuilder.append(((BufferedReader)localObject).readLine() + "\n");
      }
    } catch (java.io.IOException localIOException) {
      localIOException.printStackTrace();
    } catch (InterruptedException localInterruptedException) {
      localInterruptedException.printStackTrace();
    }

    int i = localStringBuilder.lastIndexOf("Audit done.") + 12;

    System.out.println(localStringBuilder.toString());

    if (i == localStringBuilder.length()) {
      return 0;
    }
    Object localObject = localStringBuilder.substring(i + 1);
    String[] arrayOfString = ((String)localObject).split(" ");
    int j = Integer.parseInt(arrayOfString[3]);
    return j;
  }
}
