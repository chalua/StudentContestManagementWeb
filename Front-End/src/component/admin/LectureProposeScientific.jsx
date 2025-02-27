import { useQueryClient } from '@tanstack/react-query';
import { Button, Flex, Table } from 'antd';
import React from 'react';
import { toast } from 'react-toastify';
import useApproveSpecific from '../../hooks/specific/useApproveSpecific';
import useGetProposeSpecific from '../../hooks/specific/useGetProposeSpecifics';

const LectureProposeScientific = ({
  selectedCompetition,
  setOpenReject,
  setRejectData,
  status = 'Đang Chờ Duyệt',
  setSelectedScientific,
  setOpenContent,
}) => {
  const approve = useApproveSpecific();
  const queryClient = useQueryClient();

  const columns = [
    {
      title: 'Tên đề tài',
      dataIndex: 'tenDeTai',
      key: 'tenDeTai',
    },
    {
      title: 'Ngày đề xuất',
      dataIndex: 'ngayDeXuat',
      key: 'ngayDeXuat',
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
        const handleApprove = async () => {
          await approve.mutateAsync({ deTaiIds: [record.maDeTai] });
          toast.success('Duyệt đề tài thành công');
          queryClient.invalidateQueries(['specificList-propose']);
        };

        const handleReject = async () => {
          setRejectData({ deTaiIds: [record.maDeTai] });
          setOpenReject(true);
          // await reject.mutateAsync({ deTaiIds: [record.maDeTai] });
          // toast.success('Từ chối đề tài thành công');
          // queryClient.invalidateQueries(['specificList-propose']);
        };

        const handleDetail = () => {
          setSelectedScientific(record);
          setOpenContent(true);
        };

        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }} onClick={handleDetail}>
              Chi tiết
            </Button>
            <Button style={{ color: '#02800A' }} onClick={handleApprove}>
              Phê duyệt
            </Button>
            <Button style={{ color: '#DC3B0E' }} onClick={handleReject}>
              Từ chối
            </Button>
          </Flex>
        );
      },
    },
  ];

  const { data: specificProposes } = useGetProposeSpecific();

  const filteredProposes = specificProposes?.data?.filter(
    (propose) =>
      propose.trangThai === status &&
      propose.cuocThiId === selectedCompetition.id
  );

  return (
    <>
      <Table
        columns={columns}
        dataSource={filteredProposes?.map((propose) => ({
          key: propose.id,
          ...propose,
        }))}
      />
    </>
  );
};
export default LectureProposeScientific;
