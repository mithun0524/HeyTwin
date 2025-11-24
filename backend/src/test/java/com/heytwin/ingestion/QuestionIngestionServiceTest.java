package com.heytwin.ingestion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heytwin.domain.entity.Question;
import com.heytwin.domain.entity.Topic;
import com.heytwin.domain.repository.QuestionRepository;
import com.heytwin.domain.repository.TopicRepository;
import com.heytwin.ingestion.model.OpenTdbQuestion;
import com.heytwin.ingestion.model.OpenTdbResponse;

@SuppressWarnings({ "null", "unused" })
@ExtendWith(MockitoExtension.class)
class QuestionIngestionServiceTest {

    @Mock
    private TopicRepository topicRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private OpenTdbClient openTdbClient;

    private OpenTdbMapper openTdbMapper;
    private QuestionIngestionService ingestionService;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        openTdbMapper = new OpenTdbMapper(new ObjectMapper());
        ingestionService = new QuestionIngestionService(topicRepository, questionRepository, openTdbClient,
                openTdbMapper);
    }

    @Test
    void importFromOpenTdb_insertsNewQuestionsAndSkipsDuplicates() {
        Topic topic = Topic.builder()
                .id(UUID.randomUUID())
                .name("Mathematics")
                .subject("Mathematics")
                .description("Imported")
                .build();
        when(topicRepository.findBySubjectIgnoreCaseAndNameIgnoreCase(anyString(), anyString()))
                .thenReturn(Optional.of(topic));

        OpenTdbQuestion first = new OpenTdbQuestion("Science: Mathematics", "multiple", "easy", "What is 2+2?",
                "4", List.of("3", "5", "6"));
        OpenTdbQuestion duplicate = new OpenTdbQuestion("Science: Mathematics", "multiple", "easy",
                "What is 2+2?", "4", List.of("1", "2", "3"));
        when(openTdbClient.fetchQuestions(5)).thenReturn(new OpenTdbResponse(0, List.of(first, duplicate)));

        when(questionRepository.existsByTopicIdAndPromptIgnoreCase(eq(topic.getId()), anyString()))
                .thenReturn(false, true);
        when(questionRepository.save(any(Question.class))).thenAnswer(invocation -> invocation.getArgument(0));

        QuestionImportResult result = ingestionService.importFromOpenTdb(5);

        assertThat(result.requested()).isEqualTo(5);
        assertThat(result.fetched()).isEqualTo(2);
        assertThat(result.inserted()).isEqualTo(1);
        assertThat(result.skipped()).isEqualTo(1);
        verify(questionRepository, times(1)).save(any(Question.class));
    }
}
