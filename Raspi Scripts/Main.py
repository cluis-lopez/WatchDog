import logging
import time
import json
import requests
import pusher

import Properties
import Sample
import TakePict

URL = Properties.URL

logging.basicConfig(filename='WatchDog.log',level=logging.DEBUG)

# Get a Picture
TakePict.GetPict()
# Upload it
data = {URL : ('LastPict.jpg' , open('LastPict.jpg', 'r'), 'image/jpeg', {'Expires': '0'})}  
r = requests.post('http://' + URL + '/UploadPict', files = data)
if r.status_code != 201:
    print "Error uploading picture"
    exit()
  
picturl = 'storage.googleapis.com/' + r.content +"?"+str(time.time())

# Get a Temp + HUm + Ligh Sample

t, h, l = Sample.getSample()
pack = { 'TimeStamp': time.strftime("%a %d-%b-%Y %H:%M:%S") ,'Temp':t , 'Hum':h, 'Light':l , 'Pict':picturl }
js = json.dumps(pack)

pusher_client = pusher.Pusher(app_id = Properties.App_id, key = Properties.Key, secret = Properties.Secret, cluster ='eu', ssl =True)
pusher_client.trigger('datos', 'my-event', js)

