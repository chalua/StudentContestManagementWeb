import { useMutation } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useDeleteRound() {
  return useMutation({
    mutationFn: competitionApi.deleteRound,
  });
}

export default useDeleteRound;
