/*
 * 매 웹 페이지(또는 리소스 요청)에 대한 응답마다 ServletFilter에서
 * 자신(html, jsp 등)의 url을 <div title=>에 insert 하였음 
 *
 * 이렇게 응답받은 페이지로부터 웹 컴포넌트와 웹 리소스 호출 관계 추출(html -> image, jsp -> image , css -> image ...)
 */

// backgroundScript와 연결 설정.
const contentPort = browser.runtime.connect();


let curResource;
let webCompStack  = [];


// background로 url을 받을 준비가 되었음을 알리는 단순 신호목적의 데이터 없는 메세지 전송
// 이 신호가 전달되면 background script로부터 request된 url들을 전달 받을 수 있음
contentPort.postMessage();

 

// 브라우저에서 서버로 실제 요청 처리된 모든 request를 background script에서 catch하여 전달 받는다.
// 스택 구현이아닌 다른 구현에서 사용한 기능이였으나 추후 이용 예정이라 남겨둠
contentPort.onMessage.addListener(function(m){
  urls.push(m.message);
});

 

 
// background script에서 request를 콘솔에 출력(test를 위해) 및 content script로 전달 후 
// content script와 background script간의 연결을 disConnect 시킴. 
// 이 시점부터 웹 컴포넌트 탐색시작
contentPort.onDisconnect.addListener(function(p){
 // alert("ready");
  urlSearch(document.getElementsByTagName("html")[0]); 
});

 

// 기존 자바로 만들어 놓은 POI API 기반 엑셀 연산 컴포넌트가 있어, 
// content script에서 추출한 컴포넌트에 대한 엑셀 연산은 서버(url : ResourceExcelFilter)에서 처리
function writeExcel(caller, callee){
  let req = new XMLHttpRequest();
  req.open('GET', 'http://127.0.0.1:8082/jpetstore/ResourceExcelFilter?CALLER='+caller+'&CALLEE='+callee,true);
  req.send(null);
}

 
// 웹 component 간의 경계를 나타내기 위해 서버(ServletFilter)에서 동적으로 삽입한 태그(및 어트리뷰트)를 찾아
// 해당 웹 리소스를 실제 포함하는 웹 component를 추출 태그로는 <SCRIPT>, <DIV> 등 무엇을 사용해도 큰 무리는 없지만 간단히 <DIV>태그 사용
// if (src || href element found) -> find ancestor whose nodeName(elementName) is DIV && name  = COMPONENT_LINE
function isCompLine(node){
  return node.getAttribute("name") === "COMPONENT_LINE";
}

 
// 일반적으로 image, 동영상 ,js 파일 등의 경우는 src 어트리뷰트를 사용하고 css의 경우 <LINK>태그를 사용해 리소스를 요청하기 때문에
// 데모버전에서는 src와 (LINK태그의)href 어트리뷰트만 이용. 
function checkNode(node){
  if(node.getAttribute("src")) {
    curResource = node.getAttribute("src");
    return true;
  }else if (node.nodeName == "LINK") {
    curResource = node.getAttribute("href");
    return true;
  }else {
    return false;
  }
}

 
// DFS
function urlSearch(node){
  if(isCompLine(node)){
    webCompStack.push(node.getAttribute('title'));
  }

  if(checkNode(node)){
    writeExcel(webCompStack[webCompStack.length-1], curResource);
  }
 
	if(node.hasChildNodes()){
		Array.from(node.children).forEach(function(element){
			urlSearch(element);
		});
  }

  if(isCompLine(node)){
    webCompStack.pop();  
  }
}