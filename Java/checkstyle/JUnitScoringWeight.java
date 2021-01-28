import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class JUnitScoringWeight {
  public JUnitScoringWeight() {}

  private static Method[] getTestMethods(Class<?> paramClass) {
    Method[] arrayOfMethod1 = paramClass.getDeclaredMethods();

    ArrayList localArrayList = new ArrayList();
    for (Method localMethod : arrayOfMethod1) {
      if (localMethod.getAnnotation(Test.class) != null) {
        localArrayList.add(localMethod);
      }
    }

    return (Method[])localArrayList.toArray(new Method[0]);
  }

  private static Result runTest(final Class<?> paramClass, final String paramString) throws InitializationError {
    BlockJUnit4ClassRunner local1 = new BlockJUnit4ClassRunner(paramClass) {
      protected List<FrameworkMethod> computeTestMethods() {
        try {
          Method localMethod = paramClass.getMethod(paramString, new Class[0]);
          return Arrays.asList(new FrameworkMethod[] { new FrameworkMethod(localMethod) });
        } catch (Exception localException) {
          throw new RuntimeException(localException);
        }

      }
    };

    Result localResult = new Result();
    RunNotifier localRunNotifier = new RunNotifier();
    localRunNotifier.addListener(localResult.createListener());
    local1.run(localRunNotifier);
    return localResult;
  }

  public static double calculateScoreWeight(Class<?> paramClass) {
    double d1 = 0.0D;
    double d2 = 0.0D;

    Method[] arrayOfMethod1 = getTestMethods(paramClass);

    System.out.println("TESTS FAILED...\n");
    for (Method localMethod : arrayOfMethod1) {
      try {
        if (localMethod.getAnnotation(ScoringWeight.class) != null) {
          Result localResult = runTest(paramClass, localMethod.getName());

          if (localResult.wasSuccessful()) {
            d1 += ((ScoringWeight)localMethod.getAnnotation(ScoringWeight.class)).value();
          } else {
            List localList = localResult.getFailures();

            for (int k = 0; k < localList.size(); k++) {
              System.out.println(((Failure)localList.get(k)).getException().getClass().getName() + ": " + ((Failure)localList.get(k)).getMessage());
            }
          }

          d2 += ((ScoringWeight)localMethod.getAnnotation(ScoringWeight.class)).value();
        }
      } catch (InitializationError localInitializationError) {
        localInitializationError.printStackTrace();
      }
    }
    System.out.println("\nEND OF TEST FAILURES\n");

    return d1 / d2;
  }
}
