import { useMutation } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useApplyScore() {
  return useMutation({
    mutationFn: lectureApi.applyScore,
  });
}

export default useApplyScore;
