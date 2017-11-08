import time
import Light
import Temp

def getSample():
    """ No args. Get a sample of Temp+Humidity & Light. Discard it. Wait half a second
    """
    Temp.Temp()
    Light.Light()
    time.sleep(0.5)
    
    # Get 5 samples delayed 0,1 sec each to get the average
    t, h, l  = 0, 0, 0
    for i in range(5):
        x, y = Temp.Temp()
        z = Light.Light()
        t = t + x
        h = h + y
        l = l + z
        time.sleep(0.1)
    
    t = t / 5
    h = h / 5
    l = l / 5; l = abs(l-1023)

    # print "Temperature: ", t, "   Humidity:", h,"%", "Light:", l
    return [t,h,l]

if __name__ == "__main__":
    
    t, h, l = getSample()
    print ('Temp : ' + str(t) + ' Hum : ' + str(h) + ' Light : ' + str(l))