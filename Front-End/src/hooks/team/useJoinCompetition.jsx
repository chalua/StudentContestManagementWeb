import { useMutation, useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';
import { teamApi } from '../../api/teamApi';

function useJoinCompetition() {
  return useMutation({
    mutationFn: (data) => teamApi.applyCompetition(data),
  });
}

export default useJoinCompetition;
