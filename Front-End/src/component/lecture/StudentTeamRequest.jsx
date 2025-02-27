import React from 'react';
import { Button, Flex, Table } from 'antd';
import useGetLectureTeams from '../../hooks/lecture/useGetLectureTeams';
import useLectureApproveOrReject from '../../hooks/lecture/useLectureApproveOrReject';
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';
import { useNavigate } from 'react-router-dom';

const StudentTeamRequest = ({
  selectedCompetition,
  setOpenReject,
  setRejectData,
}) => {
  const { data: lecture } = useGetCurrentLecture();
  const { data: teams } = useGetLectureTeams(selectedCompetition?.id);
  const approveOrReject = useLectureApproveOrReject();
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  const columns = [
    {
      title: 'Tên nhóm',
      dataIndex: 'tenNhom',
      key: 'tenNhom',
    },
    {
      title: 'Ngày đăng ký',
      dataIndex: 'ngayDangKy',
      key: 'ngayDangKy',
    },
    {
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: (_, record) => {
        const handleApprove = async () => {
          await approveOrReject.mutateAsync({
            maGiangVien: lecture?.data?.maGiangVien,
            maCuocThi: record.maCuocThi,
            maNhomSinhVien: record.maNhom,
          });

          await queryClient.invalidateQueries([
            'lecture-teams',
            selectedCompetition?.id,
          ]);

          toast.success('Duyệt nhóm thành công');
        };

        const handleReject = async () => {
          setOpenReject(true);

          setRejectData({
            maGiangVien: lecture?.data?.maGiangVien,
            maCuocThi: record.maCuocThi,
            maNhomSinhVien: record.maNhom,
          });
        };

        const handleViewTeam = () => {
          navigate(`/lecture/team/${record.maNhom}`);
        };

        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }} onClick={handleViewTeam}>
              Chi tiết nhóm
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

  return (
    <Table
      columns={columns}
      dataSource={teams?.data?.filter(
        (team) => team.trangThai === 'DANG_CHO_DUYET'
      )}
    />
  );
};
export default StudentTeamRequest;
