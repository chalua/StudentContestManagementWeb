import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import useGetCurrentStudent from '../hooks/student/useGetCurrentStudent';

function ProtectedRoute({ children }) {
  const navigate = useNavigate();
  const location = useLocation();

  const { data, isError } = useGetCurrentStudent();

  const allowed = ['/notifications'];

  useEffect(() => {
    if (allowed.includes(location.pathname)) return;

    if (isError) navigate('/student/sign-in');
    if (data && !data.data.maSinhVien) navigate('/student/sign-in');
  }, [data, isError]);

  return children;
}

export default ProtectedRoute;
