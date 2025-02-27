import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AdminSignIn from './component/auth/AdminSignIn.jsx';
import CompetitionRound from './component/competition/CompetitionRound.jsx';
import AppLayout from './component/layout/AppLayout.jsx';
import { CompetitionContextProvider } from './globalstate/CompetitionContext.jsx';
import './index.css';
import AnalysisPage from './pages/analysis/AnalysisPage.jsx';
import LectureSignInPage from './pages/auth/LectureSignInPage.jsx';
import SignInPage from './pages/auth/SignInPage.jsx';
import CompetitionPage from './pages/competition/CompetitionPage.jsx';
import ManageCompetitionPage from './pages/competition/ManageCompetitionPage.jsx';
import MyCompetitionPage from './pages/lecture/MyCompetitionPage.jsx';
import ProposeScientificPage from './pages/lecture/ProposeScientificPage.jsx';
import StudentViewTeamPage from './pages/lecture/StudentViewTeamPage.jsx';
import ManageNotificationPage from './pages/notification/ManageNotification.jsx';
import NotificationDetailPage from './pages/notification/NotificationDetailPage.jsx';
import NotificationPage from './pages/notification/NotificationPage.jsx';
import ChangePasswordAdminPage from './pages/password/ChangePasswordAdminPage.jsx';
import ChangePasswordLecturePage from './pages/password/ChangePasswordLecturePage.jsx';
import PermissionsPage from './pages/permissions/PermissionsPage.jsx';
import RolesPage from './pages/permissions/RolesPage.jsx';
import ScientificPage from './pages/scientific/ScientificPage.jsx';
import ScoreManagementPage from './pages/score/ScoreManagementPage.jsx';
import HistoryCompetitionNCKHPage from './pages/student/HistoryCompetitionNCKHPage.jsx';
import HistoryCompetitionPage from './pages/student/HistoryCompetitionPage.jsx';
import StudentInfoPage from './pages/student/StudentInfoPage.jsx';
import StudentTeamPage from './pages/student/StudentTeamPage.jsx';
import TeamInvitePage from './pages/student/TeamInvitePage.jsx';
import AdminProtectedRoute from './routes/AdminProtectedRoute.jsx';
import LectureProtectedRoute from './routes/LectureProtectedRoute.jsx';
import ProtectedRoute from './routes/ProtectedRoute.jsx';
import ChangePasswordStudentPage from './pages/password/ChangePasswordStudentPage.jsx';
import ExcelPage from './pages/excel/ExcelPage.jsx';

const router = createBrowserRouter([
  {
    path: '/student/sign-in',
    element: <SignInPage />,
  },
  {
    path: '/lecture/sign-in',
    element: <LectureSignInPage />,
  },
  {
    path: '/admin/sign-in',
    element: <AdminSignIn />,
  },
  {
    path: '/',
    element: (
      <ProtectedRoute>
        <AppLayout />
      </ProtectedRoute>
    ),
    children: [
      {
        index: true,
        element: <CompetitionPage />,
      },
      {
        path: 'scientific',
        element: <ScientificPage />,
      },
      {
        path: 'notifications',
        element: <NotificationPage />,
      },
      {
        path: 'notifications/:id',
        element: <NotificationDetailPage />,
      },
    ],
  },
  {
    path: '/student',
    element: (
      <ProtectedRoute>
        <AppLayout />
      </ProtectedRoute>
    ),
    children: [
      {
        path: 'info',
        element: <StudentInfoPage />,
      },
      {
        path: 'team',
        element: <StudentTeamPage />,
      },
      {
        path: 'history-competition',
        element: <HistoryCompetitionPage />,
      },
      {
        path: 'history-competition-scientific',
        element: <HistoryCompetitionNCKHPage />,
      },
      {
        path: 'invite',
        element: <TeamInvitePage />,
      },
      {
        path: 'change-password',
        element: <ChangePasswordStudentPage />,
      },
    ],
  },
  {
    path: '/lecture',
    element: (
      <LectureProtectedRoute>
        <AppLayout />
      </LectureProtectedRoute>
    ),
    children: [
      {
        path: 'my-competitions',
        element: <MyCompetitionPage />,
      },
      {
        path: 'my-propose-scientific',
        element: <ProposeScientificPage />,
      },
      {
        path: 'team/:teamId',
        element: <StudentViewTeamPage />,
      },
      {
        path: 'analysis',
        element: <AnalysisPage />,
      },
      {
        path: 'change-password',
        element: <ChangePasswordLecturePage />,
      },
    ],
  },
  {
    path: '/admin',
    element: (
      <AdminProtectedRoute>
        <AppLayout />
      </AdminProtectedRoute>
    ),
    children: [
      {
        path: 'manage-competition-project',
        element: <ManageCompetitionPage />,
      },
      {
        path: 'manage-competition',
        element: <ManageCompetitionPage />,
      },
      {
        path: 'manage-competition/:id/round/',
        element: <CompetitionRound />,
      },
      {
        path: 'manage-scientific',
        element: <ManageCompetitionPage />,
      },
      {
        path: 'manage-notification',
        element: <ManageNotificationPage />,
      },
      {
        path: 'analysis',
        element: <AnalysisPage />,
      },
      {
        path: 'change-password',
        element: <ChangePasswordAdminPage />,
      },
      {
        path: 'permissions',
        element: <PermissionsPage />,
      },
      {
        path: 'roles',
        element: <RolesPage />,
      },
      {
        path: 'upload',
        element: <ExcelPage />,
      },
    ],
  },
  {
    path: '/lecturer',
    element: <AppLayout />,
    children: [
      {
        path: 'score',
        element: <ScoreManagementPage />,
      },
    ],
  },
]);

const queryClient = new QueryClient({
  // queryCache: new QueryCache({
  //   onSuccess,
  //   onError,
  // }),
  // mutationCache: new MutationCache({
  //   onError: () => toast.error('wrong'),
  // }),
});

createRoot(document.getElementById('root')).render(
  <QueryClientProvider client={queryClient}>
    <CompetitionContextProvider>
      <RouterProvider router={router} />
    </CompetitionContextProvider>

    <ReactQueryDevtools initialIsOpen={false} />
    <ToastContainer />
  </QueryClientProvider>
);
