package br.com.haircutter.core.logback;

import com.fasterxml.jackson.core.JsonGenerator;
import net.logstash.logback.decorate.JsonGeneratorDecorator;

public class JsonGeneratorDecoratorConfig implements JsonGeneratorDecorator {
	@Override
	public JsonGenerator decorate(JsonGenerator jsonGenerator) {
		return jsonGenerator.useDefaultPrettyPrinter();
	}
}
