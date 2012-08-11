var req;
var isIE;
var completeField;
var autoRow;
var completeCheatList;
var ESCAPE_KEY = 27;
var Down_KEY = 40;
var UP_KEY = 38;
var nextId = 0;
var currentListItemId = 0;

function init() {
    completeField = document.getElementById("translate-input-form");
    completeCheatList = document.getElementById("cheat-list");

}

function doCompletion() {
		var keyCode = event.keyCode;
		switch (keyCode) {
			case(ESCAPE_KEY):
			case(Down_KEY):
			case(UP_KEY):
				return;
				break;
		}
		var url = "autocomplete?action=complete&id=" + completeField.value;
        req = initRequest();
        req.open("GET", url, true);
        req.onreadystatechange = callback;
        req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
	clearCheatList();
	
	
	if (req.readyState == 4) {
        if (req.status == 200) {
           parseMessages(req.responseXML);
        }
    }
	
}

function appendKey(keyText) {

    var listItem;
    var linkElement;

    if (isIE) {
//        completeTable.style.display = 'block';
//        row = completeTable.insertRow(completeTable.rows.length);
//        cell = row.insertCell(0);
    } else {
        completeCheatList.style.display = 'block';
        listItem = document.createElement("li");
        completeCheatList.appendChild(listItem);
    }

    linkElement = document.createElement("a");
    linkElement.id = "listItem" + ++nextId;
    linkElement.setAttribute("href", "translate?strict=yes&text=" + keyText);
    linkElement.appendChild(document.createTextNode(keyText));
    listItem.appendChild(linkElement);
}
//
//
function getElementY(element){

    var targetTop = 0;

    if (element.offsetParent) {
        while (element.offsetParent) {
            targetTop += element.offsetTop;
            element = element.offsetParent;
        }
    } else if (element.y) {
        targetTop += element.y;
    }
    return targetTop;
}
//

function clearCheatList() {
    if (completeCheatList.getElementsByTagName("li").length > 0) {
    	completeCheatList.style.display = 'none';
        for (loop = completeCheatList.childNodes.length -1; loop >= 0 ; loop--) {
        	completeCheatList.removeChild(completeCheatList.childNodes[loop]);
        }
        nextId = 0;
        currentListItemId = 0;
    }
}
//
//
function parseMessages(responseXML) {

    // совпадения не возвращены
    if (responseXML == null) {
        return false;
    } else {

        var keys = responseXML.getElementsByTagName("keys")[0];

        if (keys.childNodes.length > 0) {

            for (loop = 0; loop < keys.childNodes.length; loop++) {
                var key = keys.childNodes[loop];
                //console.log("key = ", key);
                var keyText = key.getElementsByTagName("text")[0];
                //	console.log("text = ", keyText);
                appendKey(keyText.childNodes[0].nodeValue);
            }
        }
    }
}

function keyControl() {
	var keyCode = event.keyCode;
	//console.log(" keycode: " + keyCode);
	switch(keyCode){
		case ESCAPE_KEY:
			clearCheatList();
			break;
		case Down_KEY:
			selectNextListItem();
			break;
		case UP_KEY:
			selectPreviousListItem();
			break;
		default:
			break;
	}
}

function selectNextListItem() {
	unselectCurrentListItem();
	var listItem = document.getElementById('listItem' + getNextListItemId());
	selectListItem(listItem);
}

function selectPreviousListItem() {
	unselectCurrentListItem();
	var listItem = document.getElementById('listItem' + getPreviousListItemId());
	selectListItem(listItem);
}

function getNextListItemId(){
	if(currentListItemId >= nextId){
		currentListItemId =0;
	}
	return ++currentListItemId;
}

function getPreviousListItemId(){
	if(currentListItemId <= 1){
		currentListItemId = nextId+1;
	}
	return --currentListItemId;
}

function unselectCurrentListItem(){
	if(currentListItemId == 0) return;
	var currentListItem = document.getElementById('listItem' + currentListItemId);
	currentListItem.className = '';
}

function selectListItem(listItem){
	listItem.className='selected';
	var text = listItem.childNodes[0].nodeValue;
	//console.log(text);
	completeField.value = text;
}
