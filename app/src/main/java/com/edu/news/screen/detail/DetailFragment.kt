package com.edu.news.screen.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.edu.news.data.model.Article
import com.edu.news.data.source.remote.fetchData.LoadImageFromUrl
import com.edu.news.utils.Constant
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_detail.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailFragment : BaseFragment() {

    private var article: Article? = null

    override fun getLayoutResourceId() = R.layout.fragment_detail

    override fun initView(view: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageBackDetail.setOnClickListener { fragmentManager?.popBackStack() }
    }

    override fun initData() {
        arguments?.let {
            article = it.getParcelable(ARGUMENT_ARTICLE)
        }
        article?.apply {
            textViewPublisherDetail.text = name
            textViewTitleDetail.text = title
            textViewDescriptionDetail.text = description
            textViewDateTimeDetail.text =
                LocalDateTime.parse(publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern(Constant.DATE_TIME_FORMAT))
            LoadImageFromUrl(imageArticleDetail).loadImage(urlToImage)
            textViewContentDetail.text = content
            textViewAuthorDetail.text = author
        }
    }

    companion object {
        private const val ARGUMENT_ARTICLE = "ARGUMENT_ARTICLE"

        fun newInstance(article: Article?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_ARTICLE to article)
        }
    }
}
