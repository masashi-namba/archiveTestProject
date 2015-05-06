/**
 *
 */
package Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wagahai
 *
 */
public class FileCopyUtil {


	/**
	 * @param from  コピー元ファイル名（フルパス）
	 * @param afterList ファイルパスリスト
	 * @return
	 */
	public boolean copy(String from, List<String> afterList ){

		List<Boolean> copyResultList = new ArrayList<Boolean>();

		for(String afterFileName : afterList){
			boolean copyResult = copy(from,afterFileName);
			copyResultList.add(copyResult);
		}

		for(boolean result : copyResultList){
			System.out.println("result : "+ result);
		}
		return false;
	}

	/**
	 * @param from コピー元ファイル名（フルパス）
	 * @param after コピー先ファイル名（フルパス）
	 * @return boolean true:コピー成功 false:コピー失敗
	 */
	public boolean copy(String from,String after){
		File fromFile = new File(from);
        File afterFile = new File(after);

        try {
        	afterFile.delete();
            if (fromFile.renameTo(afterFile)) {
                System.out.println("移動成功");
            } else {
                System.err.println("移動失敗");
            }

        } catch (SecurityException e) {
            System.err.println("例外が発生しました。");
            System.err.println(e);

            return false;

        } catch (NullPointerException e) {
            System.err.println("例外が発生しました。");
            System.err.println(e);

            return false;

        } finally {

        }

		return true;
	}
}
