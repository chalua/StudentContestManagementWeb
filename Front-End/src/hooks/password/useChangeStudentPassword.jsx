import { useMutation } from '@tanstack/react-query';
import { passwordApi } from '../../api/passwordApi';

function useChangeStudentPassword() {
  return useMutation({
    mutationFn: (data) =>
      passwordApi.changeStudentPassword(data.username, data),
  });
}

export default useChangeStudentPassword;
