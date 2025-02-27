import { useQuery } from '@tanstack/react-query';
import { adminApi } from '../../api/adminApi';

function useAnalysisByCompetition(competitionId) {
  return useQuery({
    queryKey: ['analysis', competitionId],
    queryFn: () => adminApi.analysisByCompetition(competitionId),
    enabled: !!competitionId,
  });
}

export default useAnalysisByCompetition;
