export default function Reminders({ data }) {
  const reminders = data?.reminders || [];
  if (reminders.length === 0) {
    return <p>All caught up! Next spaced review will be scheduled after your next session.</p>;
  }
  return (
    <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
      {reminders.map((reminder) => (
        <li key={`${reminder.topic}-${reminder.due}`} style={{ marginBottom: '0.75rem' }}>
          <div className="badge">Due {new Date(reminder.due).toLocaleDateString()}</div>
          <p style={{ marginTop: '0.2rem' }}>{reminder.topic}</p>
        </li>
      ))}
    </ul>
  );
}
