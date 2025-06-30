# 🐾 Pet Adoption System – JavaFX Desktop Application

A user-friendly and secure **JavaFX-based desktop application** that simulates a pet adoption platform with two types of users: **Admins** (shelter owners) and **Adopters**. The system allows full interaction with pet listings, shelter data, and adoption requests, with data stored via file handling in CSV format.

---

## 🔧 Key Features

### 👥 User Roles

- **Admin (Shelter Owner)**
  - Create and manage shelter account
  - Add, edit, or remove pets
  - Edit or delete shelter info
  - View and respond to adoption requests (Accept/Reject)

- **Adopter**
  - Create, edit, or delete account
  - Send adoption requests to shelters
  - Track request status via notifications
  - View adoption history

---

## 🗂️ System Architecture

### 🗃️ File Handling

- Data is loaded once at program start from `.txt` files (CSV format)
- Changes made during runtime are saved **once on program exit**
- Ensures efficiency and reduces unnecessary file I/O

### ✅ Data Validation

- Unique checks for usernames, emails, and phone numbers
- Validations for required fields before form submission
- Notifications displayed for all errors and successful actions

---

## 🖥️ GUI Overview (JavaFX)

### 🔐 Login & Sign-Up

- Users log in using a unique username and password
- Sign-up supports role selection (admin/adopter)
- Validation prevents duplicate usernames, emails, or phone numbers

### 👤 Adopter Interface

- **Menu:** Edit Profile, View History, Send Request, Notifications, Logout, Delete Account  
- **Send Request:** Choose shelter → Pet type → Available pet → Submit request  
- **Notifications:** Displays real-time updates on request status  
- **My Requests:** View accepted/rejected requests with full details  
- **Edit Info:** Update personal info with live validation  
- **Delete Account:** Full removal of account and reset of adopted pet status  

### 🛠 Admin Interface

- **Menu:** Edit/Delete Shelter Info, Add/Edit/Remove Pets, View Adoption Requests  
- **Manage Pets:** Add unique pet entries per type; prevent duplicates  
- **Edit Pet:** Modify pet health status or age  
- **Remove Pet:** Remove unadopted pets only  
- **Adoption Requests:** View full request details and respond (Accept/Reject)  

---

## 🛠️ Technologies Used

- **Java (OOP, File Handling)**
- **JavaFX** – GUI development
- **CSV (Text File)** – Data storage format
- **FXML / SceneBuilder** – UI Design
- **Exception Handling & Validation Logic**

---

## 📌 Input & Output Scenarios

### Common Scenes

- **Login Scene:** Authenticates based on stored credentials  
- **Sign-Up Scene:** Registers new users with role selection  
- **Error Handling:** Displays specific notifications for missing or invalid input

### Example Workflows

- **Adopter sends request → Admin reviews → Notification updates status**  
- **Admin deletes shelter info → Affects associated pet data**  
- **Adopter deletes account → Pets become adoptable again**

---

## 📁 Data Structure

- `Users.txt`: Stores user credentials and info  
- `Shelters.txt`: Stores shelter details  
- `Pets.txt`: Stores pet info by shelter and type  
- `Requests.txt`: Tracks adoption requests and statuses  

---

## 🚀 Getting Started

1. Clone the repository
2. Open the project in a Java IDE (e.g., IntelliJ, Eclipse)
3. Make sure JavaFX is configured properly
4. Run `Main.java`

---

## 📬 Future Improvements

- Integrate database for persistent, scalable storage  
- Implement image support for pet profiles  
- Add filtering and search in UI for large pet shelters  
- Export adoption history reports in PDF  
