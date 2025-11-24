package com.heytwin.ingestion;

public record QuestionImportResult(int requested, int fetched, int inserted, int skipped) {
}
