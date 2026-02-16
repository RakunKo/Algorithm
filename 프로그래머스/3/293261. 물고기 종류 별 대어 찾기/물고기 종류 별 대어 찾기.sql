select ID, FISH_NAME, LENGTH from FISH_INFO as fish
    inner join FISH_NAME_INFO as name on fish.FISH_TYPE = name.FISH_TYPE    
    where fish.LENGTH = (
        select max(fish2.LENGTH) from FISH_INFO as fish2
            where fish.FISH_TYPE = fish2.FISH_TYPE
    )
    order by fish.ID