import { useMutation } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useDeleteCompetition() {
  return useMutation({
    mutationFn: competitionApi.delete,
  });
}

export default useDeleteCompetition;
