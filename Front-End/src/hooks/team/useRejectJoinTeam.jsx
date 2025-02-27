import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { teamApi } from '../../api/teamApi';

function useRejectJoinTeam() {
  return useMutation({
    mutationFn: (data) => teamApi.rejectJoinTeam(data),
  });
}

export default useRejectJoinTeam;
