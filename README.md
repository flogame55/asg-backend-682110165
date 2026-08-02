# Individual Assignment - Backend Development (Airsoft Store Management System)

## 📌 1. โครงสร้าง Domain Model และคำอธิบาย (Domain Model Overview & Explanation)

โปรเจกต์นี้ได้รับการพัฒนาขึ้นสำหรับ **ระบบจัดการคลังสินค้าปืนบีบีกัน (Airsoft Store Management System)** โดยประกอบด้วย **4 Entities** หลัก และมีการเชื่อมโยงความสัมพันธ์ JPA ครบถ้วนทั้ง **3 รูปแบบ** ตามข้อกำหนดของสไลด์สั่งงาน ([99 Assignment.pptx](file:///d:/BootBasic/Individual%20Assignment/99%20Assignment.pptx)):

```
         +-------------------+
         |     Supplier      |  (ผู้ผลิต / ผู้จัดส่งสินค้า)
         +-------------------+
                   | 1
                   |
                   | N  (Many-to-One)
         +-------------------+
         |    AirsoftGun     |  (ตัวปืนบีบีกัน - Core Entity)
         +-------------------+
          | 1             | 1
          |               |
          | 1 (1-to-1)    | N (1-to-Many)
          v               v
  +---------------+  +---------------+
  |  SerialPlate  |  |   Accessory   |
  +---------------+  +---------------+
  (แผ่นเพลทเลขซีเรียล) (อุปกรณ์เสริม/ของแต่ง)
```

### คำอธิบาย Domain Model และความสัมพันธ์:
1. **`AirsoftGun`** (Core Entity): เป็นศูนย์กลางของระบบ เก็บข้อมูลตัวปืนบีบีกัน (`id`, `modelName`, `fps`, `price`, `powerType`)
2. **`Supplier`**: ผู้ผลิตหรือบริษัทจัดส่งสินค้า
   - **ความสัมพันธ์ `@ManyToOne`**: ปืนบีบีกันหลายกระบอก (`Many`) สามารถมาจากซัพพลายเออร์เจ้าเดียวกันได้ (`One`)
3. **`SerialPlate`**: แผ่นโลหะสลักเลขซีเรียลเฉพาะกระบอก
   - **ความสัมพันธ์ `@OneToOne`**: ปืนบีบีกัน 1 กระบอก (`One`) มีแผ่นเพลทเลขซีเรียลประจำตัวได้เพียง 1 อันเท่านั้น (`One`)
4. **`Accessory`**: อุปกรณ์เสริม/ของแต่ง เช่น กล้องเล็ง หรือท่อเก็บเสียง
   - **ความสัมพันธ์ `@OneToMany`**: ปืนบีบีกัน 1 กระบอก (`One`) สามารถใส่อุปกรณ์แต่งเสริมได้หลายชิ้น (`Many`)

---

## 🛠️ 2. REST API Endpoints

โปรเจกต์นี้ใช้ **DTOs** (`AirsoftGunDTO`, `SupplierDTO`, `SerialPlateDTO`, `AccessoryDTO`) ในการรับและส่งข้อมูลผ่าน REST Endpoints ทั้งหมด:

- **Create**: `POST /api/guns` - เพิ่มข้อมูลปืนบีบีกันกระบอกใหม่ พร้อมกำหนดข้อมูล Supplier, SerialPlate และ Accessories
- **List**: `GET /api/guns` - ดึงรายการข้อมูลปืนบีบีกันทั้งหมดในระบบ
- **Update (Patch)**: `PATCH /api/guns/{id}` - อัปเดตข้อมูลบางฟีลด์ของปืนบีบีกันตาม ID
- **Delete**: `DELETE /api/guns/{id}` - ลบข้อมูลปืนบีบีกันออกจากระบบตาม ID

---

## 🧪 3. การตั้งค่าฐานข้อมูลและการทดสอบ (Testing & Database Setup)

- **Database**: กำหนดค่าให้ใช้ **H2 Database** In-Memory (`jdbc:h2:mem:airsoftdb`) ตรงตามข้อกำหนดของงาน
- **Controller Tests**: อยู่ในไฟล์ [AirsoftGunControllerTest.java](file:///d:/BootBasic/Individual%20Assignment/asg-backend-682110165/web-service/src/test/java/th/camt/controller/AirsoftGunControllerTest.java) มีทั้งหมด 4 Test Methods ทดสอบครบทั้ง Create, List, Update (Patch) และ Delete
- **Test Result**: รันคำสั่ง `mvn clean test` แล้วการทดสอบผ่าน 100% (4/4 methods passed)

---

## 🤖 4. การแสดงการใช้งาน AI (AI Usage Declaration)

เพื่อให้เป็นไปตามเงื่อนไขการส่งงานในสไลด์ ขอประกาศการใช้งาน AI Tool ดังนี้:

- **เครื่องมือ AI ที่ใช้ (AI Tool Used)**: Gemini 3.6 Flash / Antigravity Agent
- **ขอบเขตและวัตถุประสงค์การใช้งาน (Purpose & Scope)**:
  1. **ออกแบบ Domain Model**: ช่วยวิเคราะห์และวางโครงสร้าง Entities 4 ตัวให้ครอบคลุมความสัมพันธ์ JPA ครบ 3 รูปแบบ (`@OneToOne`, `@ManyToOne`, `@OneToMany`)
  2. **เขียนโค้ดเริ่มต้นและ DTOs**: ช่วยเจนเนอเรตโครงสร้างไฟล์ Entity, DTO, JPA Repository และ REST Controller
  3. **เขียน Integration Tests**: ช่วยสร้าง Unit/Integration Tests โดยใช้ `MockMvc` ทดสอบ Controller Endpoints
  4. **จัดทำเอกสาร**: ช่วยสรุปเนื้อหาใน `README.md` และสร้างสคริปต์เตรียมตัวอธิบายโค้ด
