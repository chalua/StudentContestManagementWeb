import axiosClient from './axiosClient';

export const lectureApi = {
  signIn(data) {
    const url = `/login`;

    return axiosClient.post(url, data);
  },

  getCurrentUser() {
    const url = '/giangvien/me';

    return axiosClient.get(url);
  },

  getAll() {
    const url = `/giangvien`;

    return axiosClient.get(url);
  },
  getCompetitions(lectureId) {
    const url = `/giangvien/${lectureId}/cuocthi`;

    return axiosClient.get(url);
  },
  getTeams(competitionId) {
    const url = `/giangvien/nhom/${competitionId}`;

    return axiosClient.get(url);
  },
  approveOrReject(data) {
    const url = `/giangvien/duyet-nhom`;

    return axiosClient.put(url, data);
  },

  approveNhomDeTai(data) {
    const url = `/detai/duyet-nhom`;

    return axiosClient.post(url, data);
  },

  rejectNhomDeTai(data) {
    const url = `/detai/tu-choi-nhom`;

    return axiosClient.post(url, data);
  },

  reject(data) {
    const url = `/giangvien/tu-choi-nhom`;

    return axiosClient.put(url, data);
  },

  proposeSpecific(data) {
    const url = `/detai`;

    return axiosClient.post(url, data);
  },

  getProposes(lectureId) {
    const url = `/detai/de-xuat?maGV=${lectureId}`;

    return axiosClient.get(url);
  },
  getAllProposes() {
    const url = `/detai/de-xuat/all`;

    return axiosClient.get(url);
  },

  getTeam(teamId) {
    const url = `/nhom/get-nhom/${teamId}`;

    return axiosClient.get(url);
  },

  applyScore(data) {
    const url = `/ketqua`;

    return axiosClient.post(url, data);
  },
  getScore(maNhom, maVong) {
    const url = `/ketqua/thongtinketqua?maNhom=${maNhom}&maVong=${maVong}`;

    return axiosClient.get(url);
  },
  goal(data) {
    const url = `/ketqua/trao-giai`;

    return axiosClient.post(url, data);
  },
  getAllGiaiThuong() {
    const url = `/ketqua/all-giai-thuong`;

    return axiosClient.get(url);
  },
  getGoal(maNhom) {
    const url = `/ketqua/get-giai?maNhom=${maNhom}`;

    return axiosClient.get(url);
  },
};
