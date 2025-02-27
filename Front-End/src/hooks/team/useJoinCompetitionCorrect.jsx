import { useMutation, useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';
import { teamApi } from '../../api/teamApi';

function useJoinCompetitionCorrect() {
  return useMutation({
    mutationFn: (data) => teamApi.applyCompetitionCorrect(data),
  });
}

export default useJoinCompetitionCorrect;
