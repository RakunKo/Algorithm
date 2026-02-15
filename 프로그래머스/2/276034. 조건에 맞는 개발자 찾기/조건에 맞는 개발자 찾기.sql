select ID, EMAIL, FIRST_NAME, LAST_NAME from DEVELOPERS
    where (
        SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE NAME IN ('Python', 'C#'))
    ) != 0
    order by ID;