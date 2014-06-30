package com.comze_instancelabs.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class IYoutubeDownloader {

	// TODO support more formats

	int format = 18;
	static char[] illegal_filename_chars = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };
	static final Pattern semicolonPattern = Pattern.compile(";");
	static final Pattern pipePattern = Pattern.compile("\\|");
	static final DecimalFormat commaFormatNoPrecision = new DecimalFormat("###,###"); 

	public IYoutubeDownloader(String video) {
		String yt_prefix = "http://www.youtube.com/watch?v=";
		if (video.startsWith(yt_prefix)) {
			video = video.substring(yt_prefix.length());
		}
		int a = video.indexOf('&');
		if (a != -1) {
			video = video.substring(0, a);
		}
		try {
			download(video, 18, "UTF-8", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13", new File("."), getExtension(format));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void download(String videoId, int format, String encoding, String userAgent, File outputdir, String extension) throws Throwable {
		System.out.println("Retrieving " + videoId);
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("video_id", videoId));
		qparams.add(new BasicNameValuePair("fmt", "" + format));
		URI uri = getUri("get_video_info", qparams);

		CookieStore cookieStore = new BasicCookieStore();
		HttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		if (userAgent != null && userAgent.length() > 0) {
			httpget.setHeader("User-Agent", userAgent);
		}

		System.out.println("Executing " + uri);
		HttpResponse response = httpclient.execute(httpget, localContext);
		HttpEntity entity = response.getEntity();
		if (entity != null && response.getStatusLine().getStatusCode() == 200) {
			InputStream instream = entity.getContent();
			String videoInfo = getStringFromInputStream(encoding, instream);
			if (videoInfo != null && videoInfo.length() > 0) {
				List<NameValuePair> infoMap = new ArrayList<NameValuePair>();
				URLEncodedUtils.parse(infoMap, new Scanner(videoInfo), encoding);
				String downloadUrl = null;
				String filename = videoId;
				for (NameValuePair pair : infoMap) {
					String key = pair.getName();
					String val = pair.getValue();
					System.out.println(key + "=" + val);
					if (key.equals("title")) {
						filename = val;
					} else if (key.equals("url_encoded_fmt_stream_map")) {
						String t_ = URLDecoder.decode(val);
						String[] formats = semicolonPattern.split(t_);
						System.out.println("THE FREAKIN AWSUM URL: " + t_);
						boolean found = false;

						for (String fmt : formats) {
							if (fmt.contains("video/mp4") && fmt.contains("http")) {
								String u = fmt.substring(fmt.indexOf("url=") + 4);
								System.out.println("THE FREAKIN AWSUM URL 2: " + u);
								found = true;
								downloadUrl = u;
								break;
							}
						}
						if (!found) {
							System.out.println("Could not find video matching specified format, however some formats of the video do exist (use -verbose).");
						}
					}
				}

				filename = cleanFilename(filename);
				if (filename.length() == 0) {
					filename = videoId;
				} else {
					filename += "_" + videoId;
				}
				filename += "." + extension;
				File outputfile = new File(outputdir, filename);

				if (downloadUrl != null) {
					downloadWithHttpClient(userAgent, downloadUrl, outputfile);
				} else {
					System.out.println("Could not find video");
				}
			} else {
				System.out.println("Did not receive content from youtube");
			}
		} else {
			System.out.println("Could not contact youtube: " + response.getStatusLine());
		}
	}

	private static void downloadWithHttpClient(String userAgent, String downloadUrl, File outputfile) throws Throwable {
		HttpGet httpget2 = new HttpGet(downloadUrl);
		if (userAgent != null && userAgent.length() > 0) {
			httpget2.setHeader("User-Agent", userAgent);
		}

		System.out.println("Executing " + httpget2.getURI());
		HttpClient httpclient2 = new DefaultHttpClient();
		HttpResponse response2 = httpclient2.execute(httpget2);
		HttpEntity entity2 = response2.getEntity();
		if (entity2 != null && response2.getStatusLine().getStatusCode() == 200) {
			double length = entity2.getContentLength();
			if (length <= 0) {
				// Unexpected, but do not divide by zero
				length = 1;
			}
			InputStream instream2 = entity2.getContent();
			System.out.println("Writing " + commaFormatNoPrecision.format(length) + " bytes to " + outputfile);
			if (outputfile.exists()) {
				outputfile.delete();
			}
			FileOutputStream outstream = new FileOutputStream(outputfile);
			try {
				byte[] buffer = new byte[2048];
				double total = 0;
				int count = -1;
				int progress = 10;
				long start = System.currentTimeMillis();
				while ((count = instream2.read(buffer)) != -1) {
					total += count;
					int p = (int) ((total / length) * 100);
					if (p >= progress) {
						long now = System.currentTimeMillis();
						double s = (now - start) / 1000;
						int kbpers = (int) ((total / 1024) / s);
						System.out.println(progress + "% (" + kbpers + "KB/s)");
						progress += 10;
					}
					outstream.write(buffer, 0, count);
				}
				outstream.flush();
			} finally {
				outstream.close();
			}
			System.out.println("Done");
		}
	}

	private static String cleanFilename(String filename) {
		for (char c : illegal_filename_chars) {
			filename = filename.replace(c, '_');
		}
		return filename;
	}

	private static URI getUri(String path, List<NameValuePair> qparams) throws URISyntaxException {
		URI uri = URIUtils.createURI("http", "www.youtube.com", -1, "/" + path, URLEncodedUtils.format(qparams, "UTF-8"), null);
		return uri;
	}

	private static String getStringFromInputStream(String encoding, InputStream instream) throws UnsupportedEncodingException, IOException {
		Writer writer = new StringWriter();

		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(instream, encoding));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} finally {
			instream.close();
		}
		String result = writer.toString();
		return result;
	}

	public static String getExtension(int format) {
		switch (format) {
		case 18:
			return "mp4";
		default:
			throw new Error("Unsupported format " + format);
		}
	}
}
