import React from 'react';
import { Button, Flex, Table } from 'antd';
import useGetProposeSpecific from '../../hooks/specific/useGetProposeSpecifics';

const ProposeScientificApprove = ({
  selectedCompetition,
  type,
  setOpenContent,
  setSelectedScientific,
}) => {
  // console.log({ selectedCompetition });

  const columns = [
    {
      title: 'Tên đề tài',
      dataIndex: 'tenDeTai',
      key: 'tenDeTai',
    },
    {
      title: type === 'Đã Duyệt' ? 'Ngày phê duyệt' : 'Ngày từ chối',
      dataIndex: type === 'Đã Duyệt' ? 'ngayDuyet' : 'ngayTuChoi',
      key: type === 'Đã Duyệt' ? 'ngayDuyet' : 'ngayTuChoi',
    },
    {
      title: 'Được đề xuất bởi',
      dataIndex: 'tenGiangVien',
      key: 'tenGiangVien',
    },
    {
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: (_, record) => {
        const handleDetail = () => {
          setSelectedScientific(record);
          setOpenContent(true);
        };

        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }} onClick={handleDetail}>
              Chi tiết
            </Button>
          </Flex>
        );
      },
    },
  ];

  const { data: specificProposes } = useGetProposeSpecific();

  const filteredProposes = specificProposes?.data?.filter(
    (propose) =>
      propose.trangThai === type &&
      propose.cuocThiId === selectedCompetition?.id
  );

  return (
    <Table
      columns={columns}
      dataSource={filteredProposes?.map((propose) => ({
        key: propose.id,
        ...propose,
      }))}
    />
  );
};
export default ProposeScientificApprove;
