import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { lectureApi } from '../../api/lectureApi';

function useGetGiaiThuong() {
  return useQuery({
    queryKey: ['all-giai-thuong'],
    queryFn: () => lectureApi.getAllGiaiThuong(),
  });
}

export default useGetGiaiThuong;
