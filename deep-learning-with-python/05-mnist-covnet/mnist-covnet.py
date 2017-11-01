from keras import models
from keras import layers
from keras.datasets import mnist
from keras.utils import to_categorical

from time import sleep

# load mnist dataset
#
print("loading mnist dataset...")
(train_images, train_labels), (test_images, test_labels) = mnist.load_data()
print("train_images: shape={}, type={}".format(
    train_images.shape, train_images.dtype))
print("train_labels: shape={}, type={}".format(
    train_labels.shape, train_labels.dtype))
print("test_images : shape={}, type={}".format(
    test_images.shape, test_images.dtype))
print("test_labels : shape={}, type={}".format(
    test_labels.shape, test_labels.dtype))

# reshape mnist dataset
#
print("reshaping mnist dataset...")
train_images = train_images.reshape((60000, 28, 28, 1))
train_images = train_images.astype('float32') / 255
test_images = test_images.reshape((10000, 28, 28, 1))
test_images = test_images.astype('float32') / 255
print("reshaped train_images: shape={}, type={}".format(
    train_images.shape, train_images.dtype))
print("reshaped train_labels: shape={}, type={}".format(
    train_labels.shape, train_labels.dtype))
print("reshaped test_images : shape={}, type={}".format(
    test_images.shape, test_images.dtype))
print("reshaped test_labels : shape={}, type={}".format(
    test_labels.shape, test_labels.dtype))


# creating categorial labels
#
print("creating categorical labels...")
train_labels = to_categorical(train_labels)
test_labels = to_categorical(test_labels)
print("categorical train_labels: shape={}, type={}".format(
    train_labels.shape, train_labels.dtype))
print("categorical test_labels : shape={}, type={}".format(
    test_labels.shape, test_labels.dtype))


# define network architecture
#
print("defining network architecture...")
model = models.Sequential()
# covnet layers
model.add(layers.Conv2D(
    32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(
    64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(
    64, (3, 3), activation='relu'))
# classifier dense layers
model.add(layers.Flatten())
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(10, activation='softmax'))
model.summary()

# compile network architecture
#
print("compiling network architecture...")
model.compile(optimizer='rmsprop',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# train netowrk
#
print("training network...")
model.fit(train_images, train_labels, epochs=5, batch_size=64)

# test accuracy
#
print("evaluating model...")
test_loss, test_acc = model.evaluate(test_images, test_labels)
sleep(1)
print()
print('test_loss:', test_loss)
print('test_acc:', test_acc)

