import { useMutation } from '@tanstack/react-query';
import { adminApi } from '../../api/adminApi';

function useAssignLecture() {
  return useMutation({
    mutationFn: adminApi.assignLecture,
  });
}

export default useAssignLecture;
