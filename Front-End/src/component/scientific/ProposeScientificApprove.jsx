import { Button, Flex, Table } from 'antd';
import React from 'react';
import useGetAllProposes from '../../hooks/lecture/useGetAllProposes';
import useJoinCompetition from '../../hooks/team/useJoinCompetition';
import { toast } from 'react-toastify';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import useGetCurrentTeam from '../../hooks/student/useGetCurrentTeam';
import { useQueryClient } from '@tanstack/react-query';

const ProposeScientificApprove = ({ selectedScientific, onClose }) => {
  const { data: proposes } = useGetAllProposes();
  const { data: currentUser } = useGetCurrentStudent();
  const { data } = useGetCurrentTeam(currentUser?.data?.maSinhVien || 'SV001');
  const team = data?.data;

  const joinCompetition = useJoinCompetition();
  const queryClient = useQueryClient();

  const myProposes = proposes?.data?.filter(
    (propose) =>
      propose.cuocThiId === selectedScientific.id &&
      propose.trangThai === 'Đã Duyệt'
  );

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
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: (_, record) => {
        const handleJoinCompetition = async () => {
          const data = {
            maDeTai: record.maDeTai,
            maNhomSinhVien: team?.maNhom,
          };

          await joinCompetition.mutateAsync(data);
          toast.success('Đăng ký tham gia thành công!');

          queryClient.invalidateQueries({ queryKey: ['proposes'] });

          onClose();
        };

        return (
          <Flex gap={3}>
            <Button
              style={{ color: '#037cb4' }}
              onClick={handleJoinCompetition}
              disabled={record.daCoNhom}
            >
              Đăng Ký
            </Button>
          </Flex>
        );
      },
    },
  ];

  return <Table columns={columns} dataSource={myProposes || []} />;
};
export default ProposeScientificApprove;
