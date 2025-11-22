import { useEffect } from 'react';
import { Route, Routes, Link, useLocation } from 'react-router-dom';
import Dashboard from './components/Dashboard.jsx';
import DiagnosticTest from './components/sessions/DiagnosticTest.jsx';
import PracticeSession from './components/sessions/PracticeSession.jsx';

const navLinks = [
  { to: '/', label: 'Dashboard' },
  { to: '/diagnostic', label: 'Diagnostic Test' },
  { to: '/practice', label: 'Practice Session' }
];

export default function App() {
  const location = useLocation();

  useEffect(() => {
    document.title = 'HeyTwin Adaptive Learning';
  }, []);

  return (
    <div>
      <header style={{ background: '#020617', padding: '1rem 2rem', borderBottom: '1px solid rgba(255,255,255,0.08)' }}>
        <nav style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
          <span style={{ fontWeight: 700, fontSize: '1.1rem' }}>HeyTwin</span>
          {navLinks.map((link) => (
            <Link key={link.to} to={link.to} style={{ opacity: location.pathname === link.to ? 1 : 0.6 }}>
              {link.label}
            </Link>
          ))}
        </nav>
      </header>
      <main className="container">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/diagnostic" element={<DiagnosticTest />} />
          <Route path="/practice" element={<PracticeSession />} />
        </Routes>
      </main>
    </div>
  );
}
