import { useQuery } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useGetProposes(lectureId) {
  return useQuery({
    queryKey: ['proposes', lectureId],
    queryFn: () => lectureApi.getProposes(lectureId),
  });
}

export default useGetProposes;
