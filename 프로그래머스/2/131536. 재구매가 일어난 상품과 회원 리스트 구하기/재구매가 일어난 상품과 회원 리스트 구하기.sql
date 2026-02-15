select USER_ID, PRODUCT_ID from ONLINE_SALE
    group by USER_ID, PRODUCT_ID
    having count(*) > 1
    order by USER_ID asc, PRODUCT_ID desc;