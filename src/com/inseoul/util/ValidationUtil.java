package com.inseoul.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.*;
import java.sql.Connection;
import static com.inseoul.db.JdbcUtil.*;
import com.inseoul.dao.*;
import com.inseoul.vo.*;

public class ValidationUtil {
  private HashMap<String, String> regexMap;
  /* ----------------------------------------------------------------------------------------------------
   * 객체 생성시 초기화 합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public ValidationUtil() {
    regexMap = new HashMap<>();
    regexMap.put("upperCase", "A-Z");
    regexMap.put("lowerCase", "a-z");
    regexMap.put("number", "0-9");
    regexMap.put("korean", "가-힣ㄱ-ㅎㅏ-ㅣ");
    regexMap.put("special", "`~!@#$%\\^&*\\(\\)-_=+\\|\\[\\]\\{\\};:'\",.<>/?");
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 UserBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isInsertable(UserBean user) {
    boolean isInsertable = true;
    isInsertable &= isFileName(user.getPhoto(), true, "png", 255);
    isInsertable &= isEmail(user.getEmail(), true, 5, 20, 100);
    isInsertable &= isNotDuplicateEmail(user.getEmail());
    isInsertable &= isPassword(user.getPw(), true, 8, 16, true, "upperCase", "lowerCase", "number", "special");
    isInsertable &= isStringLength(user.getName(), true, 1, 45, true);
    isInsertable &= isStringLength(user.getNick(), true, 1, 45, true);
    isInsertable &= isDateString(user.getBirth(), "yyyy-MM-dd", true);
    isInsertable &= isNumericString(user.getNation(), true, 1, 4);
    isInsertable &= isNumericString(user.getPhone(), true, 11, 16);
    isInsertable &= isStringLength(user.getAddr(), true, 1, 100, true);
    isInsertable &= isStringLength(user.getDetail(), false, 0, 100, false);
    isInsertable &= isStringLength(user.getQuestion(), true, 1, 45, true);
    isInsertable &= isStringLength(user.getAnswer(), true, 1, 45, true);
    return isInsertable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 AlbumBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isInsertable(AlbumBean album) {
    boolean isInsertable = true;
    isInsertable &= isStringLength(album.getTitle(), true, 1, 100, true);
    isInsertable &= isAlbumFileName(album.getImg01(), true, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg02(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg03(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg04(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg05(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg06(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg07(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg08(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg09(), false, "png", 255);
    isInsertable &= isAlbumFileName(album.getImg10(), false, "png", 255);
    isInsertable &= isNotEmpty(album.getContents());
    return isInsertable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 BucketBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isInsertable(BucketBean bucket) {
    boolean isInsertable = true;
    isInsertable &= isStringLength(bucket.getItem(), true, 1, 100, true);
    return isInsertable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 ReplyBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isInsertable(ReplyBean reply) {
    boolean isInsertable = true;
    isInsertable &= isNotEmpty(reply.getContents());
    return isInsertable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 UserBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(InfoGeneralBean generalBean) {
    boolean isUpdatable = true;
    isUpdatable &= isFileName(generalBean.getPhoto(), false, "png", 255);
    isUpdatable &= isStringLength(generalBean.getNick(), true, 1, 45, true);
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 UserBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(InfoAddressBean addressBean) {
    boolean isUpdatable = true;
    isUpdatable &= isNumericString(addressBean.getNation(), true, 1, 4);
    isUpdatable &= isNumericString(addressBean.getPhone(), true, 11, 16);
    isUpdatable &= isStringLength(addressBean.getAddr(), true, 1, 100, true);
    isUpdatable &= isStringLength(addressBean.getDetail(), false, 0, 100, false);
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 UserBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(InfoPasswordBean passwordBean) {
    boolean isUpdatable = true;
    isUpdatable &= isPasswordCorrect(passwordBean.getUid(), passwordBean.getPwOld());
    isUpdatable &= isPassword(passwordBean.getPwNew(), true, 8, 16, true, "upperCase", "lowerCase", "number", "special");
    isUpdatable &= passwordBean.getPwNew().equals(passwordBean.getPwChk());
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 UserBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(InfoQuestionBean questionBean) {
    boolean isUpdatable = true;
    isUpdatable &= isStringLength(questionBean.getQuestion(), true, 1, 45, true);
    isUpdatable &= isStringLength(questionBean.getAnswer(), true, 1, 45, true);
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 AlbumBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(AlbumBean album) {
    boolean isUpdatable = true;
    isUpdatable &= isLocationId(album.getLid());
    isUpdatable &= isStringLength(album.getTitle(), true, 1, 100, true);
    isUpdatable &= isAlbumFileName(album.getImg01(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg02(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg03(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg04(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg05(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg06(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg07(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg08(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg09(), false, "png", 255);
    isUpdatable &= isAlbumFileName(album.getImg10(), false, "png", 255);
    isUpdatable &= isNotEmpty(album.getContents());
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 BucketBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(BucketBean bucket) {
    boolean isUpdatable = true;
    isUpdatable &= isStringLength(bucket.getItem(), true, 1, 100, true);
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * DB에 ReplyBean 객체로부터 값을 받아도 될지 여부를 판단합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isUpdatable(ReplyBean reply) {
    boolean isUpdatable = true;
    isUpdatable &= isNotEmpty(reply.getContents());
    return isUpdatable;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 문자열이 비어있거나 공백인지 확인합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isNotEmpty(String str) {
    if (str != null && str.trim().length() > 0) {
      return true;
    }
    return false;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 문자열이 숫자문자열인지 확인합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isNumericString(String str, boolean notNull) {
    if (str == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    String regex = "^[0-9]+$";
    if (!Pattern.matches(regex, str)) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 문자열이 숫자문자열인지 그리고 길이를가 알맞은지 확인 합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isNumericString(String str, boolean notNull, int minLength, int maxLength) {
    if (str == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    String regex = String.format("^[0-9]{%d,%d}$", minLength, maxLength);
    if (!Pattern.matches(regex, str)) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 문자열 길이를 확인하고 그 결과를 반환합니다.
   * ----------------------------------------------------------------------------------------------------
   * 최소 길이 측정시 공백을 제거하는 경우 매개변수 trim을 true로 전달합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isStringLength(String str, boolean notNull, int minStringLength, int maxStringLength, boolean trim) {
    int minLength = 0; 
    if (str == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    if (trim) {
      minLength = str.trim().length();
    } else {
      minLength = str.length();
    }
    if (minLength < minStringLength) {
      return false;
    }
    if (str.length() > maxStringLength) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 정수의 크기를 확인하고 그 결과를 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isNumberSize(int num, int min, int max) {
    if (num < min) {
      return false;
    }
    if (num > max) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 파일명을 확인하고 그 결과를 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isFileName(String fileName, boolean notNull, String extension, int maxFileNameLength) {
    String regex = "";
    if (fileName == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    regex = String.format("^([\\S]+(\\.(?i)(%s))$)", extension);
    if (!Pattern.matches(regex, fileName)) {
      return false;
    }
    if (fileName.length() > maxFileNameLength) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 앨범용 파일명을 확인하고 그 결과를 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isAlbumFileName(String fileName, boolean notNull, String extension, int maxFileNameLength) {
    String regex = "";
    if (fileName == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    if (fileName.equals("remove")) {
      return false;
    }
    regex = String.format("^([\\S]+(\\.(?i)(%s))$)", extension);
    if (!Pattern.matches(regex, fileName)) {
      return false;
    }
    if (fileName.length() > maxFileNameLength) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 이메일 주소를 확인하고 그 결과를 반환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isEmail(String emailAddress, boolean notNull, int minIdLength, int maxIdLength, int maxAddressLength) {
    String regex = "";
    if (emailAddress == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    regex = String.format("^[_a-z0-9-]{%d,%d}@(?:\\w+\\.)+\\w+$", minIdLength, maxIdLength);
    if (!Pattern.matches(regex, emailAddress)) {
      return false;
    }
    if (emailAddress.length() > maxAddressLength) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 비밀번호를 확인하고 그 결과를 반환합니다.
   * ----------------------------------------------------------------------------------------------------
   * 가변매개변수를 통해 전달받은 키값을 통해 유동적으로 정규식을 작성합니다.
   *    별개의 DTO를 사용하여 매개변수를 대체 가능하다. 
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isPassword(String password, boolean notNull, int minPasswordLength, int maxPasswordLength, boolean useAll, String ...keys) {
    String lookaround = "", range = "", regex = "";
    if (password == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    if (useAll) {
      for (String key : keys) {
        lookaround += String.format("(?=.*[%s])", regexMap.get(key));
      }
      for (String key : keys) {
        range += regexMap.get(key);
      }
      regex = String.format("^%s[%s]{%d,%d}$", lookaround, range, minPasswordLength, maxPasswordLength);
    } else {
      for (String key : keys) {
        range += regexMap.get(key);
      }
      regex = String.format("^[%s]{%d,%d}$", range, minPasswordLength, maxPasswordLength);
    }
    if (!Pattern.matches(regex, password)) {
      return false;
    }
    return true;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 해당 문자열이 패턴문자열에 부합하는지 확인합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isDateString(String dateString, String inputPattern, boolean notNull) {
    if (dateString == null) {
      if (notNull) {
        return false;
      } else {
        return true;
      }
    }
    try {
      SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
      inputFormat.parse(dateString);
      return true;
    } catch (Exception e) {
      System.out.println(e);
    }
    return false;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 전달받은 날짜 문자열의 양식을 변환합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public String convertDateFormat(String dateString, String inputPattern, String outputPattern) {
    String result = null;
    try {
      SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
      SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
      Date formatDate = inputFormat.parse(dateString);
      result = outputFormat.format(formatDate);
    } catch (ParseException e) {
      System.out.println(e);
    }
    return result;
  }
  /* ----------------------------------------------------------------------------------------------------
   * ■ 여기서부터는 DB와의 통신을 하여 유효성 검사를 실시합니다.
   * ----------------------------------------------------------------------------------------------------
   * 이메일 중복 여부를 확인합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isNotDuplicateEmail(String email) {
    if (email == null) {
      return false;
    }
    ConnectDB conn = new ConnectDB();
    boolean isNotDuplicateEmail = !conn.checkEmail(email);
    return isNotDuplicateEmail;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 로그인된 사용자의 비밀번호를 확인합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isPasswordCorrect(int uid, String pw) {
    if (pw == null) {
      return false;
    }
    ConnectDB conn = new ConnectDB();
    boolean isCorrect = !conn.checkPassword(uid, pw);
    return isCorrect;
  }
  /* ----------------------------------------------------------------------------------------------------
   * 관광지 ID가 DB에서 존재하는지 확인합니다.
   * ---------------------------------------------------------------------------------------------------- */
  public boolean isLocationId(int locationId) {
    Connection con = getConnection();
    LocationDAO locationDAO = LocationDAO.getInstance();
    locationDAO.setConnection(con);
    boolean isLocationId = locationDAO.isLocationID(locationId);
    close(con);
    return isLocationId;
  }
}
