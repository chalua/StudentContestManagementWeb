import { useMutation } from '@tanstack/react-query';
import { uploadApi } from '../../api/uploadApi';

function useUploadStudent() {
  return useMutation({
    mutationFn: uploadApi.uploadStudent,
  });
}

export default useUploadStudent;
