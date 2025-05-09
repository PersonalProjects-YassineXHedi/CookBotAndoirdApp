# 📱 **CookBot – Android App**

_An Android application that detects food ingredients in real time and suggests personalized salad recipes based on detected ingredients._

---

## 📝 **Description**

**CookBot** is a mobile app built with **Android Studio (Java)** that combines **real-time computer vision** and **recipe suggestion** to help users cook with what they have.

The app uses a **YOLOv12 object detection model**, deployed via **TensorFlow Lite (TFLite)**, to identify ingredients from the camera feed. Once ingredients are detected, the app matches them with a **local SQLite database** of salad recipes to offer personalized meal ideas.

---

## 🔑 **Key Features**

- 📷 **Real-time ingredient detection** using a trained YOLOv12 model,
- 🗂️ **Local database** of curated salad recipes (~800 recipes, 30 ingredients),
- 🛠️ **MVVM architecture** with LiveData + RecyclerView for responsive UI,
- ✅ **Offline functionality**: works without internet once initialized,
- 🔄 **Dynamic updates**: instantly updates the recipe list based on detected ingredients.

---

