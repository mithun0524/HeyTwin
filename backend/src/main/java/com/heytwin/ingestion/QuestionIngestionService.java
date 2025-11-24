package com.heytwin.ingestion;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.heytwin.domain.entity.Question;
import com.heytwin.domain.entity.Topic;
import com.heytwin.domain.repository.QuestionRepository;
import com.heytwin.domain.repository.TopicRepository;
import com.heytwin.ingestion.model.OpenTdbQuestion;
import com.heytwin.ingestion.model.OpenTdbResponse;

@Service
public class QuestionIngestionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionIngestionService.class);
    private static final Pattern CATEGORY_PREFIX = Pattern.compile("^(Entertainment|Science):\\s*");

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;
    private final OpenTdbClient openTdbClient;
    private final OpenTdbMapper openTdbMapper;

    public QuestionIngestionService(TopicRepository topicRepository,
            QuestionRepository questionRepository,
            OpenTdbClient openTdbClient,
            OpenTdbMapper openTdbMapper) {
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
        this.openTdbClient = openTdbClient;
        this.openTdbMapper = openTdbMapper;
    }

    @Transactional
    public QuestionImportResult importFromOpenTdb(int amount) {
        OpenTdbResponse response = openTdbClient.fetchQuestions(amount);
        List<OpenTdbQuestion> payload = response.results() == null ? List.of() : response.results();
        int inserted = 0;
        int skipped = 0;
        for (OpenTdbQuestion item : payload) {
            Topic topic = findOrCreateTopic(item);
            Question question = openTdbMapper.toQuestion(item, topic);
            boolean exists = questionRepository.existsByTopicIdAndPromptIgnoreCase(topic.getId(), question.getPrompt());
            if (exists) {
                skipped++;
                continue;
            }
            questionRepository.save(question);
            inserted++;
        }
        QuestionImportResult result = new QuestionImportResult(amount, payload.size(), inserted, skipped);
        LOGGER.info("OpenTDB import finished. requested={}, fetched={}, inserted={}, skipped={}",
                result.requested(), result.fetched(), result.inserted(), result.skipped());
        return result;
    }

    private Topic findOrCreateTopic(OpenTdbQuestion item) {
        String subject = sanitizeSubject(item.category());
        return topicRepository.findBySubjectIgnoreCaseAndNameIgnoreCase(subject, subject)
                .orElseGet(() -> createTopic(subject));
    }

    @SuppressWarnings("null")
    private Topic createTopic(String subject) {
        return topicRepository.save(Topic.builder()
                .subject(subject)
                .name(subject)
                .description("Imported from OpenTDB")
                .build());
    }

    private String sanitizeSubject(String category) {
        if (!StringUtils.hasText(category)) {
            return "General Knowledge";
        }
        String normalized = CATEGORY_PREFIX.matcher(category).replaceFirst("").trim();
        if (normalized.isEmpty()) {
            normalized = "General Knowledge";
        }
        return normalized.substring(0, 1).toUpperCase(Locale.ROOT) + normalized.substring(1);
    }
}
