/*ordenando pelo nome da categoria*/
select pc.name as category, p.nm_product, p.price
from ec_product_category pc
join ec_product_category_aux pca ON pca.product_id = pc.id
join ec_product p ON p.id = pca.category_id
order by pc.name;

/*filtrando produtos de uma categoria*/
select pc.name as category, p.nm_product, p.price
from ec_product_category pc
join ec_product_category_aux pca ON pca.product_id = pc.id
join ec_product p ON p.id = pca.category_id
where pc.id = 1;

/*Para passar mais de um valor na busca utiliza-se o in()*/
select *
from ec_product_category c
where c.id in (13, 14, 15);

/*Neste exemplo temos um produto que pertence a mais de uma categoria ao mesmo tempo.
Essa query retorna esse produto.
Note que o sum(p.price) está ai para fins de estudo.
Ele soma os valores do price pra poder fazer o group by pelo nome.
Se quiser que retorne somente o produto basta retirar o price.*/
select p.nm_product, sum(p.price)
from ec_product_category pc
join ec_product_category_aux pca ON pca.product_id = pc.id
join ec_product p ON p.id = pca.category_id
where pc.id in (13, 14, 15)
group by(p.nm_product);

