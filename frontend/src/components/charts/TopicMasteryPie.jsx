import { Cell, Pie, PieChart, ResponsiveContainer, Tooltip } from 'recharts';

const COLORS = ['#34d399', '#f9a8d4', '#fbbf24', '#60a5fa', '#f87171', '#a78bfa'];

export default function TopicMasteryPie({ data }) {
  const chartData = (data || []).map((topic, index) => ({
    name: topic.topic,
    value: topic.mastery,
    fill: COLORS[index % COLORS.length],
  }));

  if (chartData.length === 0) {
    return <p>No topic mastery data available yet.</p>;
  }

  return (
    <div style={{ width: '100%', height: 220 }}>
      <ResponsiveContainer>
        <PieChart>
          <Pie data={chartData} dataKey="value" nameKey="name" cx="50%" cy="50%" outerRadius={80} label>
            {chartData.map((entry, index) => (
              <Cell key={`cell-${entry.name}`} fill={entry.fill} />
            ))}
          </Pie>
          <Tooltip formatter={(value) => `${Math.round(value * 100)}%`} />
        </PieChart>
      </ResponsiveContainer>
    </div>
  );
}
