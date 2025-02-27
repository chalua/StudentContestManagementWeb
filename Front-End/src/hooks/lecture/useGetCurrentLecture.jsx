import { useQuery } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useGetCurrentLecture() {
  return useQuery({
    queryKey: ['current-lecture'],
    queryFn: () => lectureApi.getCurrentUser(),
    retry: 0,
  });
}

export default useGetCurrentLecture;
