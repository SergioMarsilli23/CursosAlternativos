package mx.com.tutum.shared.infrastructure;

import java.text.SimpleDateFormat;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = -5794487996606350284L;
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public CustomObjectMapper() {
		setSerializationInclusion(Include.NON_NULL);
		
		configure(Feature.ALLOW_COMMENTS, true);
		//configure(Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
		enable(JsonReadFeature.ALLOW_LEADING_ZEROS_FOR_NUMBERS.mappedFeature());
		//configure(Feature.ALLOW_SINGLE_QUOTES, true);
		configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		//disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		//configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		//configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		//configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		setDateFormat(FORMATTER);
        registerModule(new JavaTimeModule());
	}
	
	public static CustomObjectMapper getNew() {
		return new CustomObjectMapper();
	}
}
