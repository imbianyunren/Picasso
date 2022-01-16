import requests
import base64

def main(img):
    response = requests.post(
        'https://api.remove.bg/v1.0/removebg',
        data={
            'size': 'regular',
            'image_file_b64': img,
            },
        headers={'X-Api-Key': 'scqeATubMsuXt11ZdqqhG1uA'},
    )
    if response.status_code == requests.codes.ok:
        img_after=response.content
        img_str = base64.b64encode(img_after)
        return ""+str(img_str,'utf-8')
    else:
        return ""
