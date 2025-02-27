import axiosClient from './axiosClient';

export const adminApi = {
  signIn(data) {
    const url = `/login`;

    return axiosClient.post(url, data);
  },
  getCurrentUser() {
    const url = '/admin/me';

    return axiosClient.get(url);
  },
  assignLecture(data) {
    const url = `cuocthi/${data.competitionId}/assign-giangvien/${data.lectureId}`;

    return axiosClient.post(url);
  },

  removePermissionOfCompetition(data) {
    const url = `cuocthi/${data.competitionId}/giangvien/${data.lectureId}`;

    return axiosClient.delete(url);
  },

  getPermissionOfCompetition(competitionId) {
    const url = `cuocthi/${competitionId}/giangviens`;

    return axiosClient.get(url);
  },

  analysis() {
    const url = `cuocthi/thong-ke`;

    return axiosClient.get(url);
  },

  analysisByCompetition(competitionId) {
    const url = `ketqua/thong-ke/${competitionId}`;

    return axiosClient.get(url);
  },
};
