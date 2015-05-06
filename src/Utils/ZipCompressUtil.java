/**
 * アーカイブ処理を行うクラス。
 */
package Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * アーカイブ処理を行うクラス。
 * @author wagahai
 *
 */
public class ZipCompressUtil {

	/**
	 * 指定されたディレクトリ内のファイルを ZIP アーカイブし、指定されたパスに作成します。
	 *
	 * @param filePath
	 *            圧縮後の出力ファイル名をフルパスで指定 ( 例: C:/sample.zip )
	 * @param directory
	 *            圧縮するディレクトリ ( 例; C:/sample )
	 * @return 処理結果 true:圧縮成功 false:圧縮失敗
	 */
	public boolean compressDirectory(String filePath, String directory) {
		File baseFile = new File(filePath);
		File file = new File(directory);
		ZipOutputStream zipOutputStream = null;
		try {
			// ZIPファイル出力オブジェクト作成
			zipOutputStream = new ZipOutputStream(new FileOutputStream(baseFile));
			archive(zipOutputStream, baseFile, file, baseFile.getName());
		} catch (Exception e) {
			// ZIP圧縮失敗
			return false;
		} finally {
			// ZIPエントリクローズ
			if (zipOutputStream != null) {
				try {
					zipOutputStream.closeEntry();
					zipOutputStream.flush();
					zipOutputStream.close();
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		return true;
	}


	/**
	 * 指定された ArrayList のファイルを ZIP アーカイブし、指定されたパスに作成します。
	 * デフォルト文字コードは Shift_JIS ですので、日本語ファイル名も対応できます。
	 * @param filePath  圧縮後のファイル名をフルパスで指定 ( 例: C:/sample.zip )
	 * @param fileList 圧縮するファイルリスト  ( 例; {C:/sample1.txt, C:/sample2.txt} )
	 * @return 処理結果 true:圧縮成功 false:圧縮失敗
	 */
	public boolean compressFileList(String filePath, List<String> fileList) {

		ZipOutputStream outZip = null;

		File baseFile = new File(filePath);

		try {

			// ZIPファイル出力オブジェクト作成

			outZip = new ZipOutputStream(new FileOutputStream(baseFile));

			// 圧縮ファイルリストのファイルを連続圧縮

			for (int i = 0; i < fileList.size(); i++) {

				// ファイルオブジェクト作成

				File file = new File((String) fileList.get(i));

				archive(outZip, baseFile, file, file.getName());

			}

		} catch (Exception e) {

			// ZIP圧縮失敗

			return false;

		} finally {

			// ZIPエントリクローズ

			if (outZip != null) {

				try {
					outZip.closeEntry();
					outZip.flush();
					outZip.close();
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		return true;
	}



	/**
	 * @param outZip
	 *            ZipOutputStream
	 * @param baseFile
	 *            File 保存先ファイル
	 * @param targetFile
	 *            File 圧縮したいファイル
	 * @param entryName
	 *            保存ファイル名
	 * @param enc
	 *            文字コード
	 * @return
	 */
	private boolean archive(ZipOutputStream outZip, File baseFile, File targetFile, String entryName) {
		// 圧縮レベル設定
		outZip.setLevel(5);
		try {
			// ZIPエントリ作成
			outZip.putNextEntry(new ZipEntry(entryName));
			// 圧縮ファイル読み込みストリーム取得
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(targetFile));
			// 圧縮ファイルをZIPファイルに出力
			int readSize = 0;
			byte buffer[] = new byte[1024]; // 読み込みバッファ
			while ((readSize = in.read(buffer, 0, buffer.length)) != -1) {
				outZip.write(buffer, 0, readSize);
			}
			// クローズ処理
			in.close();
			// ZIPエントリクローズ
			outZip.closeEntry();
		} catch (Exception e) {
			// ZIP圧縮失敗
			return false;
		}
		return true;
	}

	public String getArchivePath(){
		return "F:\\develop\\workspaces\\archive\\archiveTestProject\\afterPressDirectory\\wagahai.zip";
	}

}
