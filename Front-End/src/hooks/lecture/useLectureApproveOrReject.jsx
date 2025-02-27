import { useMutation } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useLectureApproveOrReject() {
  return useMutation({
    mutationFn: (data) => lectureApi.approveOrReject(data),
    onSuccess: () => {},
  });
}

export default useLectureApproveOrReject;
