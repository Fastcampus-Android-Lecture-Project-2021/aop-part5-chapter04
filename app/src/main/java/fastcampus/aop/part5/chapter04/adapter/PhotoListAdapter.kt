package fastcampus.aop.part5.chapter04.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.aop.part5.chapter04.databinding.ViewholderPhotoItemBinding
import fastcampus.aop.part5.chapter04.extensions.loadCenterCrop

class PhotoListAdapter(
    private val photoItemClickListener: (Uri, Int) -> Unit,
    private val removePhotoListener: (Uri) -> Unit
) : RecyclerView.Adapter<PhotoListAdapter.ProductItemViewHolder>() {

    private var imageUriList: List<Uri> = listOf()

    inner class ProductItemViewHolder(
        private val binding: ViewholderPhotoItemBinding,
        val productItemClickListener: (Uri, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: Uri) = with(binding) {
            photoImageView.loadCenterCrop(data.toString(), 8f)
        }

        fun bindViews(data: Uri, position: Int) = with(binding) {
            root.setOnClickListener {
                productItemClickListener(data, position)
            }
            closeButton.setOnClickListener {
                removePhotoListener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = ViewholderPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(view, photoItemClickListener)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bindData(imageUriList[position])
        holder.bindViews(imageUriList[position], position)
    }

    override fun getItemCount(): Int = imageUriList.size

    fun setPhotoList(imageUriList: List<Uri>) {
        this.imageUriList = imageUriList
        notifyDataSetChanged()
    }
}
