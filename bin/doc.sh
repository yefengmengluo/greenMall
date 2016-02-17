#!/bin/bash
cd ..
mvn javadoc:javadoc
cd ./target/site
zip  -r api.zip ./apidocs
scp ./api.zip cc@124.42.11.167:/var/www/html/develop-doc
ssh cc@124.42.11.167 "cd /var/www/html/develop-doc;rm -rf ./apidocs; unzip ./api.zip;rm ./api.zip"