import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { specificApi } from '../../api/specificApi';

function useApproveSpecific() {
  return useMutation({
    mutationFn: (data) => specificApi.approve(data),
  });
}

export default useApproveSpecific;
