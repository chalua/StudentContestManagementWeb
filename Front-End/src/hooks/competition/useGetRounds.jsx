import { useQuery } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useGetRounds(competitionId) {
  return useQuery({
    queryKey: ['rounds', competitionId],
    queryFn: () => competitionApi.getRounds(competitionId),
    enabled: !!competitionId,
  });
}

export default useGetRounds;
