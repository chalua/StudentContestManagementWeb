import { useMutation } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useCreateRound() {
  return useMutation({
    mutationFn: competitionApi.createRound,
  });
}

export default useCreateRound;
