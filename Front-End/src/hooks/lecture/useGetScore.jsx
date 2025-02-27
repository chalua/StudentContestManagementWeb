import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { lectureApi } from '../../api/lectureApi';

function useGetScore(maNhom, maVong) {
  return useQuery({
    queryKey: ['score', maNhom, maVong],
    queryFn: () => lectureApi.getScore(maNhom, maVong),
    enabled: !!maNhom && !!maVong,
  });
}

export default useGetScore;
