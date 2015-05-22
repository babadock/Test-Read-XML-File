#include <FileStorage-jni.h>
#include <opencv2/core/core.hpp>
#include <opencv2/contrib/detection_based_tracker.hpp>

#include <string>
#include <vector>
#include <android/log.h>

#define LOG_TAG "FileStorage/FileStorage-jni.cpp"
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__))
#define LOGI(...) ((void)__android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__))

using namespace std;
using namespace cv;

JNIEXPORT void JNICALL Java_com_example_testreadxmlfile_FileStorage_nativeCalcFeatures(
		JNIEnv *env, jclass, jstring location) {
	LOGD("Entering nativeCalcFeatures !!!");
	const char *nativeString = env->GetStringUTFChars(location, NULL);
	string filepath = string(nativeString);

	{
		Mat R = Mat_<uchar>::eye(3, 3), T = Mat_<double>::zeros(3, 1);
		int sz[3] = { 2, 2, 2 };
		//Mat L(3, sz, CV_8UC(1), Scalar::all(0));
		//Mat C = (Mat_<double>(3, 3) << 0, -1, 0, -1, 5, -1, 0, -1, 0);

		FileStorage fs(filepath, FileStorage::WRITE);
		fs << "iterationNr" << 100;
		fs << "strings" << "[";
		fs << "image1.jpg" << "Awasomeness" << "baboon.jpg";
		fs << "]";

		fs << "Mapping";
		fs << "{" << "One" << 1;
		fs << "Two" << 2 << "}";

		fs << "R" << R;
		fs << "T" << T;

		//fs << "C" << C;

		LOGD("WRITE successful !!!");

		fs.release();
	}

	{
		FileStorage fs;
		fs.open(filepath, FileStorage::READ);

		if (!fs.isOpened()) {
			LOGI("Can't open file: %s ", filepath.c_str());
		}
		//LOGD("DEBUG Step 1");

		FileNode n = fs["strings"];

		//LOGI("DEBUG Step 2: %d", n.type());

		if (n.type() != FileNode::SEQ) {
			LOGD("strings is not a sequence ! FAIL");
		}

		LOGI("DEBUG Step 3");

		/*FileNodeIterator it = n.begin(), it_end = n.end();
		 for (; it != it_end; it_end = n.end()) {
		 //LOGI();
		 }*/

		n = fs["Mapping"];
		LOGI("%d\n", (int )n["Two"]);
		LOGI("%d\n", (int )n["One"]);

		//LOGI("DEBUG Step 4");

		Mat R;
		fs["R"] >> R;

		LOGI("%d , %d\n", R.rows, R.cols);

		LOGI("DEBUG Step 5");

		//LOGD("READ ENDED !!!");
	}

}
JNIEXPORT void JNICALL Java_com_example_testreadxmlfile_FileStorage_nativeSayHello(
		JNIEnv *env, jclass) {
	LOGD("Entering nativeSayHello !!!");
}
