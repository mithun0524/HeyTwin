import axios from 'axios';

const client = axios.create({
  baseURL: '/api'
});

client.interceptors.request.use((config) => {
  const token = localStorage.getItem('heytwin_token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const getDashboard = () => client.get('/dashboard/overview');
export const getTwinSummary = () => client.get('/digital-twin/summary');
export const getMastery = () => client.get('/analytics/mastery');
export const getTopicPie = () => client.get('/analytics/topic-pie');
export const getLearningCurve = () => client.get('/analytics/learning-curve');
export const getAchievements = () => client.get('/analytics/achievements');
export const getReminders = () => client.get('/analytics/spaced-repetition');

export const startDiagnostic = () => client.get('/diagnostic/questions');
export const submitDiagnostic = (payload) => client.post('/diagnostic/responses', payload);

export const startPractice = (payload) => client.post('/practice/session/start', payload);
export const submitPracticeResponse = (sessionId, payload) => client.post(`/practice/session/${sessionId}/response`, payload);
export const completePractice = (sessionId) => client.post(`/practice/session/${sessionId}/complete`);

export default client;
