package util;

public class ConfigUtil {

	/**
	 * KafkaProducer
	 */
	public final static String DEFAULT_TOPIC = "mykafka";
	public final static String METADATA_BROKER_LIST = "192.168.10.100:9092";
	public final static String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
	public final static String REQUEST_REQUIRED_ACKS = "1";
	
}
