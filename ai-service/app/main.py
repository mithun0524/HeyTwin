from datetime import datetime
from typing import List

from fastapi import FastAPI, HTTPException

from .model_manager import model_manager
from .schemas import PredictRequest, PredictResponse, PredictResponseItem, TrainRequest, TrainResponse
from .synthetic import generate_records

app = FastAPI(title="HeyTwin AI Service")


@app.get("/health")
def health():
    return {"status": "ok", "modelLoaded": model_manager.model is not None}


@app.post("/train", response_model=TrainResponse)
def train(request: TrainRequest):
    records = request.records or generate_records()
    model_id, metrics = model_manager.train(request.modelType, records)
    return TrainResponse(modelId=model_id, metrics=metrics, savedAt=datetime.utcnow().isoformat() + "Z")


@app.post("/predict", response_model=PredictResponse)
def predict(request: PredictRequest):
    if not request.instances:
        raise HTTPException(status_code=400, detail="instances cannot be empty")

    if model_manager.model is None:
        # auto-train with synthetic data when first hit
        model_manager.train("RandomForest", generate_records(2000))

    rows: List[dict] = []
    for instance in request.instances:
        features = instance.features.copy()
        features.setdefault("topicMastery", 0.5)
        features.setdefault("recentAccuracy", 0.5)
        features.setdefault("avgTimeSec", 45)
        features.setdefault("difficulty", 2)
        question_type = features.get("questionType", "MULTIPLE_CHOICE")
        features["questionType"] = {
            "MULTIPLE_CHOICE": 0,
            "TRUE_FALSE": 1,
            "SHORT_ANSWER": 2,
            "FILL_BLANK": 3
        }.get(question_type, 0)
        rows.append(features)

    probs = model_manager.predict(rows)
    predictions = [
        PredictResponseItem(questionId=instance.questionId, prob=float(prob))
        for instance, prob in zip(request.instances, probs)
    ]

    model_id = model_manager.meta.get("modelId", "unknown")
    return PredictResponse(modelId=model_id, predictions=predictions)
