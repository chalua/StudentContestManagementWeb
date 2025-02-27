import { useQuery } from '@tanstack/react-query';
import { studentApi } from '../../api/studentClient';

function useGetCurrentTeam(studentId) {
  return useQuery({
    queryKey: ['team', studentId],
    queryFn: () => studentApi.getCurrentTeam(studentId),
  });
}

export default useGetCurrentTeam;
