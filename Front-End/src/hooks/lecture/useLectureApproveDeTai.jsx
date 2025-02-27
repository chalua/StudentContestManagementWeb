import { useMutation } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useLectureApproveDeTai() {
  return useMutation({
    mutationFn: (data) => lectureApi.approveNhomDeTai(data),
    onSuccess: () => {},
  });
}

export default useLectureApproveDeTai;
