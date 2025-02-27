import { useQuery } from '@tanstack/react-query';
import React from 'react';
import { teamApi } from '../../api/teamApi';

function useGetInvitation(studentId) {
  return useQuery({
    queryKey: ['invitation'],
    queryFn: () => teamApi.seeInvitation(studentId),
  });
}

export default useGetInvitation;
