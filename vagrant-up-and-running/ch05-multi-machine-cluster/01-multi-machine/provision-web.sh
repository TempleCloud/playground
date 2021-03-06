#!/usr/bin/env bash

echo "Installing Apache and setting it up..."
apt-get update >/dev/null 2>&1
apt-get install -y apache2 >/dev/null 2>&1
rm -rf /var/www
ln -fs /vagrant /var/www
echo "<strong>Apache Vagrant Machine -" `hostname` "</strong>" > /var/www/index.html

echo "Installing curl..."
sudo apt-get install -y curl
