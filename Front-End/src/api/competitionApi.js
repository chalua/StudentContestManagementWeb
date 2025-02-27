import axiosClient from './axiosClient';

export const competitionApi = {
  getAll() {
    const url = '/cuocthi/all';

    return axiosClient.get(url);
  },

  getNhomThamGiaDeTai(maDeTai) {
    const url = `/detai/get-nhom-tham-gia/${maDeTai}`;

    return axiosClient.get(url);
  },

  getAllBtType(typeId) {
    const url = `/cuocthi/${typeId}`;

    return axiosClient.get(url);
  },

  create(data) {
    const url = '/cuocthi';

    return axiosClient.post(url, data);
  },

  getRounds(competitionId) {
    const url = `/cuocthi/vong-thi/${competitionId}`;

    return axiosClient.get(url);
  },

  createRound(data) {
    const url = '/cuocthi/vong-thi';

    return axiosClient.post(url, data);
  },

  updateRound(data) {
    const url = '/cuocthi/vong-thi';

    return axiosClient.put(url, data);
  },

  deleteRound(data) {
    const url = `/cuocthi/${data.competitionId}/vong-thi/${data.roundId}`;

    return axiosClient.delete(url);
  },

  update(data) {
    const url = `/cuocthi/${data.id}`;

    return axiosClient.put(url, data);
  },

  delete(id) {
    const url = `/cuocthi/${id}`;

    return axiosClient.delete(url);
  },
};
