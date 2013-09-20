package com.g3net.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipUtils {
	private static final Logger log = Logger.getLogger(ZipUtils.class);

	public static byte[] gzip(byte[] bs) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream(1000);
		GZIPOutputStream gzout = null;
		try {
			gzout = new GZIPOutputStream(bout);
			gzout.write(bs);
			gzout.flush();
		} catch (Exception e) {
			log.error("", e);
			return null;
		} finally {
			if (gzout != null) {
				try {
					gzout.close();
				} catch (Exception ex) {
				}
			}
		}

		return bout.toByteArray();

	}

	public static byte[] ungzip(byte[] bs) {
		GZIPInputStream gzin = null;
		try {
			ByteArrayInputStream bin = new ByteArrayInputStream(bs);
			gzin = new GZIPInputStream(bin);
			return FileUtils.readBytes(gzin);
		} catch (Exception e) {
			log.error("", e);
			return null;
		} finally {
		}

	}

	public static byte[] zip(byte[] bs) {

		ByteArrayOutputStream o = null;
		try {
			o = new ByteArrayOutputStream();
			Deflater compresser = new Deflater();
			compresser.setInput(bs);
			compresser.finish();
			byte[] output = new byte[1024];
			while (!compresser.finished()) {
				int got = compresser.deflate(output);
				o.write(output, 0, got);
			}
			o.flush();
			return o.toByteArray();
		} catch (Exception ex) {
			log.error("", ex);
			return null;
		} finally {
			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
		}

	}

	public static byte[] unzip(byte[] bs) {

		ByteArrayOutputStream o = null;
		try {
			o = new ByteArrayOutputStream();
			Inflater decompresser = new Inflater();
			decompresser.setInput(bs);
			byte[] result = new byte[1024];
			while (!decompresser.finished()){
				int resultLength = decompresser.inflate(result);
				o.write(result, 0, resultLength);
			}
			decompresser.end();
			o.flush();
			return o.toByteArray();
		} catch (Exception ex) {
			log.error("", ex);
			return null;
		} finally {
			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
		}

	}

	public static void zip(String[] fileNames, String outZipFileName)
			throws Exception {
		ZipOutputStream zout = null;
		try {
			FileOutputStream fout = new FileOutputStream(outZipFileName);

			zout = new ZipOutputStream(fout);

			BufferedOutputStream out = new BufferedOutputStream(zout);
			for (int i = 0; i < fileNames.length; i++) {
				ZipEntry ze = new ZipEntry(FileUtils.getFileName(fileNames[i]));
				BufferedInputStream in = null;
				try {
					in = new BufferedInputStream(new FileInputStream(
							fileNames[i]));
					zout.putNextEntry(ze);
					int readLen = 1024;
					byte[] bs = new byte[readLen];

					while (true) {
						int result = in.read(bs);
						if (result == -1)
							break;
						out.write(bs, 0, result);

					}
				} finally {
					in.close();
					out.flush();
					zout.closeEntry();
				}
			}
		} catch (Exception ex) {
			log.error(ex);
			throw ex;

		} finally {
			if (zout != null)
				zout.close();
		}
	}

	/**
	 * 解压zip文件到指定的目录 File Unzip
	 * 
	 * @param sToPath
	 *            Directory path to be unzipted to
	 * @param sZipFile
	 *           zip File Name to be ziped
	 */
	public static void unZip(String sToPath, String sZipFile)
			throws Exception {

		if (null == sToPath || ("").equals(sToPath.trim())) {
			File objZipFile = new File(sZipFile);
			sToPath = objZipFile.getParent();
			// log.info(sToPath);
		}
		ZipFile zfile = new ZipFile(sZipFile);
		Enumeration zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {

			ze = (ZipEntry) zList.nextElement();

			if (ze.isDirectory()) {
				// log.info(ze.getName());
				String dir = FileUtils.getDirectoryPath(sToPath + "/"
						+ ze.getName());
				// log.info(dir);
				FileUtils.makeDirectory(dir);
				// log.info("1111");

			} else {
				// log.info("2222");
				// log.info(ze.getName());
				String filePath = FileUtils.getDirectoryPath(sToPath + "/"
						+ ze.getName());
				// zfile.getInputStream(ze);
				// FileUtils.write(pathName, bs);

				OutputStream os = new BufferedOutputStream(
						new FileOutputStream(filePath));
				InputStream is = new BufferedInputStream(zfile
						.getInputStream(ze));
				int readLen = 0;
				while ((readLen = is.read(buf, 0, 1024)) != -1) {
					os.write(buf, 0, readLen);
				}
				is.close();
				os.close();
			}
		}
		zfile.close();
	}

	public static void main(String[] args) throws Exception {
		// byte[] old="中国你好aaa".getBytes("utf-8");
		// //log.info(HexUtils.format(old));
		// byte[] bs =gzip(old);
		// log.info(HexUtils.format(bs));
		// FileUtils.write("e:/ok.gzip", bs);
		// byte[] outbys=FileUtils.readBytes("e:/ok.gzip");
		// byte[] un=ungzip(outbys);
		// log.info(HexUtils.format(un));
		// log.info(new String(un,"utf-8"));
		//		
		// log.info("------------------");

		// ZipUtils.releaseZip("f:/tttt", "f:/222.zip");

		byte[] old = "中国你好aaa".getBytes("utf-8");
		// log.info(HexUtils.format(old));
		byte[] news = zip(old);
		log.info(ByteUtils.format(news));
		byte[] ss = unzip(news);
		log.info(new String(ss, "utf-8"));

		// Deflater

	}

}
