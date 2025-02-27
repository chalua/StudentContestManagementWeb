import { useQuery } from '@tanstack/react-query';
import { specificApi } from '../../api/specificApi';

function useSpecificQuery() {
  return useQuery({
    queryKey: ['specificList'],
    queryFn: () => specificApi.getAll(),
  });
}

export default useSpecificQuery;
