import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { studentApi } from '../../api/studentClient';

function useGetHistoryNCKH(studentId) {
  return useQuery({
    queryKey: ['history-nckh', studentId],
    queryFn: () => studentApi.getHistoryNCKH(studentId),
    retry: 0,
  });
}

export default useGetHistoryNCKH;
