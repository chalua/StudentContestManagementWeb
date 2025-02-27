import { useMutation } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useLectureReject() {
  return useMutation({
    mutationFn: (data) => lectureApi.reject(data),
    onSuccess: () => {},
  });
}

export default useLectureReject;
