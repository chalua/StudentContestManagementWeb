import { useQuery } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useCompetitionByTypeQuery(typeId) {
  return useQuery({
    queryKey: ['competitionList', typeId],
    queryFn: () => competitionApi.getAllBtType(typeId),
  });
}

export default useCompetitionByTypeQuery;
