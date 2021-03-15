## 05/03/2021 17:37
I have to complete the process of jwt; now I'm working on frontend i have to fix the problems in the cookies storage
the problem is that the client receive authenticationResponse as a string
because in specified the response type in the apiService for post method as {responseType: 'text'}

## 05/03/2021 19:40
trying to find the best practice for handling exceptions:
i find a way which is using an exception handler using @ControllerAdvice


## 14/03/2021
may do it it s so helpful
* article may have prixUnitaireHT and prixUnitaireTTC and tauxTVA
* separer l'api from photo api
* add dtos validators by adding a package of validators classes have a method validate*
* add error codes as enum with an code attribute in it (have a constructor) ex:
ARTICLE_NOT_FOUND, CATEGORY_NOT_FOUND, USER_NOT_FOUND, VENTE_NOT_FOUND, CLIENT_NOT_FOUNE, FOURNISSEUR_NOT_FOUND, ENTREPRISE_NOT_FOUND,
COMMAND_CLIENT_NOT_FOUND, COMMAND_FOURNISSEUR_NOT_FOUND, ...
LIGNE_COMMAND_CLIENT_NOT_FOUND, LIGNE_COMMAND_FOURNISSEUR_NOT_FOUND, LIGNE_VENTE_NOT_FOUND...
MVT_STOCK_NOT_FOUND...
each property has a it s range of errorCode
* add EntityNotFoundException with errorCode propery on it
* add InvalideEntityException with errorCode and errors list property on it
* add handlers package and add handlers ex: ErrorDto which have {codeError, httpCode, message, list errors} peoperties
* add RestExceptionHandler class have @RestControllerAdvice and methods with ExceptionHandler(Exception1.class) for each prsonalized exception
* add utils package which have Constant Interface : APP_ROOT =  
* add on each service's method errors property so at the end you can check for each process if has ran perfectly without any errors or else throw the error with the messages the holds the errorrs cause