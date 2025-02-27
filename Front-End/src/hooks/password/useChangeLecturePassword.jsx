import { useMutation } from '@tanstack/react-query';
import { passwordApi } from '../../api/passwordApi';

function useChangeLecturePassword() {
  return useMutation({
    mutationFn: (data) =>
      passwordApi.changeLecturePassword(data.username, data),
  });
}

export default useChangeLecturePassword;
