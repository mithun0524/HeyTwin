import numpy as np

from .schemas import TrainingRecord


def generate_records(size: int = 5000):
    rng = np.random.default_rng(42)
    records = []
    for _ in range(size):
        topic_mastery = rng.uniform(0.2, 0.95)
        recent_accuracy = topic_mastery + rng.normal(0, 0.1)
        avg_time = rng.uniform(20, 90)
        difficulty = rng.integers(1, 4)
        question_type = rng.choice(["MULTIPLE_CHOICE", "TRUE_FALSE", "SHORT_ANSWER", "FILL_BLANK"])
        base_prob = 0.4 * topic_mastery + 0.3 * max(min(recent_accuracy, 1), 0) + 0.2 * (1 - avg_time / 120) - 0.05 * (difficulty - 1)
        prob = 1 / (1 + np.exp(-8 * (base_prob - 0.5)))
        label = int(rng.random() < prob)
        records.append(TrainingRecord(
            topicMastery=float(np.clip(topic_mastery, 0, 1)),
            recentAccuracy=float(np.clip(recent_accuracy, 0, 1)),
            avgTimeSec=float(avg_time),
            difficulty=int(difficulty),
            questionType=str(question_type),
            label=label
        ))
    return records
