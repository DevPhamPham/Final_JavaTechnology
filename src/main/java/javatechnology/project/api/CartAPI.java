package javatechnology.project.api;

import javatechnology.project.dto.CartDTO;
import javatechnology.project.dto.CartItemDTO;
import javatechnology.project.model.Cart;
import javatechnology.project.model.CartItem;
import javatechnology.project.model.Product;
import javatechnology.project.model.User;
import javatechnology.project.service.CartItemService;
import javatechnology.project.service.CartService;
import javatechnology.project.service.ProductService;
import javatechnology.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/api/carts")
public class CartAPI {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    // [GET] ----Lấy tất cả
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<CartDTO> getAllCart() {
        return cartService.getAllCarts();
    }

    // [GET] ----Lấy thông tin giỏ hàng theo ID
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) throws ChangeSetPersister.NotFoundException {
        CartDTO cartDTO = cartService.getCartDTOById(cartId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setCart_total(cartDTO.getCartTotal());
        User user = new User();
        user.setId(cartDTO.getUserId());
        cart.setUser(user);

        return ResponseEntity.ok(cart);
    }
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // [GET] ----Lấy thông tin giỏ hàng theo user_id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public List<CartDTO> getCartsByUserId(@PathVariable long userId) {
        return cartService.getCartByUserId(userId);
    }

    // [POST] ---- Tạo giỏ hàng mới cho người dùng
    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public Cart createCart(@RequestBody CartDTO cartDTO) {
        User user = userService.getUserById(cartDTO.getUserId()).get();
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCart_total(cartDTO.getCartTotal());
        return cartService.saveOrUpdateCart(cart);
    }

    // [PUT] ----Cập nhật thông tin giỏ hàng
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{cartId}")
    public Cart updateCart(@RequestBody CartDTO cartDTO, @PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId).get();
        cart.setCart_total(cartDTO.getCartTotal());
        return cartService.saveOrUpdateCart(cart);
    }

    // [DEL] ----Xóa giỏ hàng theo ID
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        boolean isDeleted = cartService.deleteCartById(cartId);
        if (isDeleted) {
            return ResponseEntity.ok("Xóa giỏ hàng thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy giỏ hàng với ID = " + cartId);
        }
    }

    // [POST] ----Thêm sản phẩm vào giỏ hàng
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{cartId}/cart-item")
    public CartItemDTO addCartItem(@RequestBody CartItemDTO cartItemDTO, @PathVariable Long cartId) {
        CartDTO cartDTO = cartService.getCartDTOById(cartId).orElse(null);
        if (cartDTO == null) {
            return null;
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(cartItemDTO.getQuantity());
        Cart cart = cartService.getCartById(cartId).orElse(null);
        Product product = productService.getProductById(cartItemDTO.getProductId()).orElse(null);
        if (product == null || cart == null) {
            return null;
        }
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        CartItem savedCartItem = cartItemService.saveOrUpdateCartItem(cartItem);
        CartItemDTO savedCartItemDTO = new CartItemDTO();
        savedCartItemDTO.setId(savedCartItem.getId());
        savedCartItemDTO.setQuantity(savedCartItem.getQuantity());
        savedCartItemDTO.setCartId(savedCartItem.getCart().getId());
        savedCartItemDTO.setProductId(savedCartItem.getProduct().getId());
        return savedCartItemDTO;
    }


    // [PUT] ----Cập nhật thông tin sản phẩm trong giỏ hàng
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{cartId}/cart-item/{cartItemIdNumeric}")
    public CartItemDTO updateCartItem(@RequestBody CartItemDTO newCartItemDTO,
                                      @PathVariable Long cartId,
                                      @PathVariable("cartItemIdNumeric") Long cartItemId) {
        CartDTO cartDTO = cartService.getCartDTOById(cartId).orElse(null);
        if (cartDTO == null) {
            return null;
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(newCartItemDTO.getQuantity());
        Cart cart = cartService.getCartById(cartId).orElse(null);
        Product product = productService.getProductById(newCartItemDTO.getProductId()).orElse(null);
        if (product == null || cart == null) {
            return null;
        }
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setId(cartItemId);
        CartItem savedCartItem = cartItemService.saveOrUpdateCartItem(cartItem);
        CartItemDTO savedCartItemDTO = new CartItemDTO();
        savedCartItemDTO.setId(savedCartItem.getId());
        savedCartItemDTO.setQuantity(savedCartItem.getQuantity());
        savedCartItemDTO.setCartId(savedCartItem.getCart().getId());
        savedCartItemDTO.setProductId(savedCartItem.getProduct().getId());
        return savedCartItemDTO;
    }


    // [DEL] ----Xóa sản phẩm khỏi giỏ hàng
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{cartId}/cart-item/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        Cart cart = cartService.getCartById(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("không tồn tại giỏ hàng này trong hệ thống");
        }

        boolean isDeleted = cartItemService.deleteCartItemById(cartItemId);
        if (isDeleted) {
            return ResponseEntity.ok("Xóa sản phẩm khỏi giỏ hàng thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy mục giỏ hàng với ID = " + cartItemId);
        }
    }
}
