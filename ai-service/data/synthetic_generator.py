"""Generate and persist synthetic training data for the AI service."""
from pathlib import Path

from app.synthetic import generate_records

OUTPUT = Path(__file__).resolve().parent / "synthetic_training.json"


def main():
    records = generate_records(10000)
    payload = [record.model_dump() for record in records]
    OUTPUT.write_text(__import__("json").dumps(payload, indent=2))
    print(f"Saved {len(payload)} synthetic rows to {OUTPUT}")


if __name__ == "__main__":
    main()
