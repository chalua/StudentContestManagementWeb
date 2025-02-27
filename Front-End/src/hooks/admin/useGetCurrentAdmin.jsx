import { useQuery } from '@tanstack/react-query';
import { adminApi } from '../../api/adminApi';

function useGetCurrentAdmin() {
  return useQuery({
    queryKey: ['current-admin'],
    queryFn: () => adminApi.getCurrentUser(),
    retry: 0,
  });
}

export default useGetCurrentAdmin;
