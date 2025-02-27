import { Button, Space, Table, Tag } from 'antd';
import useGetHistory from '../../hooks/student/useGetHistory';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import { useEffect, useState } from 'react';
import { formatDate } from '../../utils/formatDay';

const HistoryCompetition = ({ showDetail, setSelectedCompetition }) => {
  const { data: student } = useGetCurrentStudent();

  const { data: histories, isLoading } = useGetHistory(
    student?.data?.maSinhVien
  );

  const dataSource = Array.isArray(histories?.data)
    ? histories.data.map((history) => ({
        tenCuocThi: history?.cuocThi?.tenCuocThi,
        tenNhom: history?.nhom?.tenNhom,
        maNhom: history?.nhom?.maNhom,
        ngayThamGia: history?.ngayThamGia,
        trangThai: history?.trangThai,
      }))
    : [];

  if (isLoading) return <div>Loading...</div>;

  const columns = [
    {
      title: 'Tên cuộc thi',
      dataIndex: 'tenCuocThi',
      key: 'tenCuocThi',
      render: (text) => <a>{text}</a>,
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
      render: (_, record) => {
        const handleDetail = () => {
          showDetail();
          console.log('check record', record);
          setSelectedCompetition(record);
        };

        return (
          <Space size='middle'>
            <Button onClick={handleDetail} style={{ color: '#458BEE' }}>
              Chi tiết
            </Button>
          </Space>
        );
      },
    },
  ];

  return (
    <Table
      columns={columns}
      dataSource={dataSource?.filter((row) => row.trangThai) || []}
    />
  );
};
export default HistoryCompetition;
