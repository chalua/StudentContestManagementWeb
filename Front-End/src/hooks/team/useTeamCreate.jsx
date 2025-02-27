import { useMutation, useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';
import { teamApi } from '../../api/teamApi';

function useTeamCreate() {
  return useMutation({
    mutationFn: (data) => teamApi.createTeam(data),
  });
}

export default useTeamCreate;
