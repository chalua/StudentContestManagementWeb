import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import useGetCurrentLecture from '../hooks/lecture/useGetCurrentLecture';
import useGetCurrentAdmin from '../hooks/admin/useGetCurrentAdmin';

function AdminProtectedRoute({ children }) {
  const navigate = useNavigate();

  const { data, isError } = useGetCurrentAdmin();

  useEffect(() => {
    if (isError) navigate('/admin/sign-in');
    if (data && !data.data.userName) navigate('/admin/sign-in'); // TODO: fix userName to role
  }, [isError, data]);

  return children;
}

export default AdminProtectedRoute;
