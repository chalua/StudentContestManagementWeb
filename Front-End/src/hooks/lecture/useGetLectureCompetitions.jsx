import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { lectureApi } from '../../api/lectureApi';

function useGetLectureCompetitions(lectureId) {
  return useQuery({
    queryKey: ['lecture-competitions', lectureId],
    queryFn: () => lectureApi.getCompetitions(lectureId),
  });
}

export default useGetLectureCompetitions;
