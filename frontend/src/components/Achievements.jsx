export default function Achievements({ data }) {
  if (!data) {
    return <p>No achievements yet.</p>;
  }

  return (
    <div className="grid" style={{ gap: '1rem' }}>
      <div>
        <h4>Earned</h4>
        {(data.earned || []).length === 0 && <p>Keep learning to unlock rewards.</p>}
        {(data.earned || []).map((achievement) => (
          <div key={achievement.code} className="badge" style={{ display: 'inline-block', marginRight: '0.5rem' }}>
            {achievement.code} · {new Date(achievement.awardedAt).toLocaleDateString()}
          </div>
        ))}
      </div>
      <div>
        <h4>In Progress</h4>
        {(data.available || []).length === 0 && <p>Start a practice streak to see progress.</p>}
        {(data.available || []).map((achievement) => (
          <div key={achievement.code} style={{ marginBottom: '0.4rem' }}>
            <span>{achievement.code}</span>
            <div style={{ background: 'rgba(255,255,255,0.1)', borderRadius: '999px', height: '8px', marginTop: '0.3rem' }}>
              <div style={{ background: '#38bdf8', width: `${Math.round(achievement.progress * 100)}%`, height: '100%', borderRadius: '999px' }}></div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
