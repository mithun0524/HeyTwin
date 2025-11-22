import { Area, AreaChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';

export default function LearningCurve({ data }) {
  if (!data || data.length === 0) {
    return <p>No learning curve data available yet.</p>;
  }
  return (
    <div style={{ width: '100%', height: 220 }}>
      <ResponsiveContainer>
        <AreaChart data={data}>
          <defs>
            <linearGradient id="colorAccuracy" x1="0" y1="0" x2="0" y2="1">
              <stop offset="5%" stopColor="#c084fc" stopOpacity={0.8} />
              <stop offset="95%" stopColor="#c084fc" stopOpacity={0} />
            </linearGradient>
          </defs>
          <XAxis dataKey="timestamp" stroke="#94a3b8" />
          <YAxis domain={[0, 1]} tickFormatter={(value) => `${Math.round(value * 100)}%`} stroke="#94a3b8" />
          <Tooltip formatter={(value) => `${Math.round(value * 100)}%`} labelStyle={{ color: '#0f172a' }} />
          <Area type="monotone" dataKey="accuracy" stroke="#c084fc" fillOpacity={1} fill="url(#colorAccuracy)" />
        </AreaChart>
      </ResponsiveContainer>
    </div>
  );
}
