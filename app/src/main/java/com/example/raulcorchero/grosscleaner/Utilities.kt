package com.example.raulcorchero.grosscleaner
import android.content.*
import android.content.res.XmlResourceParser
import java.io.*
import com.fasterxml.jackson.module.kotlin.*
import org.w3c.dom.Document
import org.xmlpull.v1.XmlPullParser
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class Utilities {
    private var oContext: Context

    constructor (c: Context) {
        this.oContext = c
    }

    fun GetConfiguration () {
        var factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        var builder: DocumentBuilder = factory.newDocumentBuilder()
        var resourceName: String = oContext.resources.getResourceName(R.xml.tables_configuration)
        var sXMLFile: String = oContext.resources.getString(R.xml.tables_configuration)

        var oFile: File = oContext.classLoader.getResourceAsStream()

        Cla

/*
        var oInputStream : InputStream = oContext.resources.openRawResource(R.xml.tables_configuration)
        var oXML: Document = builder.parse (oInputStream)
*/

        /*
        var oInputStream: File = oContext.resources.openRawResource(R.xml.tables_configuration)
        var oXML: Document = builder.parse (oInputStream)
*/
        //var oFile: File = oContext.resources.getXml(resourceName)
//        var oFile : File = ClassLoader.getSystemResources(resourceName)
//        var oXML: Document = builder.parse (oFile)



//        var sXML: Document = builder.parse(File(sXMLFile))

        var p = 0

/*
        var eventType : int = oXML.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {

        }
*/
    }

    public fun saveUserdata(usuario: User) {
        val filename: String = R.string.userDataFileName.toString()
        val file: File = File(this.oContext.getFilesDir(), filename)
        var outputStream: FileOutputStream = this.oContext.openFileOutput(filename, Context.MODE_PRIVATE);
        val mapper = jacksonObjectMapper()
        mapper.writeValue(outputStream, usuario)
        outputStream.close();
    }

    public fun LoadUserdata() : User {
        val filename: String = R.string.userDataFileName.toString()
        var fis: FileInputStream = this.oContext.openFileInput(filename)
        var isr: InputStreamReader = InputStreamReader(fis)
        val mapper = jacksonObjectMapper()
        var usuario: User = mapper.readValue<User>(isr.readText())
        return usuario
    }
}