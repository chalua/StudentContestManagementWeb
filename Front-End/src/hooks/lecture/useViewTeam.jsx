import { useQuery } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useViewTeam(teamId) {
  return useQuery({
    queryKey: ['view-team', teamId],
    queryFn: () => lectureApi.getTeam(teamId),
  });
}

export default useViewTeam;
