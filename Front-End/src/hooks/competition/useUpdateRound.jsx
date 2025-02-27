import { useMutation } from '@tanstack/react-query';
import { competitionApi } from '../../api/competitionApi';

function useUpdateRound() {
  return useMutation({
    mutationFn: competitionApi.updateRound,
  });
}

export default useUpdateRound;
