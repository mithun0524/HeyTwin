# Digital Twin Metrics & Formulas

This note documents how the rule-based digital twin engine in `backend/src/main/java/com/heytwin/digitaltwin` updates learner metrics so data science and product teams can align experiments with the current heuristics.

## Inputs per topic
Each recalculation pass aggregates the most recent batch of `Response` rows per `(student, topic)` and derives the following statistics:

| Symbol | Description | Source |
| --- | --- | --- |
| $a_{latest}$ | Accuracy of the latest batch | `correct_count / total` |
| $t_{avg}$ | Average time per response (seconds) | mean of `timeTakenSec`, default 45 | 
| $d_\Delta$ | Difficulty delta from recent questions | `question.difficulty.ordinal()/2` mean |
| $c_\Delta$ | Consistency delta | $1 - |a_{latest} - a_{previous}|$ |
| $f_\Delta$ | Forgetting delta | $\max(0, 1 - \frac{\text{age")} {7\text{ days}})$ using the freshest response timestamp |

## Exponential moving mix
For every tracked metric $m\in\{accuracy, avgTime, difficulty, consistency, forgetting\}$ we mix the persisted score ($m_{old}$) with the new deltas ($m_{new}$) using the helper below (weights mirror `RuleBasedTwinCalculator`):

$$ \text{mix}(m_{old}, m_{new}, w_{old}, w_{new}) = m_{old} \cdot w_{old} + m_{new} \cdot w_{new} $$

- Accuracy uses $(0.7, 0.3)$
- Average time uses $(0.6, 0.4)$
- Difficulty uses $(0.8, 0.2)$
- Consistency uses $(0.6, 0.4)$
- Forgetting uses $(0.5, 0.5)$

All outputs are clamped to $[0, 1]`.

## Mastery score
The mastery value stored per topic is a weighted sum of accuracy, difficulty tolerance, and time efficiency:

$$
\text{timeEfficiency} = 1 - \min\left( \frac{t_{avg}}{120}, 1 \right)
$$

$$
\text{mastery} = \text{clamp}\left(0.5 \cdot \text{accuracy} + 0.3 \cdot \text{difficultyScore} + 0.2 \cdot \text{timeEfficiency}\right)
$$

## Recommendations
The UI labels topics using simple thresholds on mastery:

- `mastery > 0.8` → "Maintain"
- `0.6 < mastery ≤ 0.8` → "Reinforce"
- otherwise → "Focus"

## Forgetting window rationale
`f_Δ` decays linearly over a rolling seven-day window using the freshest submission timestamp in the batch. This ensures dormant topics drift toward zero to trigger spaced-repetition reminders while active topics remain near one.

## Extending the model
1. Replace the weights or formulas above inside `RuleBasedTwinCalculator` if product requirements change.
2. Persist additional trends in `recentWindow` JSON if you need richer analytics.
3. Update `DigitalTwinSummary.TopicSnapshot.trend` logic to surface new insights in the dashboard.
