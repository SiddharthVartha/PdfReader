package com.example.pdfreader

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class ViewPdfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("MyPdfReader")
        setContentView(R.layout.activity_view_pdf)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        selectPdfFromStorage()

    }
    val PDF_SELECTION_CODE=100
    private fun selectPdfFromStorage() {
       Toast.makeText(this@ViewPdfActivity,"SELECT PDF FILE",Toast.LENGTH_SHORT).show()
        val browseStorage =Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type="application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(Intent.createChooser(browseStorage,"Select PDF"),PDF_SELECTION_CODE)
    }

    fun showPdfFromURI(uri: Uri?){
        var pdfView=findViewById<com.github.barteksc.pdfviewer.PDFView>(R.id.pdfView)
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    override fun onActivityResult(requestCode:Int,resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode, data)
        if( requestCode==PDF_SELECTION_CODE && resultCode== Activity.RESULT_OK && data!= null){
            val selectedPdf=data.data
            showPdfFromURI(selectedPdf)

        }
    }
}