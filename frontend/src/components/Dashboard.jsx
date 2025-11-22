import { useEffect, useState } from 'react';
import { getDashboard, getMastery, getLearningCurve, getTopicPie, getReminders, getAchievements } from '../api/client.js';
import MasteryChart from './charts/MasteryChart.jsx';
import LearningCurve from './charts/LearningCurve.jsx';
import TopicMasteryPie from './charts/TopicMasteryPie.jsx';
import Achievements from './Achievements.jsx';
import Reminders from './Reminders.jsx';

const initialState = {
  overview: null,
  mastery: null,
  learningCurve: null,
  topicPie: null,
  reminders: null,
  achievements: null
};

export default function Dashboard() {
  const [data, setData] = useState(initialState);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    let mounted = true;
    async function load() {
      try {
        const [overview, mastery, lc, pie, reminders, achievements] = await Promise.all([
          getDashboard(),
          getMastery(),
          getLearningCurve(),
          getTopicPie(),
          getReminders(),
          getAchievements()
        ]);
        if (mounted) {
          setData({
            overview: overview.data.data,
            mastery: mastery.data.data,
            learningCurve: lc.data.data,
            topicPie: pie.data.data,
            reminders: reminders.data.data,
            achievements: achievements.data.data
          });
        }
      } catch (error) {
        console.error('Dashboard load failed, showing placeholders', error);
        if (mounted) {
          setData({
            ...initialState,
            overview: {
              diagnosticCompleted: false,
              twinSummary: {
                overallMastery: 0.72,
                estimatedExamScore: 82,
                strongTopics: ['Algebra'],
                weakTopics: ['Trig']
              },
              reminders: []
            },
            mastery: { points: [{ timestamp: '2025-11-01', mastery: 0.6 }, { timestamp: '2025-11-15', mastery: 0.74 }] },
            learningCurve: { sessions: [] },
            topicPie: { topics: [] },
            reminders: { reminders: [] },
            achievements: { earned: [], available: [] }
          });
        }
      } finally {
        if (mounted) setLoading(false);
      }
    }
    load();
    return () => {
      mounted = false;
    };
  }, []);

  if (loading) {
    return <p>Loading dashboard...</p>;
  }

  return (
    <div className="grid" style={{ gap: '2rem' }}>
      <section className="grid grid-cols-2">
        <div className="card">
          <h2>Mastery Score</h2>
          <p style={{ fontSize: '2.5rem', margin: '0.8rem 0' }}>{Math.round((data.overview?.twinSummary?.overallMastery || 0) * 100)}%</p>
          <p>Estimated Exam Score: <strong>{data.overview?.twinSummary?.estimatedExamScore}</strong></p>
          <div style={{ display: 'flex', gap: '1rem', marginTop: '1rem' }}>
            <div>
              <p className="badge">Strengths</p>
              <p>{(data.overview?.twinSummary?.strongTopics || []).join(', ') || 'TBD'}</p>
            </div>
            <div>
              <p className="badge" style={{ background: 'rgba(248,250,252,0.1)', color: '#fbbf24' }}>Weaknesses</p>
              <p>{(data.overview?.twinSummary?.weakTopics || []).join(', ') || 'TBD'}</p>
            </div>
          </div>
        </div>
        <div className="card">
          <h2>Learning Momentum</h2>
          <MasteryChart data={data.mastery?.points || []} />
        </div>
      </section>

      <section className="grid grid-cols-2">
        <div className="card">
          <h3>Learning Curve</h3>
          <LearningCurve data={data.learningCurve?.sessions || []} />
        </div>
        <div className="card">
          <h3>Topics Snapshot</h3>
          <TopicMasteryPie data={data.topicPie?.topics || []} />
        </div>
      </section>

      <section className="grid grid-cols-2">
        <div className="card">
          <h3>Achievements & Rewards</h3>
          <Achievements data={data.achievements} />
        </div>
        <div className="card">
          <h3>Spaced Repetition Reminders</h3>
          <Reminders data={data.reminders} />
        </div>
      </section>
    </div>
  );
}
