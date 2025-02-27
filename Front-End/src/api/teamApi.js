import axiosClient from './axiosClient';

export const teamApi = {
  createTeam(data) {
    const url = '/nhom/taonhom';

    return axiosClient.post(url, data);
  },

  getTeam(studentId) {
    const url = `/nhom?masinhvien=${studentId}`;

    return axiosClient.get(url);
  },

  applyCompetitionCorrect(data) {
    const url = `cuocthi/dangkythamgia`;

    return axiosClient.post(url, data);
  },

  applyCompetition(data) {
    const url = `detai/dang-ky`;

    return axiosClient.post(url, data);
  },

  inviteStudent(data) {
    const url = `nhom/thanhvien`;

    return axiosClient.post(url, data);
  },

  seeInvitation(mssv) {
    const url = `nhom/xem-loi-moi?mssv=${mssv}`;

    return axiosClient.get(url, mssv);
  },

  approveJoinTeam(data) {
    const url = `nhom/chapnhanloimoi`;

    return axiosClient.post(url, data);
  },
  rejectJoinTeam(data) {
    const url = `nhom/tuchoiloimoi`;

    return axiosClient.post(url, data);
  },
  cancelTeam(data) {
    const url = `nhom/giai-tan`;

    return axiosClient.post(url, data);
  },
};
