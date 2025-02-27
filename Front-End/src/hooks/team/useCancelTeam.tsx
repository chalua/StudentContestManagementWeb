import { useMutation } from '@tanstack/react-query';
import React from 'react';
import { teamApi } from '../../api/teamApi';

function useCancelTeam() {
  return useMutation({
    mutationFn: (data) => teamApi.cancelTeam(data),
  });
}

export default useCancelTeam;
