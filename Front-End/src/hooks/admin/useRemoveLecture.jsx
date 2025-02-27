import { useMutation } from '@tanstack/react-query';
import { adminApi } from '../../api/adminApi';

function useRemoveLecture() {
  return useMutation({
    mutationFn: adminApi.removePermissionOfCompetition,
  });
}

export default useRemoveLecture;
