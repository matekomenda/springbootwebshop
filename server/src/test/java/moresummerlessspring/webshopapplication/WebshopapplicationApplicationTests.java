package moresummerlessspring.webshopapplication;


import moresummerlessspring.webshopapplication.Models.Product;
import moresummerlessspring.webshopapplication.Service.CategoryService;
import moresummerlessspring.webshopapplication.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class WebshopapplicationTests {

    public static final String categoryName = "Test Category";

    @Autowired
    CategoryService catService;

    @DisplayName("Spring Test Category")
    @Test
    void testCategory() {
        int categoryId = catService.getCategories().get(0).getCategoryId();
        assertEquals(categoryId, catService.getCategories().get(0).getCategoryId());

        assertEquals("newcat", catService.getCategoryById(categoryId).getName());
    }

    @Autowired
    ProductService productService;

    public static final int cartId = 19;
    public static final int prodId = 21;
    public static final int wishId = 20;

    @DisplayName("Spring Test Product")
    @Test
    void testProduct() {
        Product product = productService.getProductById(prodId);
        String productName = product.getName();
        int productQuantity = product.getQuantity();
        int productPrice = (int)product.getPrice();


        assertEquals(productService.getProductById(prodId).getName(), productName);

        assertEquals(productService.getProductById(prodId).getQuantity(), productQuantity);

        assertEquals(productService.getProductById(prodId).getCart().getCartId(), cartId);

        assertEquals(productService.getProductById(prodId).getPrice(), productPrice);
    }


/*
    @Autowired
    WishlistService wishlistService;

    @DisplayName("Spring Test Wish")
    @Test
    void testWishlist() {
        Wishlist wish = wishlistService.getWishListById(wishId);
        String wishName = wish.getUser().getName();
        int wishQuantity = wish.getProductList().size();
        int wishPrice = (int)wish.getTotal();


        assertEquals(wishlistService.getWishListById(wishId).getUser().getName(), wishName);

        assertEquals(wishlistService.getWishListById(wishId).getTotal(), wishPrice);

        assertEquals(wishlistService.getWishListById(wishId).getProductList().size(), wishQuantity);

    }
*/
/*
    @Mock
    private CategoryRepository categoryRepository;

    @DisplayName("Test Mock")
    @Test
    void testGet() {
        CategoryService mockCategoryService = Mockito.mock(CategoryService.class);
        CategoryRepository mockCategoryRepository = Mockito.mock(CategoryRepository.class);

        //when(mockCategoryService.saveCategory(new Category())).thenReturn(true);

        Category newcat = mockCategoryRepository.findByName("newcat");
        Category added = mockCategoryService.saveCategory(new Category());
        //assertEquals(added, mockCategoryService.getCategoryById(added.getCategoryId()));
        assertEquals(newcat.getName(), mockCategoryService.getCategoryById(newcat.getCategoryId()));
        //when(categoryRepository.findByName("newcat")).thenReturn(categoryRepository.findByName("newcat"));


        //assertEquals(categoryRepository.getOne(22), mockCategoryService.getCategoryById(22));
    }
*/

}
