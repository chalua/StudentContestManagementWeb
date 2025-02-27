import React, { useEffect } from 'react';
import useGetCurrentStudent from '../hooks/student/useGetCurrentStudent';
import { useLocation, useNavigate } from 'react-router-dom';
import useGetCurrentLecture from '../hooks/lecture/useGetCurrentLecture';

function LectureProtectedRoute({ children }) {
  const navigate = useNavigate();
  const location = useLocation();

  const { data, isError } = useGetCurrentLecture();

  const allowed = ['/', '/scientific', '/notifications'];

  useEffect(() => {
    if (isError) navigate('/lecture/sign-in');
    if (data && !data.data.maGiangVien) navigate('/lecture/sign-in');
  }, [isError, data]);

  return children;
}

export default LectureProtectedRoute;
