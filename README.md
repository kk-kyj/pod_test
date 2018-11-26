# pod_test



#container image build command
docker build -t text-read:0.0.1 .

docker build -t text-write:0.0.1 .


#container run command
docker run -it -d --net=host -v /home/jun/docker/example_pod/test_image/kube/txt:/kube/txt --name text-write text-write:0.0.1

docker run -it -d --net=host -v /home/jun/docker/example_pod/test_image/kube/txt:/kube/txt --name text-read text-read:0.0.1

docker run -it -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql
