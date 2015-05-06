/**
 * アーカイブ作成のメインクラス（テスト実行）
 */
package main;

import java.util.ArrayList;
import java.util.List;

import Utils.FileCopyUtil;
import Utils.ZipCompressUtil;

/**
 * @author wagahai
 *
 */
public class TestZipGenerator {

	/**
	 * @param args 未使用
	 */
	public static void main(String[] args) {


		String filePath = "F:\\develop\\workspaces\\archive\\archiveTestProject\\afterPressDirectory\\wagahai.zip";
		List<String> fileList = new ArrayList<String>();
		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai1.txt");
		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai2.txt");
		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai3.txt");
		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai4.txt");
		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai5.txt");
		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai6.txt");
//		fileList.add("F:\\develop\\workspaces\\archive\\archiveTestProject\\textDirectory\\wagahai7.txt");



		// アーカイブ実行
		System.out.println("アーカイブ処理を開始します");
		ZipCompressUtil zipCompressUtils = new ZipCompressUtil();
		zipCompressUtils.compressFileList(filePath, fileList);
		// アーカイブ終了
		System.out.println("アーカイブ処理終了です");


		// コピー開始
		FileCopyUtil copy2File = new FileCopyUtil();
//		copy2File.copy(zipCompressUtils.getArchivePath(),"F:\\develop\\workspaces\\archive\\archiveTestProject\\testCopy\\wagahai.zip");
		copy2File.copy(zipCompressUtils.getArchivePath(),"C:\\wagahai.zip");
	}

}
