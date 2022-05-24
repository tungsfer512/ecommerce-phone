package io.sfer.xxx.Service;

import io.sfer.xxx.Model.Product;
import io.sfer.xxx.Model.WishList;
import io.sfer.xxx.Respository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    ProductService productService;

    public List<WishList> getAllWishList() {
        return wishListRepository.findAll();
    }

    public Long countExistedWishListByUserIdAndProductId(Long user_id, Long product_id) {
        return wishListRepository.countExistedWishListByUserIdAndProductId(user_id, product_id);
    }

    public WishList getExistedWishListByUserIdAndProductId(Long user_id, Long product_id) {
        return wishListRepository.getExistedWishListByUserIdAndProductId(user_id, product_id);
    }

    public WishList addWishList(WishList wishList) {
        Product product = productService.getProductById(wishList.getProduct_id());
        if(product.getQuantity() > 0) {
            if(wishListRepository.countExistedWishListByUserIdAndProductId(wishList.getUser_id(), wishList.getProduct_id()) == 0) {
                return wishListRepository.save(wishList);
            }
        }
        return wishList;
    }

    public void deleteWishlist(Long id) {
        wishListRepository.deleteById(id);
    }

    public List<WishList> getAllWishlistByUserId(Long id) {
       return wishListRepository.getAllWishListByUserId(id);
    }


    public Long totalProductInWishList(Long user_id) {
        return wishListRepository.totalProductInWishList(user_id);
    }

}
