#!/bin/bash

# NB: Unfortunatly, it is necessary to manually log onto the site and accept the terms and 
#     conditions of the competition first.
#
# Manually downloadable from: https://www.kaggle.com/c/dogs-vs-cats/data

function deps() {
    installed=$(pip show kaggle-cli)
    if [ -z  "${installed}" ]; then
        pip install kaggle-cli
    fi
}

function download() {
    echo "Kaggle.com username:"
    read username
    echo "Kaggle.com password:"
    read -s password
    local competition=dogs-vs-cats
    pushd data > /dev/null
    kg download -u ${username} -p ${password} -c ${competition}
    unzip train.zip
    unzip test1.zip
    popd > /dev/null
}

deps && download




