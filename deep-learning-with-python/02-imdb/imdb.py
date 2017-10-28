from keras import models
from keras import layers
from keras import losses
from keras import metrics
from keras import optimizers
from keras.datasets import imdb
import numpy as np
import matplotlib.pyplot as plt
from time import sleep

#---------------------------------------------------------------------------------------------------
# imdb dataset - binary classifaction from document word frequency vector
#

# load imdb dataset
#
print("loading imdb dataset..")

(train_data, train_labels), (test_data, test_labels) = imdb.load_data(num_words=10000)
print("imdb train_data  : shape={}, type={}".format(train_data.shape, train_data.dtype))
print("imdb train_labels: shape={}, type={}".format(train_labels.shape, train_labels.dtype))
print("imdb test_images : shape={}, type={}".format(test_data.shape, test_data.dtype))
print("imdb test_data   : shape={}, type={}".format(test_labels.shape, test_labels.dtype))

# download the index to word mapping 
word_index = imdb.get_word_index()
def decode_review(word_index, train_data, review_id):
    """
    Get the word decoded representation of the document.
    """
    reverse_word_index = dict([(value, key) for (key, value) in word_index.items()])
    decoded_review = ' '.join([reverse_word_index.get(i - 3, '?') for i in train_data[0]])
    return decoded_review 

print("imdb train_label example :{}".format(train_labels[0]))
print("imdb train_data example :{}".format(train_data[0]))
print("imdb train_data decoded :{}".format(decode_review(word_index, train_data, 0)))
print()

#---------------------------------------------------------------------------------------------------
# reshape imdb dataset
#
print("reshaping imdb  dataset...")

def vectorize_sequences(sequences, dimension=10000):
    # Create an all-zero matrix of shape (len(sequences), dimension)
    results = np.zeros((len(sequences), dimension))
    for i, sequence in enumerate(sequences):
        results[i, sequence] = 1.  # set specific indices of results[i] to 1s
    return results

# vectorized training data
data_train = vectorize_sequences(train_data)
data_test = vectorize_sequences(test_data)

print("imdb train_image vectorised :{}".format(data_train[0]))
print()

label_train = np.asarray(train_labels).astype('float32')
label_test = np.asarray(test_labels).astype('float32')
print("imdb re-shaped tain labels : shape={}, type={}".format(label_train.shape, label_train.dtype))
print("imdb re-shaped test labels : shape={}, type={}".format(label_test.shape, label_test.dtype))

#---------------------------------------------------------------------------------------------------
# create network

model = models.Sequential()
model.add(layers.Dense(16, activation='relu', input_shape=(10000,)))
model.add(layers.Dense(16, activation='relu'))
model.add(layers.Dense(1, activation='sigmoid'))

#---------------------------------------------------------------------------------------------------
# compile network

# model.compile(optimizer='rmsprop',
#               loss='binary_crossentropy',
#               metrics=['accuracy'])

model.compile(optimizer=optimizers.RMSprop(lr=0.001),
              loss=losses.binary_crossentropy,
              metrics=[metrics.binary_accuracy])

#---------------------------------------------------------------------------------------------------
# partition dataset for validation

x_validation = data_train[:10000]
partial_data_train = data_train[10000:]

y_validation = label_train[:10000]
partial_label_train = label_train[10000:]

#---------------------------------------------------------------------------------------------------
# modelling

history = model.fit(partial_data_train,
                    partial_label_train,
                    epochs=20,
                    batch_size=512,
                    validation_data=(x_validation, y_validation))

#---------------------------------------------------------------------------------------------------
# partition dataset for validation

print("Modelling history attibutes: {}",format(history.history.keys()))

acc = history.history['binary_accuracy']
val_acc = history.history['val_binary_accuracy']
loss = history.history['loss']
val_loss = history.history['val_loss']
epochs = range(1, len(acc) + 1)

plt.figure()
plt.plot(epochs, loss, 'bo', label='Training loss')      # "bo" is for "blue dot"
plt.plot(epochs, val_loss, 'b', label='Validation loss') # b is for "solid blue line"
plt.title('Training and validation loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.draw()

# plf()   # clear figure
plt.figure()
plt.plot(epochs, acc, 'bo', label='Training acc')
plt.plot(epochs, val_acc, 'b', label='Validation acc')
plt.title('Training and validation accuracy')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.draw()

plt.show()


model.predict(data_test)

#---------------------------------------------------------------------------------------------------
# display top 5 models

top5 = model.predict(data_test)[:5]
for res in top5:
    print("result: {}".format(res))
