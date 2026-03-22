import com.google.inject.Inject;
import extensions.UIExtension;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.CatalogCoursesPage;

@ExtendWith(UIExtension.class)
public class CheckTitleAndDateWithCourseCardTest extends BaseTest{

  @Inject
  CatalogCoursesPage catalogCoursesPage;

  @Test
  @Epic("UI test")
  @DisplayName("UI. Поиск курса с самый поздним началом")
  public void findCourseThatStartEarlierTest() {
    catalogCoursesPage.open()
            .checkOpenPage()
            .verifyEarliestCourses()
            .verifyLatestCourses();

  }
}
