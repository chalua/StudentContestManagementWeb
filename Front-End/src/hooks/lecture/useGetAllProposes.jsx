import { useQuery } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useGetAllProposes() {
  return useQuery({
    queryKey: ['proposes'],
    queryFn: () => lectureApi.getAllProposes(),
  });
}

export default useGetAllProposes;
