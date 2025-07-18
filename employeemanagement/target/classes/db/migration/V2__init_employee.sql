CREATE TABLE IF NOT EXISTS employee (
    id SERIAL PRIMARY KEY,
    npk VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    division VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('Active', 'Inactive')),
    image_url VARCHAR(255),
    password VARCHAR(100) NOT NULL DEFAULT 'password'
);

INSERT INTO employee (npk, name, email, phone, division, status, image_url, password) VALUES
('71478', 'NABILA KARIN', 'nabilakarin02@gmail.com', '085694517410', 'Data', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71484', 'RAVEN DANIEL MARTIN', 'ravendaniel0@gmail.com', '085212411844', 'IT', 'Inactive', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71485', 'ALICE SHIZUKA HUTAGAOL', 'aliceshizuka@gmail.com', '082111160512', 'Data', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71489', 'VINCENT BHARATA', 'vincentbharataa@gmail.com', '082113372884', 'IT', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71477', 'MARCELLA AURELIA YATIJAN', 'cellaureliay@gmail.com', '081219685684', 'Data', 'Inactive', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71482', 'ADENAWATI HASIM', 'denahsm1112@gmail.com', '081296647388', 'IT', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71483', 'THEOPATRA KENNY SUSANTO', 'theopatra.ks@gmail.com', '08970339996', 'Data', 'Inactive', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71488', 'AHMAD HAFIZH ASSAAD', 'assaad.hafizh1904@gmail.com', '081249556050', 'IT', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71486', 'AHMAD ARDRA DAMARJATI', 'ahmad.ardra30@gmail.com', '081389955496', 'Data', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71479', 'LEONARD SEAN LEE', 'slleonard86@gmail.com', '082111007107', 'IT', 'Inactive', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71487', 'MICHAELL ABELARD HENDRA', 'michaellabelard24@gmail.com', '087786230620', 'Data', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71476', 'ANGELLA ANANTA BATUBARA', 'angellaa59@gmail.com', '08181402003', 'IT', 'Inactive', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71481', 'WILLMAN SATRIA SITUMORANG', 'willman.satria5@gmail.com', '085270819529', 'Data', 'Active', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS'),
('71480', 'PUTRA ADHLI FALAH', 'adhli.falah@gmail.com', '0895636701004', 'IT', 'Inactive', NULL, '$2a$12$qIz45/1/JOELsYk.sq1Th.vZSQn7mW/QMmYUSY8wcysZrBjH7fVVS');
