import { useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';

function useGetProposeSpecific() {
  return useQuery({
    queryKey: ['specificList-propose'],
    queryFn: () => specificApi.getAllPropose(),
  });
}

export default useGetProposeSpecific;
