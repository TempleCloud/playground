#!/bin/bash

# resolve script paths
#
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
TARGET_DIR="$(pwd)"
echo "SCRIPT_DIR      : ${SCRIPT_DIR}"
echo "TARGET_DIR      : ${TARGET_DIR}"


# determine host OS and package manager
#
UNAME="$(uname -s)"
case "${UNAME}" in
    Linux*)     OS=linux;;
    Darwin*)    OS=mac-osx;;
    CYGWIN*)    OS=cygwin;;
    MINGW*)     OS=min-gw;;
    *)          OS="UNKNOWN: ${UNAME}"
esac
echo "OS              : ${OS}"
if [ "$OS" == "mac-osx" ]; then
    PACKAGE_MANAGER=$(which brew)
fi
echo "PACKAGE MANAGER : ${PACKAGE_MANAGER}"


# check python and pip installations
#
PYTHON_PATH=$(which python3)
PIP_PATH=$(which pip3)
echo "PYTHON_PATH     : ${PYTHON_PATH}"
echo "PIP_PATH        : ${PIP_PATH}"


# ensure system libraries
#
HDF5=$(brew ls --versions hdf5)
if [ -z "${HDF5}" ]; then
    echo "Installing HDF5..."
    brew install hdf5
fi


# ensure virtual environment 
#
echo "VENV_DIR        : ${VENV_DIR}"
VENV_DIR="${TARGET_DIR}/venv"

if [ ! -d "${VENV_DIR}" ]; then
    echo "Creating 'venv': ${VENV_DIR}"
    mkdir -p ${VENV_DIR}
    python3 -m venv venv
fi
source "${VENV_DIR}/bin/activate"


# install python libraries
#
pip3 install numpy==1.13.3
pip3 install scipy==0.19.1
pip3 install pyaml==17.10.0
pip3 install matplotlib==2.1.0
pip3 install h5py==2.7.1
# install tensor-flow - https://www.tensorflow.org/install/install_mac
pip3 install \
    --upgrade https://storage.googleapis.com/tensorflow/mac/cpu/tensorflow-1.3.0-py3-none-any.whl
# pip3 install \
#     --upgrade https://storage.googleapis.com/tensorflow/mac/cpu/protobuf-3.1.0-cp35-none-macosx_10_11_x86_64.whl
pip3 install keras==2.0.8


