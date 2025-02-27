import { useQuery } from '@tanstack/react-query';
import { adminApi } from '../../api/adminApi';

function useGetPermissionOfCompetition(competitionId) {
  return useQuery({
    queryKey: ['perms-competitions', competitionId],
    queryFn: () => adminApi.getPermissionOfCompetition(competitionId),
  });
}

export default useGetPermissionOfCompetition;
