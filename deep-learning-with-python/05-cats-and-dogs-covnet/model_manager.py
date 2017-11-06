import os
from google.cloud import storage


# -----------------------------------------------------------------------------
# Functions
#
def ensure_bucket(storage_client, bucket_name):
    bucket = storage.bucket.Bucket(storage_client, bucket_name)
    bucket.location = "EU"
    if not bucket.exists():
        bucket.create()
        print('Bucket {} created.'.format(bucket.name))
    return bucket


# -----------------------------------------------------------------------------
# Project workspace
#
workspace_dir = os.path.dirname(os.path.realpath(__file__))
model_dir = os.path.join(workspace_dir, 'models')

# Instantiates a client
storage_client = storage.Client()

buckets = storage_client.list_buckets()
for bucket in buckets:
    print("Bucket: {}".format(bucket))

bucket_name = 'dlwp-models'
bucket = ensure_bucket(storage_client, bucket_name)

model_files = os.listdir(model_dir)
for model_file in model_files:
    blob = bucket.blob(model_file)
    if not blob.exists():
        print("model file does not exist: ", model_file)
        print("uploading...")
        blob.upload_from_filename(os.path.join(model_dir, model_file))
    print("Blob: {}".format(blob))
