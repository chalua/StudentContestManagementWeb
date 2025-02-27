import axiosClient from './axiosClient';

export const uploadApi = {
  uploadStudent(data) {
    const url = `/sinhvien/upload-danhsach`;

    return axiosClient.post(url, data);
  },
  uploadLecture(data) {
    const url = `/giangvien/upload-danhsach`;

    return axiosClient.post(url, data);
  },
};
