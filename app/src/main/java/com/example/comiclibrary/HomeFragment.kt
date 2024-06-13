package com.example.comiclibrary

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment(val userID:String) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        val db_manager= DatabaseManager(this.context)
        db_manager.open()
        val fumettiList=db_manager.fumettiMancoList(userID)
        mostraFumetti(fumettiList,view,R.id.mancoListFumettiRecycler)
        fumettiList.close()
        val availableList=db_manager.ricercaFumettiDisponibili()
        mostraFumetti(availableList,view,R.id.availableFumettiRecycler)
        availableList.close()
        return view
    }

    fun mostraFumetti(fumetti: Cursor, view: View, idRecyclerView:Int) {
        val recyclerView= view.findViewById<RecyclerView>(idRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val fumettiList= mutableListOf<RecordFumetto>()
        if(fumetti.count>0) {
            do {
                val copertina = if (fumetti.isNull(0)) null else fumetti.getBlob(0)
                fumettiList.add(
                    RecordFumetto(
                        copertina,
                        fumetti.getString(1),
                        fumetti.getString(2),
                        fumetti.getInt(3)
                    )
                )
            } while (fumetti.moveToNext())
        }
        val adapter= FumettiAdapter(fumettiList)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db_manager= DatabaseManager(this.context)
        db_manager.open()
        val fumettiList=db_manager.fumettiMancoList(userID)
        mostraFumetti(fumettiList,view,R.id.mancoListFumettiRecycler)
        fumettiList.close()
        val availableList=db_manager.ricercaFumettiDisponibili()
        mostraFumetti(availableList,view,R.id.availableFumettiRecycler)
        availableList.close()
    }
}