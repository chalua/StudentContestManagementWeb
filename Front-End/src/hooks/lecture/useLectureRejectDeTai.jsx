import { useMutation } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useLectureRejectDeTai() {
  return useMutation({
    mutationFn: (data) => lectureApi.rejectNhomDeTai(data),
    onSuccess: () => {},
  });
}

export default useLectureRejectDeTai;
