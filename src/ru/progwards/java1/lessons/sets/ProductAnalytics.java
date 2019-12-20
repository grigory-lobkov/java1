package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
2.1 Создать класс Product - товар,
2.2 Создать private String code - уникальный артикул товара
2.3 Создать конструктор public Product(String code)
2.4 Метод public String getCode()
*/
class Product {
    private String code; //уникальный артикул товара

    public Product(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

/*
2.5 Создать класс Shop - магазин
2.6 Создать private List<Product> products - товары имеющиеся в магазине
2.7 Создать конструктор public Shop(List<Product> products)
2.8 Создать метод public List<Product> getProducts()
*/
class Shop {
    private List<Product> products; //товары имеющиеся в магазине

    public Shop(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}


public class ProductAnalytics {
/*
2.10 Создать private List<Shop> shops - список магазинов
2.11 Создать private List<Product> allProducts - список всех товаров
2.12 Создать конструктор  public ProductAnalytics(List<Product> products, List<Shop> shops)
2.13 Создать функцию public Set<Product> existInAll() - товары из products, которые имеются во всех магазинах
2.14 Создать функцию public Set<Product> existAtListInOne() - товары из products, которые имеются хотя бы в одном магазине
2.15 Создать функцию public Set<Product> notExistInShops() - товары из products, которых нет ни в одном магазине
2.16 Создать функцию public Set<Product> existOnlyInOne() - товары из products, которые есть только в одном магазине
*/

    private List<Shop> shops; //список магазинов
    private List<Product> allProducts; //список всех товаров

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.allProducts = products;
    }

    //товары из products, которые имеются во всех магазинах
    public Set<Product> existInAll() {
        if (shops == null || shops.size() == 0) return new HashSet<>();
        Iterator it = shops.iterator();
        //Set<Product> products = new HashSet<Product>(((Shop)it.next()).getProducts());
        Set<Product> products = new HashSet<Product>(allProducts);
        while (it.hasNext()) {
            products.retainAll(new HashSet<Product>(((Shop) it.next()).getProducts()));
        }
        return products;
    }
}
