# 養成遊戲之叫我畢卡索

這個遊戲能夠把你所畫的圖

利用Python+OpenCV2實現去背功能，將你上傳的圖案變成遊戲主角

並可以餵他吃飯、帶他旅遊、工作，看他睡覺

陪著他一起長大😆


---
### **✍在這裡感謝我的組員們:**

* **鄭雅云** 遊戲規則訂定、遊戲介面製作、幫我de超多bug
* **郭怡靚** 實做圖片去背技術(Python+OpenCV)
* **郭弘偉** 測試APP、報告專案內容
* **陳怡靜** 遊戲介面製作

---
### 👇所有的檔案內容(APK、圖片測試檔、原始python去背程式檔、Demo影片)

https://drive.google.com/drive/folders/1J4hm4pHZSYeYFOYKR0kfrI8vxAi9i49f?usp=sharing

---
## 開發環境/安裝需求

* **開發環境：**
  Develop Environment：android studio
  
  Create Virtual Device：Phone -> Pixel 4
  
  System Image：R，API Level 30，ABI：x86

* **安裝環境需求：**

  android 11 以上(API 30)
  
---
## 圖片去背技術

可以參考下方連結的文件說明:

https://drive.google.com/file/d/1EJ1VryHlZ9obfyUx149KSYzKZgPm1LuN/view?usp=sharing

---

## 實做結果

**原圖:**

![cat_memes_342](https://user-images.githubusercontent.com/60705979/150199177-c0aa9c81-ea54-428e-b12d-edff48d78a0e.jpg)

**經過去背程式後:**

![output](https://user-images.githubusercontent.com/60705979/150199298-c7fe9f38-da25-45c6-bd11-932994ddde79.png)


---
## 執行程式
* 可以先到下面網址下載apk，並使用android11以上的手機/虛擬機安裝操作

  https://drive.google.com/file/d/1uUmrHua4ZJknlK5dsmC35rGST-CC30FV/view?usp=sharing
  
* 或是將此專案[clone](https://github.com/imbianyunren/Picasso/archive/refs/heads/main.zip)下來，並匯入android studio執行

* 詳細操作方式 / demo影片

  https://vimeo.com/manage/videos/667870146/7482363a3a

---
## 程式頁面

  主畫面如下可以在這裡帶寵物吃飯、工作、旅行、睡覺，可以在商店購買食物(詳細內容置於報告與demo影片中)
  
![image](https://user-images.githubusercontent.com/60705979/150189220-69115e16-46ea-48b4-98db-9490ce44defb.png)

---

## 使用android studio匯入須知

由於程式內容與python界接，可能**需修改build.gradle(:app)內的42行**，嵌入您電腦python.exe的位置

![image](https://user-images.githubusercontent.com/60705979/149674692-355a50d8-5253-4ff5-ab87-037c56a6dcd9.png)

---

## Meet the Team 

許庭涵
鄭雅云
郭怡靚
郭弘偉
陳怡靜

---

## Reference

Python+OpenCV實做去背功能:

https://youtu.be/FemFCcYOXsk

APP與Python整合:

https://youtu.be/gHrfPuT4qDE

Android Studio:

http://www.aaronlife.com/v1/teaching/android_sharedpreferences.html

https://iter01.com/372992.html

https://xken831.pixnet.net/blog/post/457167959-%5Bandroid%5Dandroid-%E8%B7%B3%E5%87%BA%E6%8F%90%E7%A4%BA%E8%A6%96%E7%AA%97-%28alertdialog-%E5%B0%8D%E8%A9%B1%E6%96%B9
