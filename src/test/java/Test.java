import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload;
import com.google.cloud.logging.Severity;
import com.google.cloud.logging.Synchronicity;

import java.io.FileInputStream;
import java.util.Collections;

public class Test
{
	@org.junit.Test
	public void testWithoutEnvironmentVariable() throws Exception
	{
		final LoggingOptions.Builder builder = LoggingOptions.newBuilder();

		if (System.getenv("GOOGLE_APPLICATION_CREDENTIALS_PATH") != null)
			builder.setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(System.getenv(
					"GOOGLE_APPLICATION_CREDENTIALS_PATH"))));

		Logging logging = builder.build().getService();

		logging.setWriteSynchronicity(Synchronicity.SYNC);

		logging.setFlushSeverity(Severity.DEBUG);

		LogEntry entry = LogEntry
				                 .newBuilder(Payload.StringPayload.of("Hello world"))
				                 .setSeverity(Severity.INFO)
				                 .setLogName("test-log")
				                 .setResource(MonitoredResource.newBuilder("global").build())
				                 .build();

		logging.write(Collections.singleton(entry)); // Throws PERMISSION_DENIED
	}
}
