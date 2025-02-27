import React from 'react';
import { Button, Flex, Table } from 'antd';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import useGetInvitation from '../../hooks/team/useGetInvitation';
import useApproveJoinTeam from '../../hooks/team/useApproveJoinTeam';
import { useQueryClient } from '@tanstack/react-query';
import { toast } from 'react-toastify';
import useRejectJoinTeam from '../../hooks/team/useRejectJoinTeam';

const TeamInvite = () => {
  const approve = useApproveJoinTeam();
  const reject = useRejectJoinTeam();
  const queryClient = useQueryClient();

  const { data: user } = useGetCurrentStudent();
  const { data: invitations } = useGetInvitation(user?.data?.maSinhVien || '');

  const columns = [
    {
      title: 'Tên nhóm',
      dataIndex: 'tenNhom',
      key: 'tenNhom',
    },
    {
      title: 'Ngày mời vào',
      dataIndex: 'ngayMoi',
      key: 'ngayMoi',
    },
    {
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: (_, record) => {
        const handleApproveJoinTeam = async () => {
          await approve.mutateAsync({
            maNhom: record.maNhom,
            maSinhVien: record.maSinhVien,
          });

          toast.success('Bạn đã tham gia nhóm');
          await queryClient.invalidateQueries(['invitation']);
        };

        const handleRejectJoinTeam = async () => {
          await reject.mutateAsync({
            maNhom: record.maNhom,
            maSinhVien: record.maSinhVien,
          });

          toast.success('Bạn đã từ chối tham gia nhóm');
          await queryClient.invalidateQueries(['invitation']);
        };

        return (
          <Flex gap={3}>
            <Button
              style={{ color: '#02800A' }}
              onClick={handleApproveJoinTeam}
            >
              Đồng ý
            </Button>
            <Button style={{ color: '#DC3B0E' }} onClick={handleRejectJoinTeam}>
              Từ chối
            </Button>
          </Flex>
        );
      },
    },
  ];

  return <Table columns={columns} dataSource={invitations?.data || []} />;
};
export default TeamInvite;
