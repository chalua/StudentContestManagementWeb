import { useMutation, useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';
import { teamApi } from '../../api/teamApi';

function useInviteStudent() {
  return useMutation({
    mutationFn: (data) => teamApi.inviteStudent(data),
  });
}

export default useInviteStudent;
