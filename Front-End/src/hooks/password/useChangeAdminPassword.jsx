import { useMutation } from '@tanstack/react-query';
import { passwordApi } from '../../api/passwordApi';

function useChangeAdminPassword() {
  return useMutation({
    mutationFn: (data) => passwordApi.changeAdminPassword(data.username, data),
  });
}

export default useChangeAdminPassword;
