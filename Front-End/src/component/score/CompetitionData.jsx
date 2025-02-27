const data = [
  {
    key: '1',
    name: 'Cuộc thi học thuật',
    address: 'Trường ĐH Công thương',
    tags: ['Đang diễn ra'],
    soLuongVongThi: 3,
    ngayBatDau: new Date(2024, 0, 10),
    ngayKetThuc: new Date(2024, 0, 20),
    soLuongNhomToiDa: 6,
    vongThi: [
      {
        key: '1',
        name: 'Vòng 1',
        ngayBatDau: new Date(2024, 0, 10),
        ngayKetThuc: new Date(2024, 0, 12),
        soLuongNhomThamGia: 3,
        soThuTuVong: 1,
        trangThai: 'Đang diễn ra',
        ketQuaVongThi: [
          {
            key: '1',
            tenNhom: 'Nhóm 1',
            ketQuaNhom: 'pass',
          },
          {
            key: '2',
            tenNhom: 'Nhóm 2',
            ketQuaNhom: 'pass',
          },
          {
            key: '3',
            tenNhom: 'Nhóm 3',
            ketQuaNhom: 'did not pass',
          }
        ]
      },
      {
        key: '2',
        name: 'Vòng 2',
        ngayBatDau: new Date(2024, 0, 13),
        ngayKetThuc: new Date(2024, 0, 15),
        soLuongNhomThamGia: 0,
        soThuTuVong: 2,
        trangThai: 'Chưa diễn ra',
      },
      {
        key: '3',
        name: 'Vòng 3',
        ngayBatDau: new Date(2024, 0, 16),
        ngayKetThuc: new Date(2024, 0, 20),
        soLuongNhomThamGia: 0,
        soThuTuVong: 3,
        trangThai: 'Chưa diễn ra',
      }
    ]
  },
  {
    key: '2',
    name: 'Lập trình thi đấu',
    address: 'Trường ĐH Công thương',
    tags: ['Đang diễn ra'],
    soLuongVongThi: 2,
    ngayBatDau: new Date(2024, 1, 10),
    ngayKetThuc: new Date(2024, 1, 15),
    soLuongNhomToiDa: 3,
    vongThi: [
      {
        key: '1',
        name: 'Vòng 1',
        ngayBatDau: new Date(2024, 1, 10),
        ngayKetThuc: new Date(2024, 1, 12),
        soLuongNhomThamGia: 3,
        soThuTuVong: 1,
        trangThai: 'Đang diễn ra',
        ketQuaVongThi: [
          {
            key: '1',
            tenNhom: 'Nhóm 1',
            ketQuaNhom: 'undefined',
          },
          {
            key: '2',
            tenNhom: 'Nhóm 2',
            ketQuaNhom: 'undefined',
          },
          {
            key: '3',
            tenNhom: 'Nhóm 3',
            ketQuaNhom: 'undefined',
          }
        ]
      },
      {
        key: '2',
        name: 'Vòng 2',
        ngayBatDau: new Date(2024, 1, 13),
        ngayKetThuc: new Date(2024, 1, 15),
        soLuongNhomThamGia: 0,
        soThuTuVong: 2,
        trangThai: 'Chưa diễn ra',
      }
    ]
  },
  {
    key: '3',
    name: 'Cuộc đời quá ngắn để code',
    address: 'Trường ĐH Công thương',
    tags: ['Đã kết thúc'],
  },
];

export default data;