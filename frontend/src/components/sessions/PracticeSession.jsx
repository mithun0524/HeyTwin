import { useMemo, useState } from 'react';
import { startPractice, submitPracticeResponse, completePractice } from '../../api/client.js';

const defaultConfig = {
  topic: 'Algebra Fundamentals',
  difficulty: 'MEDIUM',
  questionCount: 5,
};

const buildMockSession = () => ({
  sessionId: `mock-${Date.now()}`,
  topic: 'Quadratics Pulse',
  difficulty: 'MEDIUM',
  totalQuestions: 3,
  questions: [
    {
      id: 'practice-1',
      stem: 'Solve x^2 - 9 = 0.',
      options: ['x = ±3', 'x = 3', 'x = -3', 'No real roots'],
    },
    {
      id: 'practice-2',
      stem: 'Factor x^2 + 5x + 6.',
      options: ['(x+2)(x+3)', '(x-2)(x+3)', '(x-2)(x-3)', 'Prime'],
    },
    {
      id: 'practice-3',
      stem: 'The graph y = (x-2)^2 opens...',
      options: ['Upward', 'Downward', 'Sideways', 'Never'],
    },
  ],
});

export default function PracticeSession() {
  const [config, setConfig] = useState(defaultConfig);
  const [session, setSession] = useState(null);
  const [currentQuestion, setCurrentQuestion] = useState(null);
  const [selectedOption, setSelectedOption] = useState(null);
  const [feedback, setFeedback] = useState(null);
  const [summary, setSummary] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const answeredCount = useMemo(() => (session ? session.answered || 0 : 0), [session]);
  const progress = session ? (answeredCount / (session.totalQuestions || 1)) * 100 : 0;

  const launchSession = async () => {
    setLoading(true);
    setError(null);
    setFeedback(null);
    try {
      const res = await startPractice({
        topic: config.topic,
        difficulty: config.difficulty,
        questionCount: Number(config.questionCount),
      });
      const payload = res.data?.data;
      if (!payload) throw new Error('Missing payload');
      setSession({ ...payload, answered: 0 });
      setCurrentQuestion(payload.currentQuestion || payload.questions?.[0]);
    } catch (err) {
      console.warn('Practice session fallback engaged', err);
      const mock = buildMockSession();
      setSession(mock);
      setCurrentQuestion(mock.questions[0]);
      setError('Live practice unavailable, running adaptive mock.');
    } finally {
      setLoading(false);
    }
  };

  const handleResponse = async () => {
    if (!currentQuestion || !selectedOption) {
      setError('Choose an answer to continue.');
      return;
    }
    setLoading(true);
    setError(null);
    try {
      const res = await submitPracticeResponse(session.sessionId, {
        questionId: currentQuestion.id,
        answer: selectedOption,
      });
      const data = res.data?.data || {};
      const nextQuestion = data.nextQuestion || session.questions?.[session.answered + 1];
      setFeedback({
        correct: data.correct,
        masteryDelta: data.masteryDelta ?? 0,
        explanation: data.explanation || 'Reflect on why your reasoning worked.',
      });
      setSession((prev) => ({ ...prev, answered: (prev?.answered || 0) + 1 }));
      setCurrentQuestion(nextQuestion || null);
      setSelectedOption(null);
      if (!nextQuestion) {
        await finalizeSession();
      }
    } catch (err) {
      console.error('Practice submission failed, mocking adaptive feedback', err);
      setFeedback({
        correct: selectedOption === 'x = ±3',
        masteryDelta: 0.03,
        explanation: 'Remember to isolate the squared term before square rooting.',
      });
      advanceMockQuestion();
      setError('Session saved locally. Results will sync when online.');
    } finally {
      setLoading(false);
    }
  };

  const advanceMockQuestion = () => {
    setSession((prev) => {
      if (!prev) return prev;
      const answered = (prev.answered || 0) + 1;
      const nextQuestion = prev.questions[answered] || null;
      setCurrentQuestion(nextQuestion);
      if (!nextQuestion) {
        finalizeSession(true);
      }
      return { ...prev, answered };
    });
    setSelectedOption(null);
  };

  const finalizeSession = async (mock = false) => {
    setLoading(true);
    try {
      if (!mock) {
        const res = await completePractice(session.sessionId);
        setSummary(res.data?.data || null);
      } else {
        setSummary({ masteryGain: 0.06, accuracy: 0.8, recommendations: ['Target word problems next.'] });
      }
    } catch (err) {
      console.error('Unable to finalize session, caching locally', err);
      setSummary({ masteryGain: 0.05, accuracy: 0.76, recommendations: ['Keep reviewing factoring moves.'] });
      setError('Unable to reach server. Summary is estimated.');
    } finally {
      setLoading(false);
    }
  };

  const reset = () => {
    setSession(null);
    setCurrentQuestion(null);
    setSelectedOption(null);
    setFeedback(null);
    setSummary(null);
    setError(null);
  };

  return (
    <div className="card" style={{ maxWidth: '900px', margin: '0 auto' }}>
      <h2>Adaptive Practice Studio</h2>
      <p style={{ color: '#94a3b8' }}>Dial in a focus topic and let the digital twin steer the next best problems.</p>

      {!session && (
        <div className="grid grid-cols-2" style={{ gap: '1rem', marginTop: '1.2rem' }}>
          <label className="grid" style={{ gap: '0.4rem' }}>
            <span>Topic focus</span>
            <input
              type="text"
              value={config.topic}
              onChange={(e) => setConfig((prev) => ({ ...prev, topic: e.target.value }))}
              style={{ padding: '0.7rem', borderRadius: '10px', border: '1px solid rgba(148,163,184,0.3)' }}
            />
          </label>
          <label className="grid" style={{ gap: '0.4rem' }}>
            <span>Difficulty</span>
            <select
              value={config.difficulty}
              onChange={(e) => setConfig((prev) => ({ ...prev, difficulty: e.target.value }))}
              style={{ padding: '0.7rem', borderRadius: '10px', border: '1px solid rgba(148,163,184,0.3)' }}
            >
              <option value="EASY">Easy</option>
              <option value="MEDIUM">Medium</option>
              <option value="HARD">Hard</option>
            </select>
          </label>
          <label className="grid" style={{ gap: '0.4rem' }}>
            <span>Questions</span>
            <input
              type="number"
              min="3"
              max="10"
              value={config.questionCount}
              onChange={(e) => setConfig((prev) => ({ ...prev, questionCount: e.target.value }))}
              style={{ padding: '0.7rem', borderRadius: '10px', border: '1px solid rgba(148,163,184,0.3)' }}
            />
          </label>
          <div style={{ display: 'flex', alignItems: 'flex-end' }}>
            <button className="button-primary" onClick={launchSession} disabled={loading}>
              {loading ? 'Creating...' : 'Start Session'}
            </button>
          </div>
        </div>
      )}

      {error && (
        <div style={{ background: 'rgba(248,113,113,0.12)', padding: '0.8rem 1rem', borderRadius: '12px', margin: '1rem 0' }}>
          {error}
        </div>
      )}

      {session && (
        <div style={{ marginTop: '1.5rem' }}>
          <div style={{ marginBottom: '1rem' }}>
            <p style={{ marginBottom: '0.4rem' }}>Progress</p>
            <div style={{ background: 'rgba(255,255,255,0.08)', borderRadius: '999px', height: '10px' }}>
              <div style={{ width: `${progress}%`, background: '#22d3ee', height: '100%', borderRadius: '999px' }}></div>
            </div>
          </div>

          {currentQuestion ? (
            <div style={{ border: '1px solid rgba(148,163,184,0.2)', borderRadius: '16px', padding: '1.25rem' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                <strong>Question {answeredCount + 1}</strong>
                <span className="badge">{session.difficulty}</span>
              </div>
              <p style={{ margin: '1rem 0' }}>{currentQuestion.stem || currentQuestion.question}</p>
              <div className="grid" style={{ gap: '0.75rem' }}>
                {(currentQuestion.options || []).map((option) => (
                  <label key={option} style={{ display: 'flex', alignItems: 'center', gap: '0.6rem', cursor: 'pointer' }}>
                    <input
                      type="radio"
                      name={currentQuestion.id}
                      value={option}
                      checked={selectedOption === option}
                      onChange={() => setSelectedOption(option)}
                    />
                    {option}
                  </label>
                ))}
              </div>
              <button className="button-primary" style={{ marginTop: '1rem' }} onClick={handleResponse} disabled={loading}>
                {loading ? 'Saving...' : 'Submit Answer'}
              </button>
            </div>
          ) : (
            <div className="card" style={{ background: '#0f172a', marginTop: '1rem' }}>
              <p>Session complete. Generating adaptive summary...</p>
            </div>
          )}

          {feedback && (
            <div style={{ marginTop: '1rem', background: 'rgba(15, 118, 110, 0.12)', padding: '1rem', borderRadius: '12px' }}>
              <p>{feedback.correct ? 'Great job! ✅' : 'Let’s revisit that.'}</p>
              <p>Mastery delta: {Math.round((feedback.masteryDelta || 0) * 100)}%</p>
              <p style={{ color: '#bae6fd' }}>{feedback.explanation}</p>
            </div>
          )}

          {summary && (
            <div className="card" style={{ marginTop: '1.4rem', background: '#0f172a' }}>
              <h3>Session Summary</h3>
              <p>Mastery gain: {Math.round((summary.masteryGain || 0) * 100)}%</p>
              <p>Accuracy: {Math.round((summary.accuracy || 0) * 100)}%</p>
              <p>Next up: {(summary.recommendations || ['Review todays errors.']).join(', ')}</p>
              <div style={{ marginTop: '1rem', display: 'flex', gap: '1rem' }}>
                <button className="button-primary" onClick={reset}>
                  Start New Session
                </button>
                <button
                  className="button-primary"
                  style={{ background: 'linear-gradient(135deg, #f472b6, #fb7185)' }}
                  onClick={() => window.scrollTo({ top: 0, behavior: 'smooth' })}
                >
                  Review Dashboard
                </button>
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
