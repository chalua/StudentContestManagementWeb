import { useQuery } from '@tanstack/react-query';
import { adminApi } from '../../api/adminApi';

function useAnalysis() {
  return useQuery({
    queryKey: ['analysis'],
    queryFn: () => adminApi.analysis(),
  });
}

export default useAnalysis;
