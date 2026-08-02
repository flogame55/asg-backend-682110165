-- Insert Suppliers (ผู้ผลิต)
INSERT INTO suppliers (id, company_name, country) VALUES (1, 'Tokyo Marui', 'Japan');
INSERT INTO suppliers (id, company_name, country) VALUES (2, 'G&G Armament', 'Taiwan');
INSERT INTO suppliers (id, company_name, country) VALUES (3, 'KWA Performance Industries', 'Taiwan');

-- Insert SerialPlates (เพลทซีเรียล 1-to-1)
INSERT INTO serial_plates (id, serial_number, engraving_text) VALUES (1, 'TM-M4-2026-001', 'Property of US Armed Forces');
INSERT INTO serial_plates (id, serial_number, engraving_text) VALUES (2, 'GG-ARP9-2026-088', 'Combat Machine Custom Edition');
INSERT INTO serial_plates (id, serial_number, engraving_text) VALUES (3, 'KWA-MP7-2026-999', 'Special Operation Command');

-- Insert AirsoftGuns (ปืนบีบีกัน)
INSERT INTO airsoft_guns (id, model_name, fps, price, power_type, supplier_id, serial_plate_id) VALUES (1, 'Tokyo Marui M4A1 MWS', 380, 18500.00, 'GBB', 1, 1);
INSERT INTO airsoft_guns (id, model_name, fps, price, power_type, supplier_id, serial_plate_id) VALUES (2, 'G&G ARP9 2.0', 350, 7800.00, 'AEG', 2, 2);
INSERT INTO airsoft_guns (id, model_name, fps, price, power_type, supplier_id, serial_plate_id) VALUES (3, 'KWA MP7A1 GBB', 400, 11200.00, 'GBB', 3, 3);

-- Insert Accessories (อุปกรณ์แต่ง 1-to-Many)
INSERT INTO accessories (id, name, accessory_type, price, airsoft_gun_id) VALUES (1, 'ACOG 4x Scope w/ RMR RedDot', 'Optics', 2800.00, 1);
INSERT INTO accessories (id, name, accessory_type, price, airsoft_gun_id) VALUES (2, 'KAC QD Suppressor', 'Muzzle Device', 1450.00, 1);
INSERT INTO accessories (id, name, accessory_type, price, airsoft_gun_id) VALUES (3, 'PEQ-15 Red Laser & Flashlight', 'Laser/Light', 1900.00, 1);

INSERT INTO accessories (id, name, accessory_type, price, airsoft_gun_id) VALUES (4, 'M-LOK Handstop Kit', 'Grip', 650.00, 2);
INSERT INTO accessories (id, name, accessory_type, price, airsoft_gun_id) VALUES (5, 'ARP9 Drum Magazine (1500 rds)', 'Magazine', 2200.00, 2);

INSERT INTO accessories (id, name, accessory_type, price, airsoft_gun_id) VALUES (6, 'CQB Micro Red Dot Sight', 'Optics', 1200.00, 3);
