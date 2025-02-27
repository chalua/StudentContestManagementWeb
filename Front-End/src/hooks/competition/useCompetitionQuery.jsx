import { useQuery } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useCompetitionQuery() {
  return useQuery({
    queryKey: ['competitionList'],
    queryFn: () => competitionApi.getAll(),
  });
}

export default useCompetitionQuery;
