-- 1. Tabel Role
CREATE TABLE IF NOT EXISTS role (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Insert role data
INSERT INTO role (name) VALUES
('ADMIN'),
('USER')
ON CONFLICT DO NOTHING;

-- 3. Tabel relasi Employee <-> Role
CREATE TABLE IF NOT EXISTS employee_roles (
    employee_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (employee_id, role_id),
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- 4. Assign semua employee ke role USER
INSERT INTO employee_roles (employee_id, role_id)
SELECT e.id, r.id
FROM employee e, role r
WHERE r.name = 'USER';

-- 5. Assign AHMAD HAFIZH ASSA'AD (NPK 71488) ke role ADMIN
INSERT INTO employee_roles (employee_id, role_id)
SELECT e.id, r.id
FROM employee e, role r
WHERE e.npk = '71488' AND r.name = 'ADMIN';
