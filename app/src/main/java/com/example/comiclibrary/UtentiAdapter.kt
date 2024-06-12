package com.example.comiclibrary

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import java.util.regex.Pattern

class UtentiAdapter (private val dataSet: List<RecordUtente>, private val context:Context, private val fragManager:FragmentManager) :
        RecyclerView.Adapter<UtentiAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var emailLabel: TextView = view.findViewById(R.id.emailUtenteLista)
            var passwordLabel: TextView = view.findViewById(R.id.passwordUtenteLista)
            var adminLabel: TextView = view.findViewById(R.id.adminValueUtentiLista)
            var editButton: Button = view.findViewById(R.id.editUserButton)
            var deleteButton: Button = view.findViewById(R.id.deleteUserButton)
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.lista_utenti, viewGroup, false)

            return ViewHolder(view)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.emailLabel.text=dataSet[position].email
        holder.passwordLabel.text=dataSet[position].password
        holder.adminLabel.text= if(dataSet[position].admin)
            {holder.itemView.context.getString(R.string.admin_yes_profilo)}
            else{holder.itemView.context.getString(R.string.admin_no_profilo)}
        holder.editButton.setOnClickListener { onEditButtonClick(holder.itemView,dataSet[position].email) }
        holder.deleteButton.setOnClickListener { onDeleteButtonClick(holder.itemView,dataSet[position].email) }
    }

    fun onEditButtonClick(view:View, email:String)
    {
        val showPopUP=ModificaUtenteFragment(this,email)
        showPopUP.show(fragManager,"modificaUtente_popUp")
    }
    fun onDeleteButtonClick(view:View, email: String)
    {

    }
    fun editUserCallBack(emailOld:String, emailNew:String, password:String, admin:Boolean){
        if(validEmail(emailNew))
        {
            val db=DatabaseManager(context)
            db.open()
            if(db.updateUser(emailOld, emailNew ,password,admin)==1){
                Toast.makeText(context, context.getString(R.string.userUpdateSuccesful), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, context.getString(R.string.userUpdateError), Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, context.getString(R.string.emailPatternMismatch), Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteUserCallBack(email:String, view:View){

    }
    override fun getItemCount(): Int {
        return dataSet.size
    }
    fun validEmail(email:String):Boolean
    {
        val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

}