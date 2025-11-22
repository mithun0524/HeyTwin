from typing import List, Literal, Optional

from pydantic import BaseModel, Field


QuestionType = Literal["MULTIPLE_CHOICE", "TRUE_FALSE", "SHORT_ANSWER", "FILL_BLANK"]
ModelType = Literal["RandomForest", "LogisticRegression"]
TrainingMode = Literal["SYNTHETIC", "REAL"]


class TrainingRecord(BaseModel):
    topicMastery: float = Field(..., ge=0, le=1)
    recentAccuracy: float = Field(..., ge=0, le=1)
    avgTimeSec: float = Field(..., ge=0)
    difficulty: int = Field(..., ge=1, le=3)
    questionType: QuestionType
    label: int = Field(..., ge=0, le=1)


class TrainRequest(BaseModel):
    modelType: ModelType = "RandomForest"
    trainingMode: TrainingMode = "SYNTHETIC"
    records: Optional[List[TrainingRecord]] = None


class TrainResponse(BaseModel):
    modelId: str
    metrics: dict
    savedAt: str


class PredictInstance(BaseModel):
    studentId: str
    questionId: str
    features: dict


class PredictRequest(BaseModel):
    modelId: Optional[str] = None
    instances: List[PredictInstance]


class PredictResponseItem(BaseModel):
    questionId: str
    prob: float


class PredictResponse(BaseModel):
    modelId: str
    predictions: List[PredictResponseItem]
