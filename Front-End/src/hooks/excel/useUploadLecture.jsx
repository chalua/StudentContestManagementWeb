import { useMutation } from '@tanstack/react-query';
import { uploadApi } from '../../api/uploadApi';

function useUploadLecture() {
  return useMutation({
    mutationFn: uploadApi.uploadLecture,
  });
}

export default useUploadLecture;
