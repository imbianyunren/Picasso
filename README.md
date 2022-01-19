# 養成遊戲之叫我畢卡索

這個遊戲能夠把你所畫的圖

利用AI去背，將你上傳的圖案變成遊戲主角

並可以餵他吃飯、帶他旅遊、工作，看他睡覺

陪著他一起長大

---

**✍ 在這裡感謝我的組員們:

* 鄭雅云 遊戲規則訂定、遊戲介面製作、幫我de超多bug
* 郭怡靚 實做AI偵測去背程式(Python)
* 郭弘偉 測試APP、報告專案內容
* 陳怡靜 遊戲介面製作

---
## 開發環境/安裝需求

* 開發環境：
  Develop Environment：android studio
  
  Create Virtual Device：Phone -> Pixel 4
  
  System Image：R，API Level 30，ABI：x86

* 安裝環境需求：

  android 11 以上(API 30)
  
---

## 執行程式
* 可以先到下面網址下載apk，並使用android11以上的手機/虛擬機安裝操作

  https://drive.google.com/file/d/10lcn9it3prBaz89E2E_kzItqjFTC47eJ/view?usp=sharing
  
* 或是將此專案[clone](https://github.com/imbianyunren/Picasso/archive/refs/heads/main.zip)下來，並匯入android studio執行

* 詳細操作方式 / demo影片

  https://vimeo.com/manage/videos/667870146/7482363a3a

## 程式頁面
  主畫面如下可以在這裡帶寵物吃飯、工作、旅行、睡覺，可以在商店購買食物
  
![image](https://user-images.githubusercontent.com/60705979/150189220-69115e16-46ea-48b4-98db-9490ce44defb.png)


## 使用android studio匯入須知

由於程式內容與python界接，可能需修改build.gradle(:app)內的42行，嵌入您電腦python.exe的位置

![image](https://user-images.githubusercontent.com/60705979/149674692-355a50d8-5253-4ff5-ab87-037c56a6dcd9.png)

