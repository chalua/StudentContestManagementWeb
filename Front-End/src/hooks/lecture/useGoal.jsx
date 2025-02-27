import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { lectureApi } from '../../api/lectureApi';

function useGoal() {
  return useMutation({
    mutationFn: lectureApi.goal,
  });
}

export default useGoal;
