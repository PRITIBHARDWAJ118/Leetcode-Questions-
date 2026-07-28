SELECT 
    eu.unique_id, 
    e.name
FROM Employees e        -- Left table (keep EVERYTHING from here)
LEFT JOIN EmployeeUNI eu -- Right table (add details where possible)
    ON e.id = eu.id;     -- How to link them together