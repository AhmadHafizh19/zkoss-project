-- Ubah nama role dari 'ADMIN' dan 'USER' menjadi 'ROLE_ADMIN' dan 'ROLE_USER'
UPDATE role SET name = 'ROLE_ADMIN' WHERE name = 'ADMIN';
UPDATE role SET name = 'ROLE_USER' WHERE name = 'USER';
