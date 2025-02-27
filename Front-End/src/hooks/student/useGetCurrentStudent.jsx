import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { studentApi } from '../../api/studentClient';

function useGetCurrentStudent() {
  return useQuery({
    queryKey: ['current-student'],
    queryFn: () => studentApi.getCurrentUser(),
    retry: 0,
  });
}

export default useGetCurrentStudent;
