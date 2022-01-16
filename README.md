# 養成遊戲之叫我畢卡索
### ver 1.0
目前做到可以輸入圖片並完成去背的工作
以及可以幫寵物取名進入主畫面

TO兩個超棒的AS組員:
---
新增內容於build.gradle(對應到Project: My_Application)及app/build.gradle(對應到Module: MyApplication.app)檔案內

上面新增完後會自動生成python資料夾，但要手動在裡面新增script.py檔

並把app/src/main/python/script.py的內容貼上去

app/src/main/java/com/example/myapplication/input_image.java的內容也貼到input_image.java中


因為有使用python界接，因此需要安裝python才能夠執行

### 可能需修改build.gradle(:app)內的42行，嵌入您電腦python.exe的位置(大部分windows用戶的python.exe位於此)
![image](https://user-images.githubusercontent.com/60705979/149674692-355a50d8-5253-4ff5-ab87-037c56a6dcd9.png)

