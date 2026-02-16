select PRODUCT_ID, PRODUCT_NAME, PRODUCT_CD, CATEGORY, PRICE from FOOD_PRODUCT
    group by PRICE
    having max(PRICE) >= (select max(PRICE) from FOOD_PRODUCT)