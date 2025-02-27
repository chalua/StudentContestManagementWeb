import { Button, Flex, Table } from 'antd';
import React, { useState } from 'react';
import useGetAllProposes from '../../hooks/lecture/useGetAllProposes';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';

const ProposeScientificApprove = ({
  selectedScientific,
  setOpenApproveTeamModal,
  setSelectedDeTai,
}) => {
  const { data: proposes } = useGetAllProposes();
  const { data: lecture } = useGetCurrentLecture();

  const myProposes = proposes?.data?.filter(
    (propose) =>
      propose.cuocThiId === selectedScientific.id &&
      propose.maGiangVien === lecture?.data?.maGiangVien &&
      propose.trangThai !== 'Đang Chờ Duyệt'
  );

  const columns = [
    {
      title: 'Tên đề tài',
      dataIndex: 'tenDeTai',
      key: 'tenDeTai',
      render: (_, record) => {
        if (record.lyDoTuChoi)
          return <span style={{ color: '#da3636' }}>{record.tenDeTai}</span>;

        return record.tenDeTai;
      },
    },
    {
      title: 'Ngày đề xuất',
      dataIndex: 'ngayDeXuat',
      key: 'ngayDeXuat',
    },
    {
      title: 'Lý do từ chối',
      dataIndex: 'lyDoTuChoi',
      key: 'lyDoTuChoi',
    },
    {
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: (_, record) => {
        const handleApprove = () => {
          setOpenApproveTeamModal(true);

          setSelectedDeTai(record);
        };

        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }} onClick={handleApprove}>
              Chi tiết
            </Button>
          </Flex>
        );
      },
    },
  ];

  return <Table columns={columns} dataSource={myProposes || []} />;
};
export default ProposeScientificApprove;
