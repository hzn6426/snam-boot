#dockerbin="/usr/local/bin/docker"
dockerbin="/usr/bin/docker"
echo "keep only latest two images, delete others..."
docker images | grep snapper/authority | grep -v grep | awk '{print $3}'| tail -n +3 | xargs $dockerbin rmi -f
echo "keep only latest three containers, delete others..."
docker ps --all|grep snam-authority | grep -v grep | awk '{print $1}' | tail -n +4 | xargs $dockerbin rm -f
#stop all restart policy
echo "set previous container not auto start..."
docker ps --all|grep snam-authority | grep -v grep | awk '{print $1}' | xargs docker update --restart=no
# shellcheck disable=SC2126
count=$(${dockerbin} ps  | grep snam-authority | grep -v "grep" | wc -l)
md=$(date "+%m%d")
echo "There are "$count" running docker containers! "
if [ "$count" -gt 0 ];
then
echo "Stop docker containers....."
${dockerbin} ps  | grep snam-authority | grep -v grep | awk '{print $1}' | xargs ${dockerbin} stop
else
echo "There are no containers running....."
fi;
#cd /Users/frog/jenkins_test/book
cd /usr/local/snapper/authority || exit
$dockerbin build -t snapper/authority:v"${md}" .
# shellcheck disable=SC2126
ccount=$(${dockerbin} ps --all | grep snam-authority"${md}" | grep -v "grep" | wc -l)
echo "There are ${ccount} count same name container are exist..."
if [ "$ccount" -gt 0 ];
then
echo "remove exist name container..."
$dockerbin ps --all|grep snam-authority"${md}"  | grep -v grep | awk '{print $1}' | xargs $dockerbin rm -f
fi;
$dockerbin run -di --restart=always --network=host --name snam-authority"${md}" -p 8091:8091 snapper/authority:v"${md}"
# shellcheck disable=SC2086
$dockerbin logs -f snam-authority${md} &> authority${md}.log &
