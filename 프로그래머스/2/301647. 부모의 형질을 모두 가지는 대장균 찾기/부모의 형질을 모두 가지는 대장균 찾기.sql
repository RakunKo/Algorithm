select child.ID, child.GENOTYPE, parent.GENOTYPE as PARENT_GENOTYPE from ECOLI_DATA as child
    left join ECOLI_DATA as parent on child.PARENT_ID = parent.ID 
    where parent.GENOTYPE & child.GENOTYPE = parent.GENOTYPE
    order by child.ID asc
    