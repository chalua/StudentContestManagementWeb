import { useMutation } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useProposeSpecific() {
  return useMutation({
    mutationFn: lectureApi.proposeSpecific,
  });
}

export default useProposeSpecific;
