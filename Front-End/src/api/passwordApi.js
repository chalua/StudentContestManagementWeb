import axiosClient from './axiosClient';

export const passwordApi = {
  changeAdminPassword(username, data) {
    const url = `/login/${username}/change-password/admin`;

    return axiosClient.post(url, data);
  },

  changeLecturePassword(username, data) {
    const url = `/login/${username}/change-password/gv`;

    return axiosClient.post(url, data);
  },

  changeStudentPassword(username, data) {
    const url = `/login/${username}/change-password/sv`;

    return axiosClient.post(url, data);
  },
};
