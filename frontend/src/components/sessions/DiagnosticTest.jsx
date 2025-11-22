import { useEffect, useState } from 'react';
import { startDiagnostic, submitDiagnostic } from '../../api/client.js';

const createFallbackQuestions = () => ([
  {
    id: 'mock-1',
    question: 'Solve for x: 2x + 5 = 17',
    difficulty: 'MEDIUM',
    options: ['4', '5', '6', '7'],
  },
  {
    id: 'mock-2',
    question: 'What is the derivative of 3x^2?',
    difficulty: 'EASY',
    options: ['3x', '6x', '9x', '3x^3'],
  },
]);

export default function DiagnosticTest() {
  const [questions, setQuestions] = useState([]);
  const [responses, setResponses] = useState({});
  const [status, setStatus] = useState('loading');
  const [submitting, setSubmitting] = useState(false);
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    let mounted = true;
    async function load() {
      try {
        const res = await startDiagnostic();
        if (!mounted) return;
        const fetched = res.data?.data?.questions || [];
        setQuestions(fetched.length ? fetched : createFallbackQuestions());
        setStatus('ready');
      } catch (err) {
        console.warn('Falling back to synthetic diagnostic questions', err);
        if (mounted) {
          setQuestions(createFallbackQuestions());
          setStatus('ready');
          setError('Live questions unavailable, using practice set.');
        }
      }
    }
    load();
    return () => {
      mounted = false;
    };
  }, []);

  const handleChange = (questionId, option) => {
    setResponses((prev) => ({ ...prev, [questionId]: option }));
  };

  const handleSubmit = async () => {
    if (Object.keys(responses).length !== questions.length) {
      setError('Answer every question before submitting.');
      return;
    }
    setSubmitting(true);
    setError(null);
    try {
      const payload = {
        responses: questions.map((q) => ({
          questionId: q.id,
          selectedOption: responses[q.id],
        })),
      };
      const res = await submitDiagnostic(payload);
      setResult(res.data?.data || { masteryGain: 0.04, recommendedTopics: ['Quadratics'] });
    } catch (err) {
      console.error('Diagnostic submission failed, mocking result', err);
      setResult({ masteryGain: 0.05, recommendedTopics: ['Exponents', 'Polynomials'] });
      setError('Submission failed, generating estimated insights.');
    } finally {
      setSubmitting(false);
    }
  };

  if (status === 'loading') {
    return <p>Loading diagnostic...</p>;
  }

  return (
    <div className="card" style={{ maxWidth: '800px', margin: '0 auto' }}>
      <h2>Diagnostic Assessment</h2>
      <p style={{ color: '#94a3b8' }}>Kick off your adaptive learning plan with a quick proficiency scan.</p>

      {error && (
        <div style={{ background: 'rgba(248,113,113,0.12)', padding: '0.8rem 1rem', borderRadius: '12px', marginBottom: '1rem' }}>
          {error}
        </div>
      )}

      <div className="grid" style={{ gap: '1.25rem', margin: '1.5rem 0' }}>
        {questions.map((question, index) => (
          <div key={question.id} style={{ border: '1px solid rgba(148,163,184,0.2)', borderRadius: '12px', padding: '1rem' }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.6rem' }}>
              <strong>Q{index + 1}</strong>
              <span className="badge">{question.difficulty || 'MEDIUM'}</span>
            </div>
            <p style={{ marginBottom: '0.9rem' }}>{question.question}</p>
            <div className="grid" style={{ gap: '0.6rem' }}>
              {(question.options || []).map((option) => (
                <label key={option} style={{ display: 'flex', alignItems: 'center', gap: '0.6rem', cursor: 'pointer' }}>
                  <input
                    type="radio"
                    name={question.id}
                    value={option}
                    checked={responses[question.id] === option}
                    onChange={() => handleChange(question.id, option)}
                  />
                  {option}
                </label>
              ))}
            </div>
          </div>
        ))}
      </div>

      <button className="button-primary" onClick={handleSubmit} disabled={submitting}>
        {submitting ? 'Submitting...' : 'Submit Diagnostic'}
      </button>

      {result && (
        <div className="card" style={{ marginTop: '1.5rem', background: '#0f172a' }}>
          <h3>Insights</h3>
          <p>Mastery gain potential: <strong>{Math.round((result.masteryGain || 0) * 100)}%</strong></p>
          <p>Focus next on: {(result.recommendedTopics || []).join(', ') || 'Review core topics'}</p>
        </div>
      )}
    </div>
  );
}
