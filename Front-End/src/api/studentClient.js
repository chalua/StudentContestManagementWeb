import axiosClient from './axiosClient';

export const studentApi = {
  login(data) {
    const url = '/sinhvien/login';

    return axiosClient.post(url, data);
  },

  getCurrentUser() {
    const url = '/sinhvien/me';

    return axiosClient.get(url);
  },

  getCurrentTeam(studentId) {
    const url = `/nhom/hien-tai?masinhvien=${studentId}`;

    return axiosClient.get(url);
  },

  getHistory(studentId) {
    const url = `/sinhvien/lich-su-tham-gia/${studentId}`;

    return axiosClient.get(url);
  },

  getHistoryNCKH(studentId) {
    const url = `/sinhvien/lich-su-tham-gia-nckh/${studentId}`;

    return axiosClient.get(url);
  },
};
