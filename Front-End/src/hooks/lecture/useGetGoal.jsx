import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { lectureApi } from '../../api/lectureApi';

function useGetGoal(maNhom) {
  return useQuery({
    queryKey: ['goal', maNhom],
    queryFn: () => lectureApi.getGoal(maNhom),
    enabled: !!maNhom,
  });
}

export default useGetGoal;
