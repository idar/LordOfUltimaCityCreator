#!/bin/bash

rm -rf loucc
mkdir loucc
cp -rv target/loucc.jar loucc/
cp -rv target/lib/ loucc/
cp -rv examplefiles loucc/
tar -czvf loucc.tar.gz loucc
