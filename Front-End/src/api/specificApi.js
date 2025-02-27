import axiosClient from './axiosClient';

export const specificApi = {
  getAll() {
    const url = '/cuocthi/all/nckh';

    return axiosClient.get(url);
  },

  getAllPropose() {
    const url = '/detai/de-xuat/all';

    return axiosClient.get(url);
  },

  approve(data) {
    const url = '/detai';

    return axiosClient.put(url, data);
  },
  reject(data) {
    const url = '/detai/tuchoidetai';

    return axiosClient.put(url, data);
  },
};
