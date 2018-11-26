let requestUrls=[];
let port;

browser.webRequest.onBeforeRequest.addListener(
  putUrl,
  { 
    urls: ['<all_urls>']
  }
);

browser.runtime.onConnect.addListener(p => {
 port=p;
 console.log('connected');
 
 port.onMessage.addListener(m =>{
   console.log('onMessage');
   
   requestUrls.forEach(element =>{
     console.log(element);
     
     port.postMessage({
        message:element
     });
   });
   requestUrls=[];
   port.disconnect();
 });
});


// ResourceExcelFilter라는 url에 대한 웹 요청은 추출한 웹 컴포넌트와 리소스를 엑셀 파일에 쓰기 위해 contentScript에서 ajax 호출 
// 한 것. 이는 원래의 웹 페이지에 실제 존재하는 요청 정보가 아니므로 따로 처리함 그리고 테스트 확인을 위한 console log 수행
function putUrl(requestDetails){
  if(requestDetails.url.includes('ResourceExcelFilter')){
    console.log('--------------------'+requestDetails.url + '--------------------');
    return;
  }

  requestUrls.push(requestDetails.url);
}
