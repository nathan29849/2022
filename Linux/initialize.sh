#!/bin/sh

ROOT=${PWD}
mkdir DIR
cd ${ROOT}/DIR

i=0
while [ $i -ne 13 ]
do
	i=$(($i+1))
	mkdir day$i
	cd ${ROOT}/DIR/day$i
	randomName=`openssl rand -hex 12`
	echo $randomName
	touch ${randomName}.cs
	touch ${randomName}.py
	touch a.cs
	touch ${randomName}.txt
	cd ${ROOT}/DIR
done

while [ $i -ne 16 ]
do
	i=$(($i+1))
	mkdir day$i
done
