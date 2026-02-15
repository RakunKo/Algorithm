select count(*) as FISH_COUNT from FISH_INFO as f_info
    left join FISH_NAME_INFO as fn_info on f_info.FISH_TYPE = fn_info.FISH_TYPE
    where FISH_NAME in ("BASS", "SNAPPER")