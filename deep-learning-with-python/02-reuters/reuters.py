from keras import models
from keras import layers
from keras.datasets import reuters
from keras.utils.np_utils import to_categorical
import numpy as np
import matplotlib.pyplot as plt
from time import sleep

#---------------------------------------------------------------------------------------------------
# reuters dataset - multiclass classifaction from document word frequency vector
#

# load imdb dataset
#
print("loading reuters dataset..")

(train_data, train_labels), (test_data, test_labels) = reuters.load_data(num_words=10000)
print("train_data  : shape={}, type={}".format(train_data.shape, train_data.dtype))
print("train_labels: shape={}, type={}".format(train_labels.shape, train_labels.dtype))
print("test_images : shape={}, type={}".format(test_data.shape, test_data.dtype))
print("test_data   : shape={}, type={}".format(test_labels.shape, test_labels.dtype))

# download the index to word mapping 
word_index = reuters.get_word_index()
def decode_newswire(word_index, train_data, review_id):
    """
    Get the word decoded representation of the document.
    """
    reverse_word_index = dict([(value, key) for (key, value) in word_index.items()])
    # Offset as 0, 1 and 2 are reserved indices for "padding", "start of sequence", and "unknown".
    decoded_newswire = ' '.join([reverse_word_index.get(i - 3, '?') for i in train_data[0]])
    return decoded_newswire 


print("train_label example :{}".format(train_labels[0]))
print("train_data example :{}".format(train_data[0]))
print("train_data decoded :{}".format(decode_newswire(word_index, train_data, 0)))
print()

#---------------------------------------------------------------------------------------------------
# reshape imdb dataset
#
print("reshaping reuters dataset...")

def vectorize_sequences(sequences, dimension=10000):
    # Create an all-zero matrix of shape (len(sequences), dimension)
    results = np.zeros((len(sequences), dimension))
    for i, sequence in enumerate(sequences):
        results[i, sequence] = 1.  # set specific indices of results[i] to 1s
    return results

# vectorized training data
data_train = vectorize_sequences(train_data)
data_test = vectorize_sequences(test_data)

print("train_image vectorised :{}".format(data_train[0]))
print()

# def to_one_hot(labels, dimension=46):
#     results = np.zeros((len(labels), dimension))
#     for i, label in enumerate(labels):
#         results[i, label] = 1.
#     return results
# 
# one_hot_train_labels = to_one_hot(train_labels)
# one_hot_test_labels = to_one_hot(test_labels)

one_hot_train_labels = to_categorical(train_labels)
one_hot_test_labels = to_categorical(test_labels)
print("re-shaped train labels : shape={}, type={}".format(
    one_hot_train_labels.shape, one_hot_train_labels.dtype))
print("re-shaped test labels  : shape={}, type={}".format(
    one_hot_test_labels.shape, one_hot_train_labels.dtype))

#---------------------------------------------------------------------------------------------------
# create network

model = models.Sequential()
model.add(layers.Dense(64, activation='relu', input_shape=(10000,)))
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(46, activation='softmax'))

#---------------------------------------------------------------------------------------------------
# compile network

model.compile(optimizer='rmsprop',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

#---------------------------------------------------------------------------------------------------
# partition dataset for validation

x_validation = data_train[:1000]
partial_data_train = data_train[1000:]

y_validation = one_hot_train_labels[:1000]
partial_label_train = one_hot_train_labels[1000:]

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

acc = history.history['acc']
val_acc = history.history['val_acc']
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
