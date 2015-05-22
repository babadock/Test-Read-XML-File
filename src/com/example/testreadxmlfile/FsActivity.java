package com.example.testreadxmlfile;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class FsActivity extends ActionBarActivity {
	private static final String TAG = "OCVSample::Activity";
	private FileStorage fs;
	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS: {
				Log.i(TAG, "OpenCV loaded successfully");
				System.loadLibrary("filestorage");

				//String dirname = Environment.getExternalStorageDirectory();
				
				// String path =
				fs = new FileStorage();
				//fs.CalcFeatures("test.xml");
				fs.SayHello();
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
