package com.equalize.cpi.groovy.testing

import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
// Load Groovy Script
GroovyShell shell = new GroovyShell()
def script = shell.parse(new File("src/main/resources/script/default.groovy"))
// Initialize message with body, header and property
Message msg = new MessageImpl()
msg.setBody(new String("Hello Groovy World"))
msg.setHeader("oldHeader", "MyGroovyHeader")
msg.setProperty("oldProperty", "MyGroovyProperty")
// Execute script
script.processData(msg)
// Display results of script in console
println("Body:\r\n" + msg.getBody())

def displayMaps = { String mapName, Map map ->
	println mapName
	map.each { key, value -> println( key + " = " + value) }
}
displayMaps("Headers:", msg.getHeaders())
displayMaps("Properties:", msg.getProperties())
