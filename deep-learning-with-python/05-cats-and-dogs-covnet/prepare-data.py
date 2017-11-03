import os
import shutil


def ensure_dir(path):
    if not os.path.exists(path):
        os.makedirs(path)


# Project workspace
workspace_dir = os.path.dirname(os.path.realpath(__file__))
print("workspace_dir        :", workspace_dir)

# # The path to the train directory where the original dataset was uncompressed
original_dataset_dir = workspace_dir + '/data/train'
print("original_dataset_dir :", original_dataset_dir)

# The directory where we will store our smaller dataset
base_dir = workspace_dir + '/data_small'
print("output_dir           :", base_dir)
ensure_dir(base_dir)


# Directories for our training, validation and test splits
train_dir = os.path.join(base_dir, 'train')
ensure_dir(train_dir)
validation_dir = os.path.join(base_dir, 'validation')
ensure_dir(validation_dir)
test_dir = os.path.join(base_dir, 'test1')
ensure_dir(test_dir)

# Directory with our training cat pictures
train_cats_dir = os.path.join(train_dir, 'cats')
ensure_dir(train_cats_dir)

# Directory with our training dog pictures
train_dogs_dir = os.path.join(train_dir, 'dogs')
ensure_dir(train_dogs_dir)

# Directory with our validation cat pictures
validation_cats_dir = os.path.join(validation_dir, 'cats')
ensure_dir(validation_cats_dir)

# Directory with our validation dog pictures
validation_dogs_dir = os.path.join(validation_dir, 'dogs')
ensure_dir(validation_dogs_dir)

# Directory with our validation cat pictures
test_cats_dir = os.path.join(test_dir, 'cats')
ensure_dir(test_cats_dir)

# Directory with our validation dog pictures
test_dogs_dir = os.path.join(test_dir, 'dogs')
ensure_dir(test_dogs_dir)

# Copy first 1000 cat images to train_cats_dir
fnames = ['cat.{}.jpg'.format(i) for i in range(1000)]
for fname in fnames:
    src = os.path.join(original_dataset_dir, fname)
    dst = os.path.join(train_cats_dir, fname)
    shutil.copyfile(src, dst)

# Copy next 500 cat images to validation_cats_dir
fnames = ['cat.{}.jpg'.format(i) for i in range(1000, 1500)]
for fname in fnames:
    src = os.path.join(original_dataset_dir, fname)
    dst = os.path.join(validation_cats_dir, fname)
    shutil.copyfile(src, dst)

# Copy next 500 cat images to test_cats_dir
fnames = ['cat.{}.jpg'.format(i) for i in range(1500, 2000)]
for fname in fnames:
    src = os.path.join(original_dataset_dir, fname)
    dst = os.path.join(test_cats_dir, fname)
    shutil.copyfile(src, dst)

# Copy first 1000 dog images to train_dogs_dir
fnames = ['dog.{}.jpg'.format(i) for i in range(1000)]
for fname in fnames:
    src = os.path.join(original_dataset_dir, fname)
    dst = os.path.join(train_dogs_dir, fname)
    shutil.copyfile(src, dst)

# Copy next 500 dog images to validation_dogs_dir
fnames = ['dog.{}.jpg'.format(i) for i in range(1000, 1500)]
for fname in fnames:
    src = os.path.join(original_dataset_dir, fname)
    dst = os.path.join(validation_dogs_dir, fname)
    shutil.copyfile(src, dst)

# Copy next 500 dog images to test_dogs_dir
fnames = ['dog.{}.jpg'.format(i) for i in range(1500, 2000)]
for fname in fnames:
    src = os.path.join(original_dataset_dir, fname)
    dst = os.path.join(test_dogs_dir, fname)
    shutil.copyfile(src, dst)

# Validate
print('total training cat images:', len(os.listdir(train_cats_dir)))
assert len(os.listdir(train_cats_dir)) == 1000
print('total training dog images:', len(os.listdir(train_dogs_dir)))
assert len(os.listdir(train_dogs_dir)) == 1000
print('total validation cat images:', len(os.listdir(validation_cats_dir)))
assert len(os.listdir(validation_cats_dir)) == 500
print('total validation dog images:', len(os.listdir(validation_dogs_dir)))
assert len(os.listdir(validation_dogs_dir)) == 500
print('total test cat images:', len(os.listdir(test_cats_dir)))
assert len(os.listdir(test_cats_dir)) == 500
print('total test dog images:', len(os.listdir(test_dogs_dir)))
assert len(os.listdir(test_dogs_dir)) == 500
