import { useMutation } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useUpdateCompetition() {
  return useMutation({
    mutationFn: competitionApi.update,
  });
}

export default useUpdateCompetition;
