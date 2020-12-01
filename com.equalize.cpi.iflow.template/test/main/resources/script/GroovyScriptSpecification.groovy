package test.main.resources.script

import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
import spock.lang.Shared

class GroovyScriptSpecification extends spock.lang.Specification {

	@Shared GroovyShell shell = new GroovyShell()
	@Shared Script script
	private Message msg
	
	def setupSpec() {
		// Load Groovy Script		
		script = shell.parse(new File("src/main/resources/script/default.groovy"))
	}
	
	def setup() {
		this.msg = new MessageImpl()
	}
	
	def "message body, headers and properties are updated "() {
		
		given: "the message body, header and property is provided with initial value"
		this.msg.setBody(new String("Hello Groovy World"))
		this.msg.setHeader("oldHeader", "MyGroovyHeader")
		this.msg.setProperty("oldProperty", "MyGroovyProperty")
		
		when: "we execute the Groovy script"
		script.processData(this.msg)
		
		then: "the body, headers and properties are correctly populated or modified"
		this.msg.getBody() == "Hello Groovy WorldBody is modified"
		this.msg.getHeaders()["oldHeader"] == "MyGroovyHeadermodified"
		this.msg.getHeaders()["newHeader"] == "newHeader"
		this.msg.getProperties()["oldProperty"] == "MyGroovyPropertymodified"
		this.msg.getProperties()["newProperty"] == "newProperty"
	}
}
