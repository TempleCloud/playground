import os
import datetime

from keras import models
from keras import layers
from keras import optimizers
from keras.preprocessing import image
from keras.preprocessing.image import ImageDataGenerator

import matplotlib.pyplot as plt


def ensure_dir(path):
    if not os.path.exists(path):
        os.makedirs(path)


def demo_gen_augmented(datagen, image_dir):
    fnames = [os.path.join(image_dir, fname)
              for fname in os.listdir(image_dir)]
    # We pick one image to "augment"
    img_path = fnames[3]
    # Read the image and resize it
    img = image.load_img(img_path, target_size=(150, 150))
    # Convert it to a Numpy array with shape (150, 150, 3)
    x = image.img_to_array(img)
    # Reshape it to (1, 150, 150, 3)
    x = x.reshape((1,) + x.shape)
    # The .flow() command below generates batches of randomly transformed
    # images.
    # It will loop indefinitely, so we need to `break` the loop at some point!
    i = 0
    for batch in datagen.flow(x, batch_size=1):
        plt.figure(i)
        plt.imshow(image.array_to_img(batch[0]))
        i += 1
        if i % 4 == 0:
            break
    plt.show()
    exit


# -----------------------------------------------------------------------------
# Project workspace
#
workspace_dir = os.path.dirname(os.path.realpath(__file__))
base_dir = workspace_dir + '/data_small'
train_dir = os.path.join(base_dir, 'train')
validation_dir = os.path.join(base_dir, 'validation')

test_dir = os.path.join(base_dir, 'test')

model_dir = os.path.join(workspace_dir, 'models')
ensure_dir(model_dir)

# -----------------------------------------------------------------------------
# Data Input Generators
#
# All images will be rescaled by 1./255
#
# train_datagen = ImageDataGenerator(rescale=1./255)
train_datagen = ImageDataGenerator(
        rescale=1./255,
        rotation_range=40,
        width_shift_range=0.2,
        height_shift_range=0.2,
        shear_range=0.2,
        zoom_range=0.2,
        horizontal_flip=True,
        fill_mode='nearest')

test_datagen = ImageDataGenerator(rescale=1./255)

train_generator = train_datagen.flow_from_directory(
        train_dir,
        # All images will be resized to 150x150
        target_size=(150, 150),
        batch_size=20,
        # Since we use binary_crossentropy loss, we need binary labels
        class_mode='binary')

validation_generator = test_datagen.flow_from_directory(
        validation_dir,
        target_size=(150, 150),
        batch_size=20,
        class_mode='binary')

for data_batch, labels_batch in train_generator:
        print('data batch shape:', data_batch.shape)
        print('labels batch shape:', labels_batch.shape)
        break

# demo_gen_augmented(datagen, os.path.join(base_dir, 'test1/cats'))

# -----------------------------------------------------------------------------
# network architecture
#
print("defining network architecture...")
model = models.Sequential()
# covnet layers
model.add(layers.Conv2D(32, (3, 3), activation='relu',
                        input_shape=(150, 150, 3)))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(64, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(128, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
model.add(layers.Conv2D(128, (3, 3), activation='relu'))
model.add(layers.MaxPooling2D((2, 2)))
# classifier dense layers
model.add(layers.Flatten())
# add dropout layer to reduce over-fitting
model.add(layers.Dropout(0.5))
model.add(layers.Dense(512, activation='relu'))
model.add(layers.Dense(1, activation='sigmoid'))
model.summary()


# -----------------------------------------------------------------------------
# compile network architecture
#
print("compiling network architecture...")
model.compile(loss='binary_crossentropy',
              optimizer=optimizers.RMSprop(lr=1e-4),
              metrics=['acc'])


# -----------------------------------------------------------------------------
# train network
#
print("training network...")

now = datetime.datetime.now()
model_name = 'cats_and_dogs_small_imggen_droput_' + now.strftime("%Y%m%d%H%M%S")
history = model.fit_generator(
      train_generator,
      steps_per_epoch=100,
      epochs=50,
      validation_data=validation_generator,
      validation_steps=50)
model.save(os.path.join(model_dir, model_name + '.h5'))


# -----------------------------------------------------------------------------
# plot graphs
#
acc = history.history['acc']
val_acc = history.history['val_acc']
loss = history.history['loss']
val_loss = history.history['val_loss']
epochs = range(1, len(acc) + 1)
# epoch accuracy plot
plt.figure()
plt.plot(epochs, acc, 'bo', label='Training acc')
plt.plot(epochs, val_acc, 'b', label='Validation acc')
plt.title('Training and validation accuracy')
plt.legend()
plt.savefig(os.path.join(model_dir, model_name + '-epoch-acc.png'))
# epoch loss plot
plt.figure()
plt.plot(epochs, loss, 'bo', label='Training loss')
plt.plot(epochs, val_loss, 'b', label='Validation loss')
plt.title('Training and validation loss')
plt.legend()
plt.savefig(os.path.join(model_dir, model_name + '-epoch-loss.png'))
