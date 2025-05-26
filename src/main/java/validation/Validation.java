package validation;

import java.util.ArrayList;
import java.util.List;

public class Validation {
	//エラーメッセージを保持するString型のerrorMsgListという名前のリスト
	private List<String> errorMsgList;
	
	
	public Validation() {
		//コンストラクタ。newしたときに9行目のリストを初期化
		this.errorMsgList = new ArrayList<>();
	}
	
	//エラーメッセージが一つでも存在するかどうかをチェックするメソッド
	public boolean hasErrorMsg() {
		if(errorMsgList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	//指定されたテキストがnull（何も入っていない状態）
	//または空文字（スペースで空白の文字を意図的に入力している状態）かどうかをチェック
	//nullか空文字ならエラーメッセージを追加
	
	public void isBlank(String textName, String text) {
		if(text == null || text.isEmpty()) {
			this.errorMsgList.add(textName + "が入力されていません");
		}
	}
	
	//テキストの長さが最小値以上かどうかをチェック。超えている場合はエラーメッセージを追加
	public void length(String textName, String text, int min, int max) {
		if(text == null || text.length() < min || text.length() > max) {
			this.errorMsgList.add(textName + "は、" + min + "文字以上、" + max + "文字以内で入力してください");
		}
	}
	
	//テキストの長さが最大値以上かどうかをチェック。超えている場合はエラーメッセージを追加
	public void length(String textName, String text, int max) {
		if(text == null || text.length() > max) {
			this.errorMsgList.add(textName + "は、" + max + "文字以上内で入力してください");
		}
	}
	//エラーメッセージをリストに追加
	public void addErrorMsg(String msg) {
		errorMsgList.add(msg);
	}
	
	
	//保持しているエラーメッセージのリストを返す
	public List<String> getErrorMsgList(){
		return errorMsgList;
	}
	

}
