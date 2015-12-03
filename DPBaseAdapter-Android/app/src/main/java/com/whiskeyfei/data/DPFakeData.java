package com.whiskeyfei.data;

import com.whiskeyfei.utils.ApiConstant;

public class DPFakeData {
	private final String FILE_NAME = ApiConstant.COMMON_ROOT + ApiConstant.COMMON_COMMON_FILE;
	private static DPFakeData mInstance = new DPFakeData();

	private DPFakeData() {
	}

	public static DPFakeData get() {
		return mInstance;
	}
	
//	public List<DPItemModel> getDatas(){
//		String data = AssetsUtils.getDataFromAssets(DPAppClient.get().getApplicationContext(), FILE_NAME);
//		if (StringUtils.isEmpty(data)) {
//			return null;
//		}
//		List<DPItemModel> listModels = (ArrayList<DPItemModel>) JSON.parseArray(data, DPItemModel.class);
//		return listModels;
//	}
}
