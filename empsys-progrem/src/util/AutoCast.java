package util;

public class AutoCast {

	/**
	 * Object型から総称型（List<String>など）へのキャストなどは
	 * 「型の安全性に問題がある（キャストに失敗する可能性がある）」という理由でコンパイラが
	 * 未検査キャストの警告を出してくるが、これを回避する。
	 * @param src キャスト対象のオブジェクト
	 * @return キャスト後のオブジェクト
	 */
	@SuppressWarnings("unchecked")
	public static <T> T automaticCast(Object src) {
	    T castedObject = (T) src;
	    return castedObject;

	}

}
