
install-linux:
	conda env create -f install/virtual_platform_linux.yml

install-windows:
	conda env create -f install/virtual_platform_windows.yml

install-mac:
	conda env create -f install/virtual_platform_mac.yml

clean-install:
	rm -Rf ~/anaconda3/envs/virtual_platform/

init-dbus-mac:
	mkdir -p ~/Library/LaunchAgents
	cp /Users/Temple/anaconda3/envs/virtual_platform/org.freedesktop.dbus-session.plist ~/Library/LaunchAgents/
	launchctl load -w ~/Library/LaunchAgents/org.freedesktop.dbus-session.plist

re-init-dbus-mac:
	launchctl unload -w ~/Library/LaunchAgents/org.freedesktop.dbus-session.plist
	cp /Users/Temple/anaconda3/envs/virtual_platform/org.freedesktop.dbus-session.plist ~/Library/LaunchAgents/
	launchctl load -w ~/Library/LaunchAgents/org.freedesktop.dbus-session.plist

info:
	which anaconda
	which anaconda-navigator

