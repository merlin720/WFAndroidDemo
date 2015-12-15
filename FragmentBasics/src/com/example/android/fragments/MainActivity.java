/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.android.fragments.inter.IOnHeadlineSelectedListener;

/**
 * 必须实现IOnHeadlineSelectedListener接口，作为fragment左侧列表的点击回调
 * 一般fragment交互都是通过activity进行，
 * 
 */
public class MainActivity extends FragmentActivity implements IOnHeadlineSelectedListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//这里会会根据屏幕尺寸来选择不同的xml布局
		setContentView(R.layout.main);
		
		// 检查外层FrameLayout是否存在，和加载不同布局是相互其作用的
		if (findViewById(R.id.fragment_container) != null) {
			if (savedInstanceState != null) {
				return;
			}
			HeadlinesFragment firstFragment = new HeadlinesFragment();
			firstFragment.setArguments(getIntent().getExtras());//通过setArguments设置参数
			// 添加fragment到FrameLayout布局中
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
		}
	}

	@Override
	public void onArticleSelected(int position) {
		ArticleFragment articleFrag = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);
		//平板等大屏，布局中直接有左侧和右侧，所以不用创建fragment，直接更新内容
		if (articleFrag != null) {
			articleFrag.updateArticleView(position);
		} else {
			//手机屏幕，需要动态new fragment添加到fragment_container布局中，并且添加回退栈，这样才能依次返回，同时设置参数position
			ArticleFragment newFragment = new ArticleFragment();
			Bundle args = new Bundle();
			args.putInt(ArticleFragment.ARG_POSITION, position);
			newFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment_container, newFragment);
			transaction.addToBackStack(null);//添加回退栈 内部是BackStackRecord记录依次操作
			transaction.commit();// 提交事务
		}
	}
}