import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { specificApi } from '../../api/specificApi';

function useRejectSpecific() {
  return useMutation({
    mutationFn: (data) => specificApi.reject(data),
  });
}

export default useRejectSpecific;
