import { useMutation } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useCreateCompetition() {
  return useMutation({
    mutationFn: competitionApi.create,
  });
}

export default useCreateCompetition;
