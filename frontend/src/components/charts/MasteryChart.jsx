import { Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';

export default function MasteryChart({ data }) {
  if (!data || data.length === 0) {
    return <p>No mastery data available yet.</p>;
  }
  return (
    <div style={{ width: '100%', height: 220 }}>
      <ResponsiveContainer>
        <LineChart data={data}>
          <XAxis dataKey="timestamp" stroke="#94a3b8" />
          <YAxis domain={[0, 1]} tickFormatter={(value) => `${Math.round(value * 100)}%`} stroke="#94a3b8" />
          <Tooltip formatter={(value) => `${Math.round(value * 100)}%`} labelStyle={{ color: '#0f172a' }} />
          <Line type="monotone" dataKey="mastery" stroke="#38bdf8" strokeWidth={3} dot={false} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}
