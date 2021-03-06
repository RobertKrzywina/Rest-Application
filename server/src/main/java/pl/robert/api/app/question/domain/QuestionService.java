package pl.robert.api.app.question.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.robert.api.app.challenge.domain.Challenge;
import pl.robert.api.app.question.query.QuestionQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class QuestionService {

    QuestionRepository repository;

    List<QuestionQuery> queryRandomQuestions() {
        return repository.findAll()
                .stream()
                .collect(toShuffledList())
                .stream()
                .map(question -> new QuestionQuery(
                        question.getId(),
                        question.getQuestion(),
                        Arrays.asList(question.getAnswers().split(":", -1))))
                .collect(Collectors.toList())
                .subList(0, 5)
                .stream()
                .sorted(Comparator.comparing(QuestionQuery::getQuestionId).reversed())
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private static <T> Collector<T, ?, List<T>> toShuffledList() {
        return (Collector<T, ?, List<T>>) SHUFFLER;
    }

    private static final Collector<?, ?, ?> SHUFFLER = Collectors.collectingAndThen(
            Collectors.toCollection(ArrayList::new),
            list -> {
                Collections.shuffle(list);
                return list;
            }
    );

    List<QuestionQuery> queryQuestionsOfDefenderChallengeId(Challenge challenge) {
        return challenge.getQuestions()
                .stream()
                .map(question -> new QuestionQuery(
                        question.getId(),
                        question.getQuestion(),
                        Arrays.asList(question.getAnswers().split(":", -1))))
                .filter(distinctByKey(QuestionQuery::getQuestionId))
                .sorted(Comparator.comparing(QuestionQuery::getQuestionId).reversed())
                .collect(Collectors.toList());
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    List<Character> calculateCorrectAnswers(List<Character> answers, List<Long> questionsId) {
        List<Character> correctAnswers = questionsId.stream()
                .map(repository::findById)
                .collect(Collectors.toList())
                .stream()
                .filter(Optional::isPresent)
                .map(correctAnswer -> correctAnswer.get().getCorrectAnswerShortForm().toString().charAt(0))
                .collect(Collectors.toList());

        List<Character> myAnswers = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            if (correctAnswers.get(i) == answers.get(i)) myAnswers.add('1');
            else myAnswers.add('0');
        }

        return myAnswers;
    }

    List<Question> queryQuestionsByIds(List<Long> questionsIds) {
        return questionsIds.stream()
                .map(repository::findById)
                .collect(Collectors.toList())
                .stream()
                .map(Optional::get)
                .sorted(Comparator.comparing(Question::getId).reversed())
                .collect(Collectors.toList());
    }
}
