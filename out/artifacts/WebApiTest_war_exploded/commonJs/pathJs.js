var pathPre = "http://192.168.31.203:8080/ParkingSystem/";
function getUrl(select,lastPath){
    $(select).attr('action',pathPre+lastPath);
}

function makeUrl(select,lastPath){
	$(select).attr('href',pathPre+lastPath);
}