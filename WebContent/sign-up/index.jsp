<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.time.LocalDate"%>
<jsp:useBean id="conn" class="com.inseoul.dao.ConnectDB" />
<c:set var="hotplace" value="${conn.getHotplace()}" />
<c:set var="posters" value="${conn.getYearlyFestivalPosters()}" />
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<!-- 폰트 스타일시트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<!-- 아이콘 폰트 스타일시트 -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 스타일시트 -->
<link rel="stylesheet" type="text/css" href="../css/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="../css/common.css">
<link rel="stylesheet" type="text/css" href="../css/sign-up.css">
<!-- 자바스크립트 -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="../js/jquery.bxslider.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/sign-up.js"></script>
</head>
<body>
	<c:if test="${uid != null}">
		<c:redirect url="../" />
	</c:if>
	<c:if test="${uid == null}">
		<jsp:include page="../header.jsp" />
		<div class="top-title">
			<img src="../images/top-bg-001.png" />
			<div class="titleBox">회원가입</div>
		</div>
		<section>
			<form action="sign-up-result.jsp" name="sign-up" onsubmit="return signUp()" method="post" enctype="multipart/form-data">
				<article>
					<div class="input-fields">
						<ul class="flex-list">
							<li>프로필사진</li>
							<li>
								<input type="file" name="fil-photo" id="photo" alt="프로필사진" accept="image/png" onchange="return photoCheck()" required="required">
								<br />
								<span><font size="2">· 파일 형식 : image/png</font></span>
								<br />
								<span class="file-size"><font size="2">· 파일 크기 : 0MB / 5MB</font></span>
								<img class="float-right-block" id="preview" src="../images/man 1.png" width="100px" height="120px">
							</li>
						</ul>
						<div id="fileAlert"></div>
						<ul class="flex-list">
							<li>이메일</li>
							<li><input type="text" class="quarter-size" name="txt-email"
								id="email" maxlength="100" size="10" alt="이메일" required="required"> @ <input
								type="text" class="quarter-size" name="txt-domain" id="domain"
								maxlength="100" size="10" alt="도메인" required="required"> <select
								name="sel-domain" id="sel-domain" size="1">
									<option value="">직접 입력</option>
									<option value="gmail.com">gmail.com</option>
									<option value="naver.com">naver.com</option>
									<option value="kakao.com">kakao.com</option>
							</select>
								<button type="button" class="quarter-size" id="bt-email-check">중복확인</button></li>
						</ul>
						<div id="emailAlert"></div>
						<ul class="flex-list">
							<li>비밀번호</li>
							<li><input type="password" class="full-size" name="pw-input" id="pw-input"
								maxlength="20" size="40" alt="비밀번호" onblur="alertPwInput()"
								placeholder="8~16자리" required="required"><span
						id="pw-visibility" class="material-symbols-outlined">
							visibility_off </span></li>
						</ul>
						<div id="pwAlert"></div>
						<ul class="flex-list">
							<li>비밀번호 확인</li>
							<li><input type="password" class="full-size" name="pw-chk" id="pw-chk"
								maxlength="20" size="40" alt="비밀번호 확인" onblur="alertPwChk()"
								placeholder="8~16자리" required="required"><span
						id="pw-chk-visibility" class="material-symbols-outlined">
							visibility_off </span></li>
						</ul>
						<div id="pwChkAlert"></div>
						<ul class="flex-list">
							<li>이름</li>
							<li><input type="text" class="full-size" name="txt-name"
								maxlength="40" size="40" alt="이름" onblur="alertName()" required="required"></li>
						</ul>
						<div id="nameAlert"></div>
						<ul class="flex-list">
							<li>별명</li>
							<li><input type="text" class="full-size" name="txt-nick"
								maxlength="40" size="40" alt="별명" onblur="alertNick()" required="required"></li>
						</ul>
						<div id="nickAlert"></div>
						<ul class="flex-list">
							<li>생년월일</li>
							<li><input type="number" class="one-third-size"
								name="num-year" maxlength="4" value="1900" min="${LocalDate.now().getYear() - 200}" max="${LocalDate.now().getYear() - 14}"
								alt="년도" required="required"> <select name="sel-month" id="sel-month"
								size="1" onchange="birthCheck()" required="required">
									<option>01</option>
									<option>02</option>
									<option>03</option>
									<option>04</option>
									<option>05</option>
									<option>06</option>
									<option>07</option>
									<option>08</option>
									<option>09</option>
									<option>10</option>
									<option>11</option>
									<option>12</option>
							</select> <input type="number" class="one-third-size" name="num-day"
								value="1" min="1" max="31" required="required"></li>
						</ul>
						<div id="birthAlert"><font size='2'>${LocalDate.now().getYear() - 14}년도 이전 출생자만 가입 가능합니다.</font></div>
						<ul class="flex-list">
							<li>연락처</li>
							<li><select class="half-size" name="nationNo" required="required">
									<option value="233">가나 +233</option>
									<option value="241">가봉 +241</option>
									<option value="592">가이아나 +592</option>
									<option value="220">감비아 +220</option>
									<option value="502">과테말라 +502</option>
									<option value="1671">괌 +1 671</option>
									<option value="1473">그레나다 +1 473</option>
									<option value="30">그리스 +30</option>
									<option value="224">기니 +224</option>
									<option value="245">기니비사우 +245</option>
									<option value="264">나미비아 +264</option>
									<option value="674">나우루 +674</option>
									<option value="234">나이지리아 +234</option>
									<option value="672">남극,오스트레일리아의외 +672</option>
									<option value="27">남아프리카공화국 +27</option>
									<option value="31">네덜란드 +31</option>
									<option value="599">네덜란드령보네르 +599</option>
									<option value="297">네덜란드령아루바 +297</option>
									<option value="977">네팔 +977</option>
									<option value="47">노르웨이 +47</option>
									<option value="64">뉴질랜드 +64</option>
									<option value="683">뉴질랜드령니우에 +683</option>
									<option value="690">뉴질랜드령토켈라우제도 +690</option>
									<option value="227">니제르 +227</option>
									<option value="505">니카라과 +505</option>
									<option value="82" selected="selected">대한민국 +82</option>
									<option value="45">덴마크 +45</option>
									<option value="299">덴마크령그린란드 +299</option>
									<option value="298">덴마크령페로제도 +298</option>
									<option value="1809">도미니카공화국 +1 809</option>
									<option value="1829">도미니카공화국 2 +1 829</option>
									<option value="1849">도미니카공화국 3 +1 849</option>
									<option value="49">독일 +49</option>
									<option value="670">동티모르 +670</option>
									<option value="856">라오스 +856</option>
									<option value="231">라이베리아 +231</option>
									<option value="371">라트비아 +371</option>
									<option value="7">러시아/카자흐스탄 +7</option>
									<option value="961">레바논 +961</option>
									<option value="266">레소토 +266</option>
									<option value="40">루마니아 +40</option>
									<option value="352">룩셈부르크 +352</option>
									<option value="250">르완다 +250</option>
									<option value="218">리비아 +218</option>
									<option value="370">리투아니아 +370</option>
									<option value="423">리히텐슈타인 +423</option>
									<option value="261">마다가스카르 +261</option>
									<option value="692">마셜제도공화국 +692</option>
									<option value="691">마이크로네시아연방 +691</option>
									<option value="853">마카오 +853</option>
									<option value="389">마케도니아공화국 +389</option>
									<option value="265">말라위 +265</option>
									<option value="60">말레이시아 +60</option>
									<option value="223">말리 +223</option>
									<option value="52">멕시코 +52</option>
									<option value="377">모나코 +377</option>
									<option value="212">모로코 +212</option>
									<option value="230">모리셔스 +230</option>
									<option value="222">모리타니아 +222</option>
									<option value="258">모잠비크 +258</option>
									<option value="382">몬테네그로 +382</option>
									<option value="373">몰도바 +373</option>
									<option value="960">몰디브 +960</option>
									<option value="356">몰타 +356</option>
									<option value="976">몽골 +976</option>
									<option value="1">미국/캐나다 +1</option>
									<option value="1670">미국령북마리아나제도 +1 670</option>
									<option value="95">미얀마 +95</option>
									<option value="678">바누아투 +678</option>
									<option value="973">바레인 +973</option>
									<option value="1246">바베이도스 +1 246</option>
									<option value="1242">바하마 +1 242</option>
									<option value="880">방글라데시 +880</option>
									<option value="229">베냉 +229</option>
									<option value="58">베네수엘라 +58</option>
									<option value="84">베트남 +84</option>
									<option value="32">벨기에 +32</option>
									<option value="375">벨라루스 +375</option>
									<option value="501">벨리즈 +501</option>
									<option value="387">보스니아헤르체고비나 +387</option>
									<option value="267">보츠와나 +267</option>
									<option value="591">볼리비아 +591</option>
									<option value="257">부룬디 +257</option>
									<option value="226">부르키나파소 +226</option>
									<option value="975">부탄 +975</option>
									<option value="359">불가리아 +359</option>
									<option value="55">브라질 +55</option>
									<option value="673">브루나이 +673</option>
									<option value="685">사모아 +685</option>
									<option value="966">사우디아라비아 +966</option>
									<option value="378">산마리노 +378</option>
									<option value="239">상투메프린시페 +239</option>
									<option value="221">세네갈 +221</option>
									<option value="381">세르비아 +381</option>
									<option value="248">세이셀 +248</option>
									<option value="1784">세인트빈센트그레나딘 +1 784</option>
									<option value="252">소말리아 +252</option>
									<option value="677">솔로몬제도 +677</option>
									<option value="249">수단 +249</option>
									<option value="597">수리남 +597</option>
									<option value="94">스리랑카 +94</option>
									<option value="268">스와질랜드 +268</option>
									<option value="46">스웨덴 +46</option>
									<option value="41">스위스 +41</option>
									<option value="34">스페인 +34</option>
									<option value="421">슬로바키아 +421</option>
									<option value="386">슬로베니아 +386</option>
									<option value="963">시리아 +963</option>
									<option value="232">시에라리온 +232</option>
									<option value="65">싱가포르 +65</option>
									<option value="971">아랍에미리트 +971</option>
									<option value="374">아르메니아 +374</option>
									<option value="54">아르헨티나 +54</option>
									<option value="1684">아메리칸사모아 +1 684</option>
									<option value="354">아이슬란드 +354</option>
									<option value="509">아이티 +509</option>
									<option value="353">아일랜드 +353</option>
									<option value="994">아제르바이잔 +994</option>
									<option value="93">아프가니스탄 +93</option>
									<option value="376">안도라 +376</option>
									<option value="355">알바니아 +355</option>
									<option value="213">알제리 +213</option>
									<option value="244">앙골라 +244</option>
									<option value="251">에디오피아 +251</option>
									<option value="291">에리트레아 +291</option>
									<option value="372">에스토니아 +372</option>
									<option value="593">에콰도르 +593</option>
									<option value="503">엘살바도르 +503</option>
									<option value="44">영국 +44</option>
									<option value="290">영국령세인트헬레나 +290</option>
									<option value="246">영국령인도양지역 +246</option>
									<option value="350">영국령지브롤터 +350</option>
									<option value="500">영국령포클랜드제도 +500</option>
									<option value="967">예멘 +967</option>
									<option value="968">오만 +968</option>
									<option value="43">오스트리아 +43</option>
									<option value="504">온두라스 +504</option>
									<option value="962">요르단 +962</option>
									<option value="256">우간다 +256</option>
									<option value="598">우루과이 +598</option>
									<option value="998">우즈베키스탄 +998</option>
									<option value="380">우크라이나 +380</option>
									<option value="964">이라크 +964</option>
									<option value="98">이란 +98</option>
									<option value="972">이스라엘 +972</option>
									<option value="20">이집트 +20</option>
									<option value="39">이탈리아 +39</option>
									<option value="91">인도 +91</option>
									<option value="62">인도네시아 +62</option>
									<option value="81">일본 +81</option>
									<option value="1876">자메이카 +1 876</option>
									<option value="260">잠비아 +260</option>
									<option value="240">적도기니 +240</option>
									<option value="995">조지아 +995</option>
									<option value="86">중국 +86</option>
									<option value="236">중앙아프리카공화국 +236</option>
									<option value="253">지부티 +253</option>
									<option value="263">짐바브웨 +263</option>
									<option value="235">차드 +235</option>
									<option value="420">체코 +420</option>
									<option value="56">칠레 +56</option>
									<option value="237">카메룬 +237</option>
									<option value="238">카보베르데 +238</option>
									<option value="974">카타르 +974</option>
									<option value="855">캄보디아왕국 +855</option>
									<option value="254">케냐 +254</option>
									<option value="269">코모로,마요트 +269</option>
									<option value="506">코스타리카 +506</option>
									<option value="225">코트디부아르 +225</option>
									<option value="57">콜롬비아 +57</option>
									<option value="242">콩고 +242</option>
									<option value="243">콩고민주공화국 +243</option>
									<option value="53">쿠바 +53</option>
									<option value="965">쿠웨이트 +965</option>
									<option value="682">쿡제도 +682</option>
									<option value="385">크로아티아 +385</option>
									<option value="996">키르기스스탄 +996</option>
									<option value="686">키리바시 +686</option>
									<option value="357">키프로스 +357</option>
									<option value="886">타이완 +886</option>
									<option value="992">타지키스탄 +992</option>
									<option value="255">탄자니아 +255</option>
									<option value="66">태국 +66</option>
									<option value="90">터키 +90</option>
									<option value="228">토고 +228</option>
									<option value="676">통가 +676</option>
									<option value="993">투르크메니스탄 +993</option>
									<option value="216">튀니지 +216</option>
									<option value="1868">트리니다드토바고 +1 868</option>
									<option value="507">파나마 +507</option>
									<option value="595">파라과이 +595</option>
									<option value="92">파키스탄 +92</option>
									<option value="675">파푸아뉴기니 +675</option>
									<option value="680">팔라우 +680</option>
									<option value="970">팔레스타인 +970</option>
									<option value="51">페루 +51</option>
									<option value="351">포르투갈 +351</option>
									<option value="48">폴란드 +48</option>
									<option value="1787">푸에르토리코 +1 787</option>
									<option value="33">프랑스 +33</option>
									<option value="590">프랑스령과들루프 +590</option>
									<option value="594">프랑스령기아나 +594</option>
									<option value="687">프랑스령뉴칼레도니아 +687</option>
									<option value="262">프랑스령레위니옹 +262</option>
									<option value="596">프랑스령마르티니크 +596</option>
									<option value="508">프랑스령생피에르미클롱 +508</option>
									<option value="681">프랑스령월리스푸투나제 +681</option>
									<option value="689">프랑스령폴리네시아 +689</option>
									<option value="679">피지 +679</option>
									<option value="358">핀란드 +358</option>
									<option value="63">필리핀 +63</option>
									<option value="36">헝가리 +36</option>
									<option value="61">호주 +61</option>
									<option value="852">홍콩 +852</option>
							</select> <input type="text" class="half-size" name="num-phone"
								maxlength="16" alt="연락처" onkeypress="return numberCheck()"
								placeholder="'-' 없이 11~16자리" onblur="alertPhone()" required="required"></li>
						</ul>
						<div id="phoneAlert"></div>
						<ul class="flex-list">
							<li>기본주소</li>
							<li><input type="text" class="three-quarter-size"
								id="address" name="txt-addr" maxlength="100" size="30"
								alt="기본주소" onblur="alertAddr()" readonly required="required">
								<button type="button" class="quarter-size" name="bt-search"
									onclick="execDaumPostcode()" value="주소검색">주소검색</button></li>
						</ul>
						<div id="addrAlert"></div>
						<ul class="flex-list">
							<li>상세주소</li>
							<li><input type="text" class="full-size" id="detailAddress"
								name="txt-detail" maxlength="100" size="40" alt="상세주소"></li>
						</ul>
						<div id="detailAlert"></div>
						<ul class="flex-list">
							<li>본인확인질문</li>
							<li><input type="text" class="full-size" name="txt-question"
								maxlength="40" size="40" alt="본인확인질문" onblur="alertQuestion()" required="required"></li>
						</ul>
						<div id="questionAlert"></div>
						<ul class="flex-list">
							<li>본인확인답변</li>
							<li><input type="text" class="full-size" name="txt-answer"
								maxlength="40" size="40" alt="본인확인답변" onblur="alertAnswer()" required="required"></li>
						</ul>
						<div id="answerAlert"></div>
						<div class="checkbox-item">
							<input type="checkbox" name="chk-pi" value="pi" alt="개인정보수집 동의" required="required">
							<span>개인정보수집에 동의합니다</span><a href="" class="float-right-block">
								보기 </a>
						</div>
						<div class="checkbox-item">
							<input type="checkbox" name="chk-tos" value="tos" alt="이용약관 동의" required="required">
							<span>이용약관에 동의합니다</span><a href="" class="float-right-block">
								보기 </a>
						</div>
					</div>
					<div>
						<p>IN서울에 가입하면...</p>
						<div class="bxslider">
							<div>
								<img src="../images/sign-up-banner-001.png"
									title="서울 방방곡곡의 여행지 정보를 한눈에!" />
							</div>
							<div>
								<img src="../images/sign-up-banner-002.png"
									title="나만의 여행 경로를 작성하고 공유!" />
							</div>
							<div>
								<img src="../images/sign-up-banner-003.png"
									title="마음이 맞는 사람들과 함께 여행!" />
							</div>
							<div>
								<img src="../images/sign-up-banner-004.png"
									title="내 프로필 페이지를 꾸미고 여행의 즐거웠던 추억들을 기록!" />
							</div>
						</div>
						<p>핫 플레이스</p>
						<dl>
							<dt class="hotplace">
								<a href="../travels/locationDetail.tv?lid=${hotplace.getLid()}" >
									<img src="../images/${hotplace.getImg()}">
								</a>
							</dt>
							<dd>${hotplace.getName()}</dd>
						</dl>

						<p>올해의 공연/축제</p>
						<ul class="poster">
							<li>
								<a href="http://localhost:8082/inseoul/festival/festivalDetail.val?fid=${posters.get(0).getFid()}" >
									<img src="../festival-img/${posters.get(0).getImg()}" alt="">
								</a>
							</li>
							<li>
								<a href="http://localhost:8082/inseoul/festival/festivalDetail.val?fid=${posters.get(1).getFid()}" >
									<img src="../festival-img/${posters.get(1).getImg()}" alt="">
								</a>
							</li>
							<li>
								<a href="http://localhost:8082/inseoul/festival/festivalDetail.val?fid=${posters.get(2).getFid()}" >
									<img src="../festival-img/${posters.get(2).getImg()}" alt="">
								</a>
							</li>
							<li>
								<a href="http://localhost:8082/inseoul/festival/festivalDetail.val?fid=${posters.get(3).getFid()}" >
									<img src="../festival-img/${posters.get(3).getImg()}" alt="">
								</a>
							</li>
						</ul>
					</div>
				</article>
				<footer>
					<div>
						<input type="submit" name="sub-sign-up" value="가입신청">
					</div>
				</footer>
			</form>
		</section>
		<jsp:include page="../footer.jsp" />
	</c:if>
</body>
</html>