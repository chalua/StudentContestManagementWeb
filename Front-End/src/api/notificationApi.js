import axiosClient from './axiosClient';

export const notificationApi = {
  create(data) {
    const url = `/thongbao`;

    return axiosClient.post(url, data);
  },

  getAll() {
    const url = `/thongbao/all`;

    return axiosClient.get(url);
  },

  get(id) {
    const url = `/thongbao/${id}`;

    return axiosClient.get(url);
  },

  delete(id) {
    const url = `/thongbao/${id}`;

    return axiosClient.delete(url);
  },
};
