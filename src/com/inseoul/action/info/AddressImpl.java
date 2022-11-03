package com.inseoul.action.info;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.inseoul.action.ProfileCommandInter;
import com.inseoul.model.InfoModel;
import com.inseoul.util.ValidationUtil;
import com.inseoul.vo.InfoAddressBean;

public class AddressImpl implements ProfileCommandInter{

  static AddressImpl impl = new AddressImpl();
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  public static AddressImpl instance(){
    return impl;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 
   * ---------------------------------------------------------------------------------------------------- */
  @Override
  public String showData(HttpServletRequest request, HttpServletResponse response) throws Exception {

    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    InfoAddressBean addressBean = new InfoAddressBean();
    addressBean.setUid(Integer.parseInt(uid));
    InfoAddressBean addressInfo = model.getAddressInfo(addressBean);
    request.setAttribute("addressInfo", addressInfo);
    
    LinkedHashMap<String, String> map = new  LinkedHashMap<>();
    map.put("233", "가나 +233");
    map.put("241", "가봉 +241");
    map.put("592", "가이아나 +592");
    map.put("220", "감비아 +220");
    map.put("502", "과테말라 +502");
    map.put("1671", "괌 +1 671");
    map.put("1473", "그레나다 +1 473");
    map.put("30", "그리스 +30");
    map.put("224", "기니 +224");
    map.put("245", "기니비사우 +245");
    map.put("264", "나미비아 +264");
    map.put("674", "나우루 +674");
    map.put("234", "나이지리아 +234");
    map.put("672", "남극,오스트레일리아의외 +672");
    map.put("27", "남아프리카공화국 +27");
    map.put("31", "네덜란드 +31");
    map.put("599", "네덜란드령보네르 +599");
    map.put("297", "네덜란드령아루바 +297");
    map.put("977", "네팔 +977");
    map.put("47", "노르웨이 +47");
    map.put("64", "뉴질랜드 +64");
    map.put("683", "뉴질랜드령니우에 +683");
    map.put("690", "뉴질랜드령토켈라우제도 +690");
    map.put("227", "니제르 +227");
    map.put("505", "니카라과 +505");
    map.put("82", "대한민국 +82");
    map.put("45", "덴마크 +45");
    map.put("299", "덴마크령그린란드 +299");
    map.put("298", "덴마크령페로제도 +298");
    map.put("1809", "도미니카공화국 +1 809");
    map.put("1829", "도미니카공화국 2 +1 829");
    map.put("1849", "도미니카공화국 3 +1 849");
    map.put("49", "독일 +49");
    map.put("670", "동티모르 +670");
    map.put("856", "라오스 +856");
    map.put("231", "라이베리아 +231");
    map.put("371", "라트비아 +371");
    map.put("7", "러시아/카자흐스탄 +7");
    map.put("961", "레바논 +961");
    map.put("266", "레소토 +266");
    map.put("40", "루마니아 +40");
    map.put("352", "룩셈부르크 +352");
    map.put("250", "르완다 +250");
    map.put("218", "리비아 +218");
    map.put("370", "리투아니아 +370");
    map.put("423", "리히텐슈타인 +423");
    map.put("261", "마다가스카르 +261");
    map.put("692", "마셜제도공화국 +692");
    map.put("691", "마이크로네시아연방 +691");
    map.put("853", "마카오 +853");
    map.put("389", "마케도니아공화국 +389");
    map.put("265", "말라위 +265");
    map.put("60", "말레이시아 +60");
    map.put("223", "말리 +223");
    map.put("52", "멕시코 +52");
    map.put("377", "모나코 +377");
    map.put("212", "모로코 +212");
    map.put("230", "모리셔스 +230");
    map.put("222", "모리타니아 +222");
    map.put("258", "모잠비크 +258");
    map.put("382", "몬테네그로 +382");
    map.put("373", "몰도바 +373");
    map.put("960", "몰디브 +960");
    map.put("356", "몰타 +356");
    map.put("976", "몽골 +976");
    map.put("1", "미국/캐나다 +1");
    map.put("1670", "미국령북마리아나제도 +1 670");
    map.put("95", "미얀마 +95");
    map.put("678", "바누아투 +678");
    map.put("973", "바레인 +973");
    map.put("1246", "바베이도스 +1 246");
    map.put("1242", "바하마 +1 242");
    map.put("880", "방글라데시 +880");
    map.put("229", "베냉 +229");
    map.put("58", "베네수엘라 +58");
    map.put("84", "베트남 +84");
    map.put("32", "벨기에 +32");
    map.put("375", "벨라루스 +375");
    map.put("501", "벨리즈 +501");
    map.put("387", "보스니아헤르체고비나 +387");
    map.put("267", "보츠와나 +267");
    map.put("591", "볼리비아 +591");
    map.put("257", "부룬디 +257");
    map.put("226", "부르키나파소 +226");
    map.put("975", "부탄 +975");
    map.put("359", "불가리아 +359");
    map.put("55", "브라질 +55");
    map.put("673", "브루나이 +673");
    map.put("685", "사모아 +685");
    map.put("966", "사우디아라비아 +966");
    map.put("378", "산마리노 +378");
    map.put("239", "상투메프린시페 +239");
    map.put("221", "세네갈 +221");
    map.put("381", "세르비아 +381");
    map.put("248", "세이셀 +248");
    map.put("1784", "세인트빈센트그레나딘 +1 784");
    map.put("252", "소말리아 +252");
    map.put("677", "솔로몬제도 +677");
    map.put("249", "수단 +249");
    map.put("597", "수리남 +597");
    map.put("94", "스리랑카 +94");
    map.put("268", "스와질랜드 +268");
    map.put("46", "스웨덴 +46");
    map.put("41", "스위스 +41");
    map.put("34", "스페인 +34");
    map.put("421", "슬로바키아 +421");
    map.put("386", "슬로베니아 +386");
    map.put("963", "시리아 +963");
    map.put("232", "시에라리온 +232");
    map.put("65", "싱가포르 +65");
    map.put("971", "아랍에미리트 +971");
    map.put("374", "아르메니아 +374");
    map.put("54", "아르헨티나 +54");
    map.put("1684", "아메리칸사모아 +1 684");
    map.put("354", "아이슬란드 +354");
    map.put("509", "아이티 +509");
    map.put("353", "아일랜드 +353");
    map.put("994", "아제르바이잔 +994");
    map.put("93", "아프가니스탄 +93");
    map.put("376", "안도라 +376");
    map.put("355", "알바니아 +355");
    map.put("213", "알제리 +213");
    map.put("244", "앙골라 +244");
    map.put("251", "에디오피아 +251");
    map.put("291", "에리트레아 +291");
    map.put("372", "에스토니아 +372");
    map.put("593", "에콰도르 +593");
    map.put("503", "엘살바도르 +503");
    map.put("44", "영국 +44");
    map.put("290", "영국령세인트헬레나 +290");
    map.put("246", "영국령인도양지역 +246");
    map.put("350", "영국령지브롤터 +350");
    map.put("500", "영국령포클랜드제도 +500");
    map.put("967", "예멘 +967");
    map.put("968", "오만 +968");
    map.put("43", "오스트리아 +43");
    map.put("504", "온두라스 +504");
    map.put("962", "요르단 +962");
    map.put("256", "우간다 +256");
    map.put("598", "우루과이 +598");
    map.put("998", "우즈베키스탄 +998");
    map.put("380", "우크라이나 +380");
    map.put("964", "이라크 +964");
    map.put("98", "이란 +98");
    map.put("972", "이스라엘 +972");
    map.put("20", "이집트 +20");
    map.put("39", "이탈리아 +39");
    map.put("91", "인도 +91");
    map.put("62", "인도네시아 +62");
    map.put("81", "일본 +81");
    map.put("1876", "자메이카 +1 876");
    map.put("260", "잠비아 +260");
    map.put("240", "적도기니 +240");
    map.put("995", "조지아 +995");
    map.put("86", "중국 +86");
    map.put("236", "중앙아프리카공화국 +236");
    map.put("253", "지부티 +253");
    map.put("263", "짐바브웨 +263");
    map.put("235", "차드 +235");
    map.put("420", "체코 +420");
    map.put("56", "칠레 +56");
    map.put("237", "카메룬 +237");
    map.put("238", "카보베르데 +238");
    map.put("974", "카타르 +974");
    map.put("855", "캄보디아왕국 +855");
    map.put("254", "케냐 +254");
    map.put("269", "코모로,마요트 +269");
    map.put("506", "코스타리카 +506");
    map.put("225", "코트디부아르 +225");
    map.put("57", "콜롬비아 +57");
    map.put("242", "콩고 +242");
    map.put("243", "콩고민주공화국 +243");
    map.put("53", "쿠바 +53");
    map.put("965", "쿠웨이트 +965");
    map.put("682", "쿡제도 +682");
    map.put("385", "크로아티아 +385");
    map.put("996", "키르기스스탄 +996");
    map.put("686", "키리바시 +686");
    map.put("357", "키프로스 +357");
    map.put("886", "타이완 +886");
    map.put("992", "타지키스탄 +992");
    map.put("255", "탄자니아 +255");
    map.put("66", "태국 +66");
    map.put("90", "터키 +90");
    map.put("228", "토고 +228");
    map.put("676", "통가 +676");
    map.put("993", "투르크메니스탄 +993");
    map.put("216", "튀니지 +216");
    map.put("1868", "트리니다드토바고 +1 868");
    map.put("507", "파나마 +507");
    map.put("595", "파라과이 +595");
    map.put("92", "파키스탄 +92");
    map.put("675", "파푸아뉴기니 +675");
    map.put("680", "팔라우 +680");
    map.put("970", "팔레스타인 +970");
    map.put("51", "페루 +51");
    map.put("351", "포르투갈 +351");
    map.put("48", "폴란드 +48");
    map.put("1787", "푸에르토리코 +1 787");
    map.put("33", "프랑스 +33");
    map.put("590", "프랑스령과들루프 +590");
    map.put("594", "프랑스령기아나 +594");
    map.put("687", "프랑스령뉴칼레도니아 +687");
    map.put("262", "프랑스령레위니옹 +262");
    map.put("596", "프랑스령마르티니크 +596");
    map.put("508", "프랑스령생피에르미클롱 +508");
    map.put("681", "프랑스령월리스푸투나제 +681");
    map.put("689", "프랑스령폴리네시아 +689");
    map.put("679", "피지 +679");
    map.put("358", "핀란드 +358");
    map.put("63", "필리핀 +63");
    map.put("36", "헝가리 +36");
    map.put("61", "호주 +61");
    map.put("852", "홍콩 +852");
    
    request.setAttribute("Nations", map);
    return "address.jsp";
  }
  
  @Override
  public String runData(HttpServletRequest request, HttpServletResponse response) throws Exception {
    InfoModel model = InfoModel.instance();
    HttpSession session = request.getSession();
    String uid = (String)session.getAttribute("uid");
    InfoAddressBean addressBean = new InfoAddressBean();
    addressBean.setUid(Integer.parseInt(uid));
    addressBean.setNation(request.getParameter("nation"));
    addressBean.setPhone(request.getParameter("phone"));
    addressBean.setAddr(request.getParameter("addr"));
    addressBean.setDetail(request.getParameter("detail"));
    ValidationUtil util = new ValidationUtil();
    boolean isUpdatable = util.isUpdatable(addressBean);
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<script>");
    if (uid == null || !isUpdatable) {
      out.println("alert('입력된 정보가 양식에 맞지 않아 반려되었습니다.');");
    } else {
      int rowCount = model.upAddressInfo(addressBean);
      if (rowCount > 0) {
        out.println("alert('회원 정보가 수정되었습니다.');");
      } else {
        out.println("alert('회원 정보 수정에 실패하였습니다.');");
      }
    }
    out.println("history.back();");
    out.println("</script>");
    out.close();
    return null;
  }
}