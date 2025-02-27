import { useQuery } from '@tanstack/react-query';
import { lectureApi } from '../../api/lectureApi';
import { competitionApi } from '../../api/competitionApi';

function useGetLectureTeamsDeTai(maDeTai) {
  return useQuery({
    queryKey: ['lecture-teams-detai', maDeTai],
    queryFn: () => competitionApi.getNhomThamGiaDeTai(maDeTai),
  });
}

export default useGetLectureTeamsDeTai;
