package com.example.testreadxmlfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class FsActivity extends ActionBarActivity {
	private static final String TAG = "OCVSample::Activity";
	private File mCascadeFile;
	private FileStorage fs;
	
	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		

		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS: {
				Log.i(TAG, "OpenCV loaded successfully");
				System.loadLibrary("filestorage");
				try {
					InputStream is = getResources().openRawResource(
							R.raw.lbpcascade_frontalface);
					File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
					mCascadeFile = new File(cascadeDir,
							"lbpcascade_frontalface.xml");

					FileOutputStream os = new FileOutputStream(mCascadeFile);

					byte[] buffer = new byte[4096];
					int bytesRead;
					while ((bytesRead = is.read(buffer)) != -1) {
						os.write(buffer, 0, bytesRead);
					}
					is.close();
					os.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					Log.e(TAG, "Failed to load file. Exception thrown: " + e);
				}
				
				fs = new FileStorage();
				fs.CalcFeatures(mCascadeFile.getAbsolutePath());
				// fs.SayHello();
			}
				break;
			default: {
				super.onManagerConnected(status);
			}
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onResume() {
		super.onResume();
		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this,
				mLoaderCallback);
	}

}
