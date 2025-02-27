import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { studentApi } from '../../api/studentClient';

function useGetHistory(studentId) {
  return useQuery({
    queryKey: ['history', studentId],
    queryFn: () => studentApi.getHistory(studentId),
    retry: 0,
  });
}

export default useGetHistory;
