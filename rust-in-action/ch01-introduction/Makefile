
name=new-project

new:
	cargo new --bin ${name}

run:
	pushd `pwd`/${name} && cargo run && popd

info:
	popd `pwd`/${name} && tree --dirsfirst ${name} && popd

