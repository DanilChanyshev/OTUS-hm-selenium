import com.google.inject.Inject;
import data.CatalogCoursesName;
import data.MenuItemData;
import extensions.UIExtension;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;

@ExtendWith(UIExtension.class)
public class CheckOpenCategoryCoursesTest extends BaseTest{

  @Inject
  HomePage homePage;

  @Test
  @Epic("UI test")
  @DisplayName("UI. Проверка открытия страницы 'Категория'")
  public void checkOpenCategoryCoursesTest() {
    homePage.open()
            .moveToButton(MenuItemData.EDUCATION)
            .choseCatalogCourses(CatalogCoursesName.PROGRAMMING)
            .checkOpenPage()
            .checkSelectedFilter(CatalogCoursesName.PROGRAMMING);
  }
}
