package com.example.mynews2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.modernnewsapp.R
import com.example.modernnewsapp.databinding.ItemNewsBinding
import com.example.mynews2.models.Article

class NewsAdapter(
    val list:ArrayList<Article>,
    val context: Context
  ):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){
    //кривая реализация rv!!!
    inner class ArticleViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)


        private lateinit var articleImage:ImageView
        private lateinit var articleSource:TextView
        private lateinit var articleTitle:TextView
        private lateinit var articleDescription:TextView
        private lateinit var articleDataTime:TextView

        private val differCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        )
    }

    //currentList-текущий лист пользователей!!!
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener:((Article)->Unit)?=null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]
        articleImage=holder.itemView.findViewById(R.id.articleImage)
        articleSource=holder.itemView.findViewById(R.id.articleSource)
        articleTitle=holder.itemView.findViewById(R.id.articleTitle)
        articleDescription=holder.itemView.findViewById(R.id.articleDescription)
        articleDataTime=holder.itemView.findViewById(R.id.articleDateTime)

        //написанное ранне применеям к этому!!!
        holder.itemView.apply{
            //тут идет забор параметров из др классов!!!
            Glide.with(this).load(article.urlToImage).into(articleImage)
            articleSource.text=article.source?.name
            articleTitle.text=article.title
            articleDescription.text=article.description
            articleDataTime.text=article.publishedAt

            setOnClickListener{
                onItemClickListener?.let{
                    it(article)
                }
            }
        }
    }

    fun setOnItemClickLister(listener:(Article)->Unit){
        onItemClickListener=listener
    }
  }