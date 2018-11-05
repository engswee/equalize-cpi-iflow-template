import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange

// Load Groovy Script
GroovyShell shell = new GroovyShell()
def script = shell.parse(new File('src/main/resources/script/default.groovy'))

// Setup Camel context and exchange for the message
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new MessageImpl(exchange)

//--------------------------------------------------------------
// Initialize message with body, header and property
def body = new String('Hello Groovy World')
msg.setHeader('oldHeader', 'MyGroovyHeader')
msg.setProperty('oldProperty', 'MyGroovyProperty')
//--------------------------------------------------------------

// Set exchange body in case automatic Type Conversion is required
exchange.getIn().setBody(body)
msg.setBody(exchange.getIn().getBody())

// Execute script
script.processData(msg)

// Display results of script in console
println("Body:\r\n${msg.getBody()}")

def displayMaps = { String mapName, Map map ->
	println mapName
	map.each { key, value -> println("$key = $value") }
}
displayMaps('Headers:', msg.getHeaders())
displayMaps('Properties:', msg.getProperties())
