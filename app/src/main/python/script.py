import cv2
import numpy as np
import base64
import io
from PIL import Image

def main(data):
    decoded_data = base64.b64decode(data)
    np_data=np.fromstring(decoded_data,np.uint8)
    img_origin=cv2.imdecode(np_data,cv2.IMREAD_UNCHANGED)

    # img_origin = cv2.imread('cat_memes_34.jpg')          #讀圖片

    t_blur = 21
    thresh_1 = 50
    thresh_2 = 200
    dilate = 13
    erode = 11

    img_fin = remove_bg(
        img_origin.copy(),
        BLUR = t_blur,
        CANNY_THRESH_1 = thresh_1,
        CANNY_THRESH_2 = thresh_2,
        MASK_DILATE_ITER = dilate,
        MASK_ERODE_ITER = erode,
    )

    img_after=cv2.cvtColor(img_fin, cv2.COLOR_RGBA2BGRA)
    jpg_after = cv2.imencode('.png', img_after)
    img_str = base64.b64encode(jpg_after[1])
    return ""+str(img_str,'utf-8')

def remove_bg(
        img,
        BLUR = 21,
        CANNY_THRESH_1 = 20,
        CANNY_THRESH_2 = 120,
        MASK_DILATE_ITER = 10,
        MASK_ERODE_ITER = 10,
        MASK_COLOR = (0.0, 0.0, 1.0),
):
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)                        #轉換顏色(從BGR轉換到灰階)
    blurred = cv2.GaussianBlur(gray, (3, 3), 0)                 #模糊化(消除雜訊)
    edges = cv2.Canny(blurred, CANNY_THRESH_1, CANNY_THRESH_2)      #邊緣偵測
    kernel = np.ones((3,3), np.uint8)
    edges = cv2.dilate(edges,kernel, iterations= 3)             #放大邊框
    edges = cv2.erode(edges,kernel, iterations= 3)             #縮小邊框(消除雜訊)
    contour_info = []
    contours, _ =cv2.findContours(                          #找輪廓
        edges, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    for c in contours:
        contour_info.append((
            c,
            cv2.isContourConvex(c),
            cv2.contourArea(c),
        ))
    contour_info = sorted(contour_info, key = lambda c: c[2], reverse= True)
    max_contour = contour_info[0]                   #找出角色的輪廓
    mask = np.zeros(edges.shape)                #建立mask
    cv2.fillPoly(mask, [max_contour[0]], (255))            #把輪廓內的地方塗成白色
    mask = cv2.GaussianBlur(mask, (BLUR, BLUR), 0)
    # cv2.waitKey(0)
    mask_stack = np.dstack([mask]*3)

    mask_stack = mask_stack.astype(
        'float32') / 255.0
    img = img.astype('float32') / 255.0
    masked = (mask_stack * img) + ((1-mask_stack)* MASK_COLOR)              #利用mask對原本圖片進行去背
    masked = (masked * 255).astype('uint8')
    c_blue, c_green, c_red = cv2.split(img)
    img_a = cv2.merge((c_red,c_green,c_blue,mask.astype('float32') / 255.0))        #整合圖片
    return img_a.astype("float32")*255


