import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { teamApi } from '../../api/teamApi';

function useApproveJoinTeam() {
  return useMutation({
    mutationFn: (data) => teamApi.approveJoinTeam(data),
  });
}

export default useApproveJoinTeam;
