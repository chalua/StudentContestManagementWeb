import { useQuery } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';

function useGetLectureTeams(competitionId) {
  return useQuery({
    queryKey: ['lecture-teams', competitionId],
    queryFn: () => lectureApi.getTeams(competitionId),
  });
}

export default useGetLectureTeams;
