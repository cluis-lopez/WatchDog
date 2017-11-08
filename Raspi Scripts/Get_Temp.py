import logging
import time
import json
import pusher

import Properties
import Sample

URL = Properties.URL

logging.basicConfig(filename='WatchDog.log',level=logging.DEBUG)

# Get a Temp + HUm + Ligh Sample

t, h, l = Sample.getSample()
pack = { 'TimeStamp': time.strftime("%a %d-%b-%Y %H:%M:%S") ,'Temp':t , 'Hum':h, 'Light':l }
js = json.dumps(pack)

pusher_client = pusher.Pusher(app_id = Properties.App_id, key = Properties.Key, secret = Properties.Secret, cluster ='eu', ssl =True)
pusher_client.trigger('voicedata', 'voicedata-event', js)

