package code
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import code.lib._
import Helpers._

import net.liftweb.http.S
import net.liftweb.util.ValueCell
import net.liftweb.http.WiringUI
import net.liftweb.http.SHtml
import net.liftweb.json._

import net.liftweb.http.rest.RestHelper

object HelloWorld extends RestHelper with Loggable {

	private val words = ValueCell[List[String]](Nil)  
 
	def render = "#words" #> WiringUI.asText(words)

	serve {
		case "tokenize" :: Nil Post _ => for { sentence <- S.param("sentence") ?~ "no text" ~> 400
    		} yield { 
	    		tokenize(sentence)
	    		JNull 
	    	}    
	}

	private def tokenize(text: String) {
		val toks = text.split("\\W").toList
		logger.info("Tokenized: "+toks)
		words.set(toks)
	}

}

