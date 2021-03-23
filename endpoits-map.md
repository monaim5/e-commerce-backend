## Product Controller
### GET :
* /products : list all products
* /products/{id} : retrieve product by id

### PUT :
* /products/{id} & Payload : update product by id

### POST :
* /products & Payload : Create Product

### DELETE :
* /products/{id} : delete products by its id
---
## Promo Controller
### GET :
* /promos : list all promos
* /promos/{id} : retrieve promo by id
* /promos/{id}/products : retrieve all products belongs to that promo

### PUT :
* /promos/{id} & Payload : update promo by id

### POST :
* /promos & Payload : Create Promo
* /promos/{id}/products & Payload : add product to promo
### DELETE :
* /promos/{id} : delete promo by its id *cascade delete*
* /promos/{id}/products/{productId} : delete the product which have id equals to productId from that promo
* /promos/{id}/products : delete all products belongs to that promo
---
## Photo Controller
### GET :
* /photos/{id} : retrieve photo by id

### PUT :
* /photos/{id} & Payload : update photo by id

### POST :
* /upload & Payload = {file: MultipartFile} : upload photo file
* /photos & Payload : create photo
  
### DELETE :
* /photos/{id} : delete photo by its id *cascade delete*
--- 
