var req;
var isIE;
var completeField;
var completeTable;
var autoRow;

function init() {
    completeField = document.getElementById("complete-field");
    completeTable = document.getElementById("complete-table");
    autoRow = document.getElementById("auto-row");
    completeTable.style.top = getElementY(autoRow) + "px";
}

function doCompletion() {
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
	
	clearTable();
	
	
	if (req.readyState == 4) {
        if (req.status == 200) {
           parseMessages(req.responseXML);
        }
    }
	
}

function appendKey(keyText) {

    var row;
    var cell;
    var linkElement;

    if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell = row.insertCell(0);
    } else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        completeTable.appendChild(row);
    }

    cell.className = "popupCell";

    linkElement = document.createElement("a");
    linkElement.className = "popupItem";
    linkElement.setAttribute("href", "translate?strict=yes&text=" + keyText);
    linkElement.appendChild(document.createTextNode(keyText));
    cell.appendChild(linkElement);
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
function clearTable() {
    if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
        for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
            completeTable.removeChild(completeTable.childNodes[loop]);
        }
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
            completeTable.setAttribute("bordercolor", "black");
            completeTable.setAttribute("border", "1");

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
