import { Button, Space, Table, Tag } from 'antd';
import useGetHistory from '../../hooks/student/useGetHistory';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import { useEffect, useState } from 'react';
import { formatDate } from '../../utils/formatDay';
import useGetHistoryNCKH from '../../hooks/student/useGetHistoryNCKH';

const HistoryCompetitionNCKH = ({ showDetail }) => {
  const { data: student } = useGetCurrentStudent();

  const { data: histories, isLoading } = useGetHistoryNCKH(
    student?.data?.maSinhVien
  );

  const dataSource = Array.isArray(histories?.data)
    ? histories.data.map((history) => ({
        tenDeTai: history?.deTai?.tenDeTai,
        deXuatBoi: history?.deTai?.tenGiangVien,
        tenNhom: history?.nhom?.tenNhom,
        ngayThamGia: history?.ngayThamGia,
        trangThai: history?.trangThai,
      }))
    : [];

  if (isLoading) return <div>Loading...</div>;

  const columns = [
    {
      title: 'Tên đề tài',
      dataIndex: 'tenDeTai',
      key: 'tenDeTai',
      render: (text) => <a>{text}</a>,
    },
    {
      title: 'Đề xuất bời',
      dataIndex: 'deXuatBoi',
      key: 'deXuatBoi',
      render: (text) => <span>{text}</span>,
    },
    {
      title: 'Tên nhóm',
      dataIndex: 'tenNhom',
      key: 'tenNhom',
    },
    {
      title: 'Ngày tham gia',
      dataIndex: 'ngayThamGia',
      key: 'ngayThamGia',
      render: (text) => <span>{formatDate(text)}</span>,
    },
    {
      title: 'Trạng thái',
      key: 'trangThai',
      dataIndex: 'trangThai',
    },
    {
      title: 'Hành động',
      key: 'Hành động',
      render: (_, record) => (
        <Space size='middle'>
          <Button onClick={showDetail} style={{ color: '#458BEE' }}>
            Chi tiết
          </Button>
        </Space>
      ),
    },
  ];

  return (
    <Table
      columns={columns}
      dataSource={dataSource?.filter((row) => row.trangThai) || []}
    />
  );
};
export default HistoryCompetitionNCKH;
