package com.example.themarketer.ui.Menu.MenuSections

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themarketer.R
import com.example.themarketer.data.model.Menu.MenuSections.SectionItem

class MenuChildSectionsAdapter (  childList: List<SectionItem>, type:String, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    internal val VIEW_TYPE_ONE = 1
    internal val VIEW_TYPE_TWO = 2
    internal val VIEW_TYPE_THREE = 3
   var childList: List<SectionItem>? = null
   public var designType:String?=null
    private var context: Context? = null
    lateinit var mItemProductCLicked: ItemProductCLickedListener


 init {
     this.childList = childList
     this.context = context
     this.designType=type
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
     return if(viewType == VIEW_TYPE_ONE)
     {
         TopMarketersViewHolder(LayoutInflater.from(context).inflate(R.layout.row_menu_top_marketer, parent, false))
     }else if(viewType == VIEW_TYPE_TWO){
         ProductsViewHolder(LayoutInflater.from(context).inflate(R.layout.row_sample_people_also_bought, parent, false))
     }
     else  MainViewHolder(LayoutInflater.from(context).inflate(R.layout.row_menu_child_sections, parent, false))
 }

 //this method is binding the data on the list
 override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


     //holder.itemView.rvMenuChildSections.setAdapter(MenuChildSectionsAdapter(parentObject.get(position).name, context = context!!.applicationContext))

     if (designType .equals("1")) { // put your condition, according to your requirements
         childList?.get(position)?.let {(holder as TopMarketersViewHolder).bindTopMarketersItems(it) }
         holder.itemView.setOnClickListener {view->
             view.findNavController().navigate(R.id.action_menuFragment_to_topMarketersDetailsFragment)
             //mItemProductCLicked.onItemProductClicked(childList!!.get(position).id)
         }
     } else if(designType.equals("2")) {

         childList?.get(position)?.let {(holder as ProductsViewHolder).bindProductsItems(it) }
         holder.itemView.setOnClickListener {view->
             val bundle = bundleOf(
                 "productId" to childList!!.get(position).id.toString())
             println("success :-)")
             view.findNavController().navigate(R.id.action_menuFragment_to_productDetailsFragment, bundle)

             //mItemProductCLicked.onItemProductClicked(childList!!.get(position).id)
         }
     }else {
         childList?.get(position)?.let { (holder as MainViewHolder).bindItems(it) }
         holder.itemView.setOnClickListener {view->
             view.findNavController().navigate(R.id.action_menuFragment_to_popularBrandsDetailsFragment)
             //mItemProductCLicked.onItemProductClicked(childList!!.get(position).id)
         }
     }
 }

 //this method is giving the size of the list
 override fun getItemCount(): Int {
    /* if(childList?.size!=null)
     {
     if(childList!!.size>5)
         return 5
         return childList!!.size
     }
     return 0*/
     return childList!!.size
 }

 //the class is hodling the list view
 class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     private val context: Context = itemView.context
     fun bindItems(sectionItem: SectionItem) {
         val imgItem = itemView.findViewById(R.id.imgItem) as ImageView
         val tItemName  = itemView.findViewById(R.id.tItemName) as TextView
         val tItemPadge  = itemView.findViewById(R.id.tPadge) as TextView


         Glide.with(context)
             .load(sectionItem.image)
             .into(imgItem)

         tItemName.text = sectionItem.name
         tItemPadge.text=sectionItem.padge



     }
 }
 class TopMarketersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
     private val context: Context = view.context
     fun bindTopMarketersItems(sectionItem: SectionItem) {
         val imgTopMarketer = itemView.findViewById(R.id.imgTopMarketer) as ImageView
         val tTopMarketerName  = itemView.findViewById(R.id.tTopMarketerName) as TextView

         Glide.with(context)
             .load(sectionItem.image)
             .into(imgTopMarketer)

         tTopMarketerName.text = sectionItem.name


     }
 }
    fun setUpProductListener(itemProductCLicked: ItemProductCLickedListener){
        this.mItemProductCLicked = itemProductCLicked
    }
    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val context: Context = itemView.context
        fun bindProductsItems(sectionItem: SectionItem) {
            val img_Product = itemView.findViewById(R.id.img_Product) as ImageView
            val tProductName  = itemView.findViewById(R.id.tProductName) as TextView
            val tNewPrice  = itemView.findViewById(R.id.tProdNewPrice) as TextView
            val tOldPrice  = itemView.findViewById(R.id.tOldPrice) as TextView
            val imgStoreLogo = itemView.findViewById(R.id.imgStoreLogo) as ImageView
            val tProductPadge  = itemView.findViewById(R.id.tProductPadge) as TextView


            Glide.with(context)
                .load(sectionItem.image)
                .into(img_Product)
            tProductName.text=sectionItem.name
            tProductPadge.text=sectionItem.padge

            Glide.with(context)
                .load(sectionItem.storeImage)
                .into(imgStoreLogo)

            //note: check old,new proce
            tNewPrice.text=sectionItem.price.get(0).priceAfterDiccount.toString()
            tOldPrice.text=sectionItem.price.get(0).price.toString()
            tOldPrice.setPaintFlags(tOldPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
//            tNewPrice.text = sectionItem.price.get(0).priceAfterDiccount.toString()+" QAR"
   //        tOldPrice.text = sectionItem.price.get(0).price.toString()+" QAR"
   //      tOldPrice.setPaintFlags(tOldPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)



        }
    }
 /* interface ItemInterestsCLickedListener {
        fun onInterestsItemClicked(countryDetails: CountryDetails)
    }*/
 override fun getItemViewType(position: Int): Int {

     // here you can get decide from your model's ArrayList, which type of view you need to load. Like
     return if (designType.equals("1")) { // put your condition, according to your requirements
         VIEW_TYPE_ONE
     }else if(designType.equals("2")){
         VIEW_TYPE_TWO
     } else VIEW_TYPE_THREE
 }

    interface ItemProductCLickedListener {
        fun onItemProductClicked(id: Int)
    }
}