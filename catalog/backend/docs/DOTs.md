## Uso de DTOs

Em um controller não devemos utilizar a Entity pois é comum termos a
necessidade de customizar as informações que serão retornadas nas
requisições, então é uma boa prática utilizar DTOs nos controllers e 
as Entities somente nas services e repositories.
