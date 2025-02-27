import { useMutation, useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';
import { teamApi } from '../../api/teamApi';

function useTeamInfoQuery(studentId) {
  return useQuery({
    queryKey: ['team-info'],
    queryFn: () => teamApi.getTeam(studentId),
  });
}

export default useTeamInfoQuery;
