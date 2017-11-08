import cv2
import numpy as np
import time

def GetPict():
    cap = cv2.VideoCapture(0)
 
    # Increase cam definition   
    ret = cap.set (3, 1280)
    ret = cap.set (4, 960)
  
    # Get a Picture
    ret, frame = cap.read()
    cap.release()
    
    now = time.strftime("%d %b %Y %H:%M:%S")
    cv2.putText(frame, now, (25, frame.shape[0]-25), cv2.FONT_HERSHEY_SIMPLEX, 1, (0,0,255))
    cv2.imwrite('LastPict.jpg', frame)
    
    
if __name__ == "__main__":
    
    GetPict()
