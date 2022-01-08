#!/bin/bash

DATE=`date "+%Y%m%d"`
echo ${DATE}

BackupDIR="backup_"${DATE}
BackupDIRRoot="${PWD}/${BackupDIR}"
mkdir ${BackupDIR}
ROOT="${PWD}"


function search {
	cd ${ROOT}/DIR/day$1
	for file in ./*.cs; do
		FILENAME="${file##*/}"
#		echo $FILENAME
		cp $FILENAME ${BackupDIRRoot}
		cd ${BackupDIRRoot}
		mv $FILENAME ${FILENAME%.*}_day$1.cs
		cd ${ROOT}/DIR/day$1
	done
}

#echo $(search 1)
i=0
while [ $i -ne 16 ]
do
	i=$(($i+1))
	{
		$(search ${i} ${j})
	} || {
		echo "day${i} is empty"
	}
done

cd ${BackupDIR}
tree .

cd ${ROOT}
echo "${ROOT}"
zip -r ${BackupDIR}.zip ${BackupDIR}

scp -P 2222 -r ${BackupDIR}.zip nathanTest@192.168.1.151:/backup

rm ${BackupDIR}.zip
rm -r ${BackupDIR}


