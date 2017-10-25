from keras import models
from keras import layers
from keras.datasets import mnist
from keras.utils import to_categorical

from time import sleep

# load mnist dataset
#
print("loading mnist dataset...")
(train_images, train_labels), (test_images, test_labels) = mnist.load_data()
print("mnist train_images: shape={}, type={}".format(train_images.shape, train_images.dtype))
print("mnist train_labels: shape={}, type={}".format(train_labels.shape, train_labels.dtype))
print("mnist test_images : shape={}, type={}".format(test_images.shape, test_images.dtype))
print("mnist test_labels : shape={}, type={}".format(test_labels.shape, test_labels.dtype))

# reshape mnist dataset
#
print("reshaping mnist dataset...")
train_images = train_images.reshape((60000, 28 * 28))
train_images = train_images.astype('float32') / 255
test_images = test_images.reshape((10000, 28 * 28))
test_images = test_images.astype('float32') / 255
print("reshaped mnist train_images: shape={}, type={}".format(train_images.shape, train_images.dtype))
print("reshaped mnist train_labels: shape={}, type={}".format(train_labels.shape, train_labels.dtype))
print("reshaped mnist test_images : shape={}, type={}".format(test_images.shape, test_images.dtype))
print("reshaped mnist test_labels : shape={}, type={}".format(test_labels.shape, test_labels.dtype))


# creating categorial labels
#
print("creating categorical labels...")
train_labels = to_categorical(train_labels)
test_labels = to_categorical(test_labels)
print("categorical mnist train_labels: shape={}, type={}".format(train_labels.shape, train_labels.dtype))
print("categorical mnist test_labels : shape={}, type={}".format(test_labels.shape, test_labels.dtype))


# define network architecture
#
print("defining network architecture...")
network = models.Sequential()
network.add(layers.Dense(512, activation='relu', input_shape=(28 * 28,)))
network.add(layers.Dense(10, activation='softmax'))

# compile network architecture
#
print("compiling network architecture...")
network.compile(optimizer='rmsprop',
                loss='categorical_crossentropy',
                metrics=['accuracy'])

# train netowrk
#
print("training network...")
network.fit(train_images, train_labels, epochs=5, batch_size=128)

# test accuracy
#
print("evaluating model...")
test_loss, test_acc = network.evaluate(test_images, test_labels)
sleep(1)
print()
print('test_loss:', test_loss)
print('test_acc:', test_acc)
