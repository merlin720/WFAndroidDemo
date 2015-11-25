package com.whiskeyfei.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fei.library.fragment.DPBaseFragment;
import com.fei.library.inter.DPMultiItemTypeSupport;
import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;
import com.whiskeyfei.R;
import com.whiskeyfei.adapter.DPChatAdapter;
import com.whiskeyfei.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatListDemoPage extends DPBaseFragment implements DPOnItemChildViewByIdClickListener,DPOnItemChildViewByIdLongClickListener {
	
	private static final String TAG = "ChatListDemoPage";
	private ListView mListView;
	private List<ChatMessage> mDataList = new ArrayList<ChatMessage>();
	private DPChatAdapter mDpListViewAdapter;
	private View mMainView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mMainView = inflater.inflate(R.layout.listview, null);
		initView();
		initData();
		return mMainView;
	}

	private DPMultiItemTypeSupport<ChatMessage> mMultiItemTypeSupport = new DPMultiItemTypeSupport<ChatMessage>() {
		@Override
		public int getLayoutId(int position, ChatMessage msg) {
			if (msg.isComMeg()) {
				return R.layout.listview_chat_from_msg;
			}
			return R.layout.listview_chat_send_msg;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int postion, ChatMessage msg) {
			if (msg.isComMeg()) {
				return ChatMessage.RECIEVE_MSG;
			}
			return ChatMessage.SEND_MSG;
		}
	};

	private void initData() {
		for (int i = 0; i < 30; i++) {
			ChatMessage model = null;
			if (i%2 == 0){
				model = new ChatMessage(R.drawable.ic_launcher, "小明", "How do you do! ",null, false);
			}else {
				model = new ChatMessage(R.drawable.ic_launcher, "老王", "do do do! ",null, true);
			}

			mDataList.add(model);
		}
	}

	private void initView() {
		mListView = (ListView) mMainView.findViewById(R.id.list_view);
		mDpListViewAdapter = new DPChatAdapter(getActivity(),mDataList, mMultiItemTypeSupport);
		mDpListViewAdapter.setOnItemChildViewByIdClickListener(this);
		mDpListViewAdapter.setOnItemChildViewByIdLongClickListener(this);
		mListView.setAdapter(mDpListViewAdapter);
	}

	@Override
	public boolean onItemChildViewByIdLongClick(ViewGroup viewGroup,View v, int position) {
		if (v.getId() == R.id.chat_from_content) {
			Toast.makeText(getActivity(), "长按了 chat_from_content" +  position,Toast.LENGTH_SHORT).show();
			return true;
		}else if (v.getId() == R.id.chat_send_content) {
			Toast.makeText(getActivity(), "长按了 chat_send_content" +  position,Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	@Override
	public void onItemChildViewByIdClick(View v, int position) {
		if (v.getId() == R.id.chat_from_content) {
			Toast.makeText(getActivity(), "按了 chat_from_content" +  position,Toast.LENGTH_SHORT).show();
        }else if(v.getId() == R.id.chat_send_content){
			Toast.makeText(getActivity(), "按了 chat_send_content" +  position,Toast.LENGTH_SHORT).show();
		}
	}
}
