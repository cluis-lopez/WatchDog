Scripts to install in your Raspebrry Pi creating a directory (ex. WatchDog) and copy them into.

In addition, you'll need to have installed:

* **Python**, **node** and **OpenCV** come preinstalled in the standard Raspbian distro. If you use a different OS install them

* Install python library to manage the DHT22 temperature sensor from [Adafruit](https://github.com/adafruit/Adafruit_Python_DHT/)

* Install the python pusher using `pip`:
```
sudo pip install pusher
```
	
* Install the node.js javascript Pusher library uning `npm`  :
```
sudo npm install -g pusher-js
```

Run the server:
```
chmod +x launch
./launch
```
