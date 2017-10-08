# -*- coding: utf-8 -*-
"""
"""

import cv2

# load pre-learnt haar cascades -----------------------------------------------
#
# face detector
face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
# eye detecor
eye_cascade = cv2.CascadeClassifier('haarcascade_eye.xml')


# detect face and eye features -----------------------------------------------
#

# define detection function
#
def detect(frame):
    """
    Take the specified cv2 video image frame; search for haar face and eye 
    features, draw a bounding box around each one, and, return the 
    annotated frame.
    """
    
    # create a gray-scale version of the frame
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    
    # detect the haar face feature in the gray-scale image
    face_scale_factor = 1.3
    face_min_accepted_neighbours = 5
    faces = face_cascade.detectMultiScale(gray, 
                                          face_scale_factor, 
                                          face_min_accepted_neighbours)
    
    # for each detected face feature; draw an enclosing rectangle in the frame
    # image, and, search inside that rectangle for haar eye features
    #
    # (x, y): origin or rectangle
    # w: width of rectangle
    # h: height of rectangle
    for (x, y, w, h) in faces:
        # draw rectangle in frame around detected feature
        origin = (x, y)
        adjacency = (x+w, y+h)
        color = (255, 0, 0) # blue
        width = 2
        cv2.rectangle(frame, origin, adjacency, color, width)
        
        # define face region of interest for the grayscale image
        roi_gray = gray[y:y+h, x:x+y]
        # define face region of interest for color video frame
        roi_color = frame[y:y+h, x:x+y]
        
        # detect haar eye features in the grey-scale sub-image
        eye_scale_factor = 1.1
        eye_min_accepted_neighbours = 3
        eyes = eye_cascade.detectMultiScale(roi_gray, 
                                            eye_scale_factor, 
                                            eye_min_accepted_neighbours)
        # for each eye feature detected; draw an enclosing rectangle in the 
        # frame image
        #
        # (ex, ey): origin or rectangle
        # ew: width of rectangle
        # eh: height of rectangle
        for (ex, ey, ew, eh) in eyes:
            # draw rectangle in frame around detected feature
            e_origin = (ex, ey)
            e_adjacency = (ex+ew, ey+eh)
            e_color = (0, 255, 0) # green
            e_width = 2
            cv2.rectangle(roi_color, e_origin, e_adjacency, e_color, e_width)
        
    # return the annotated frame
    return frame


# webcam capture loop ---------------------------------------------------------
#
        
# get the last frame from the webcam
# NB: if multiple camera are available select the required one via the idx.
#     on my mac I had to play around with some apps in-order to activate the
#     camera
webcam_idx = 0
video_capture = cv2.VideoCapture(webcam_idx)

# in an infinte loop - catch images from the webcam, detect haar features, and, 
# draw them in the viewer. 'q' - will exit
while True:
    _, frame = video_capture.read()
    canvas = detect(frame)
    cv2.imshow('Video', canvas)
    # if the user presses 'q' on the keybaord then exist the capture loop
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# release video resources
video_capture.release()
cv2.destroyAllWindows()




