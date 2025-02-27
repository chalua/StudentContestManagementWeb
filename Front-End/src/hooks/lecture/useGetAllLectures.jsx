import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { lectureApi } from '../../api/lectureApi';

function useGetAllLectures() {
  return useQuery({
    queryKey: ['lectures'],
    queryFn: lectureApi.getAll,
  });
}

export default useGetAllLectures;
