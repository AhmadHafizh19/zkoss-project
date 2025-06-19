CREATE TABLE IF NOT EXISTS student (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(255),
    major VARCHAR(100),
    year INT
);

INSERT INTO student (name, email, phone, address, major, year) VALUES
('Andi Wijaya', 'andi.wijaya@example.com', '081234567890', 'Jl. Merdeka No.1, Jakarta', 'Teknik Informatika', 3),
('Budi Santoso', 'budi.santoso@example.com', '082345678901', 'Jl. Mawar No.10, Bandung', 'Sistem Informasi', 2),
('Citra Lestari', 'citra.lestari@example.com', '083456789012', 'Jl. Melati No.5, Surabaya', 'Manajemen', 4),
('Dewi Ayu', 'dewi.ayu@example.com', '084567890123', 'Jl. Kenanga No.8, Yogyakarta', 'Psikologi', 1),
('Eka Putra', 'eka.putra@example.com', '085678901234', 'Jl. Cemara No.3, Medan', 'Teknik Elektro', 2),
('Farhan Malik', 'farhan.malik@example.com', '086789012345', 'Jl. Anggrek No.7, Semarang', 'Kedokteran', 5),
('Gita Pratiwi', 'gita.pratiwi@example.com', '087890123456', 'Jl. Teratai No.12, Bali', 'Desain Komunikasi Visual', 3),
('Hendra Saputra', 'hendra.saputra@example.com', '088901234567', 'Jl. Flamboyan No.4, Makassar', 'Akuntansi', 4),
('Intan Maharani', 'intan.maharani@example.com', '089012345678', 'Jl. Kamboja No.6, Palembang', 'Hukum', 2),
('Joko Setiawan', 'joko.setiawan@example.com', '080123456789', 'Jl. Dahlia No.9, Malang', 'Ilmu Komputer', 1);
