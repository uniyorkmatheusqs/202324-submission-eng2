package uk.ac.york.eng2.assessment.trending.events;

import java.time.Duration;
import java.util.stream.Collectors;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.TimeWindows;

import io.micronaut.configuration.kafka.serde.SerdeRegistry;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import main.java.uk.ac.york.eng2.assessment.trending.events.ITopTrendingHashtagsConsumer;
import main.java.uk.ac.york.eng2.assessment.trending.events.IVideoInteractionConsumer;
import main.java.uk.ac.york.eng2.assessment.trending.events.InteractionEventKey;
import main.java.uk.ac.york.eng2.assessment.trending.events.TrendEventKey;
import uk.ac.york.eng2.assessment.trending.clients.VideoClient;
import uk.ac.york.eng2.assessment.trending.domain.Hashtag;

@Factory
public class TopHashtagsStream {
	@Inject
	private SerdeRegistry serdeRegistry;

	@Inject
	private VideoClient client;

	@Singleton
	public KStream<TrendEventKey, Long> trend(ConfiguredStreamBuilder builder) {
		// Properties props = builder.getConfiguration();
		// props.put(StreamsConfig.APPLICATION_ID_CONFIG, "trending-metrics");

		// props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);

		KStream<InteractionEventKey, InteractionEventImpl> interactionStream = builder
				.stream(IVideoInteractionConsumer.TOPIC_VIDEO_INTERACTION, Consumed.with(
						serdeRegistry.getSerde(InteractionEventKey.class),
						serdeRegistry.getSerde(InteractionEventImpl.class)));

		KStream<TrendEventKey, Long> stream = interactionStream.filter((key, value) -> value.getLiked())
				.flatMapValues((key, value) -> {
					var video = client.listOne(Long.parseLong(key.getVideoId()));

					return video.getHashtags()
							.stream()
							.map(Hashtag::getValue)
							.collect(Collectors.toList());
				})
				.groupBy((key, value) -> value, Grouped.with(Serdes.String(), Serdes.String()))
				.windowedBy(TimeWindows.of(Duration.ofHours(1)).advanceBy(Duration.ofHours(1)))
				.count(Materialized.as("hashtag-count"))
				.toStream()
				.selectKey((k, v) -> new TrendEventKeyImpl(k.key(), k.window().start(), k.window().end()));

		stream.to(ITopTrendingHashtagsConsumer.TOPIC_TOP_TRENDING_HASHTAGS,
				Produced.with(serdeRegistry.getSerde(TrendEventKey.class), Serdes.Long()));

		return stream;
	}

}
