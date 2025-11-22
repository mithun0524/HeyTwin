import json
from pathlib import Path
from typing import Tuple

import joblib
import numpy as np
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, f1_score

from .schemas import ModelType, TrainingRecord

MODEL_DIR = Path(__file__).resolve().parent.parent / "models"
MODEL_DIR.mkdir(parents=True, exist_ok=True)
MODEL_PATH = MODEL_DIR / "model.pkl"
META_PATH = MODEL_DIR / "model_meta.json"


class ModelManager:
    def __init__(self):
        self.model = None
        self.meta = {}
        if MODEL_PATH.exists():
            self.model = joblib.load(MODEL_PATH)
        if META_PATH.exists():
            self.meta = json.loads(META_PATH.read_text())

    def _build_model(self, model_type: ModelType):
        if model_type == "RandomForest":
            return RandomForestClassifier(n_estimators=200, max_depth=6, random_state=42)
        return LogisticRegression(max_iter=500)

    def _prep_dataframe(self, records):
        df = pd.DataFrame([record.model_dump() for record in records])
        df["questionType"] = df["questionType"].map({
            "MULTIPLE_CHOICE": 0,
            "TRUE_FALSE": 1,
            "SHORT_ANSWER": 2,
            "FILL_BLANK": 3
        })
        X = df.drop(columns=["label"])
        y = df["label"].astype(int)
        return X, y

    def train(self, model_type: ModelType, records: list[TrainingRecord]) -> Tuple[str, dict]:
        X, y = self._prep_dataframe(records)
        model = self._build_model(model_type)
        model.fit(X, y)
        preds = model.predict(X)
        metrics = {
            "accuracy": float(accuracy_score(y, preds)),
            "f1": float(f1_score(y, preds))
        }
        model_id = f"{model_type[:2].lower()}-{Path(MODEL_PATH).stat().st_mtime_ns if MODEL_PATH.exists() else 0}"
        joblib.dump(model, MODEL_PATH)
        META_PATH.write_text(json.dumps({
            "modelId": model_id,
            "modelType": model_type,
            "metrics": metrics
        }, indent=2))
        self.model = model
        self.meta = {"modelId": model_id, "modelType": model_type, "metrics": metrics}
        return model_id, metrics

    def predict(self, feature_rows):
        if self.model is None:
            raise ValueError("Model not trained yet")
        df = pd.DataFrame(feature_rows)
        probs = self.model.predict_proba(df)[:, 1]
        return probs


model_manager = ModelManager()
