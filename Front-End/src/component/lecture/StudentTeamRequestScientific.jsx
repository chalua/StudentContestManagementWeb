import { useQueryClient } from '@tanstack/react-query';
import { Button, Flex, Table } from 'antd';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';
import useGetLectureTeamsDeTai from '../../hooks/lecture/useGetLectureTeamsDeTai';
import useLectureApproveDeTai from '../../hooks/lecture/useLectureApproveDeTai';
import useLectureRejectDeTai from '../../hooks/lecture/useLectureRejectDeTai';
import ScoreModal from './ScoreModal';
import GoalModal from './GoalModal';

const StudentTeamRequestScientific = ({
  selectedDeTai,
  setOpenReject,
  setRejectData,
  type,
}) => {
  const { data: lecture } = useGetCurrentLecture();
  const { data: teams } = useGetLectureTeamsDeTai(selectedDeTai?.maDeTai);
  const [openScore, setOpenScore] = useState(false);
  const [openGoal, setOpenGoal] = useState(false);
  const [selectedTeam, setSelectedTeam] = useState({});

  const navigate = useNavigate();

  const approve = useLectureApproveDeTai();
  const reject = useLectureRejectDeTai();

  const queryClient = useQueryClient();

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
          await approve.mutateAsync({
            maGiangVien: lecture?.data?.maGiangVien,
            maDeTai: record.maDeTai,
            maNhomSinhVien: record.maNhom,
          });

          await queryClient.invalidateQueries([
            'lecture-teams-detai',
            selectedDeTai?.maDeTai,
          ]);

          toast.success('Duyệt nhóm thành công');
        };

        const handleReject = async () => {
          setOpenReject(true);

          setRejectData({
            maGiangVien: lecture?.data?.maGiangVien,
            maDeTai: record.maDeTai,
            maNhomSinhVien: record.maNhom,
          });
        };

        const handleViewTeam = () => {
          navigate(`/lecture/team/${record.maNhom}`);
        };

        const handleScore = () => {
          setOpenScore(true);
          setSelectedTeam(record);
        };

        const handleGoal = () => {
          setOpenGoal(true);
          setSelectedTeam(record);
        };

        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }} onClick={handleViewTeam}>
              Chi tiết nhóm
            </Button>
            {type === 'DA_DUYET' && (
              <>
                <Button style={{ color: '#137d17' }} onClick={handleScore}>
                  Chấm điểm
                </Button>
                <Button style={{ color: '#a3a10a' }} onClick={handleGoal}>
                  Trao giải
                </Button>
              </>
            )}
            <span
              style={{ display: type !== 'DANG_CHO_DUYET' ? 'none' : 'block' }}
            >
              <Button style={{ color: '#02800A' }} onClick={handleApprove}>
                Phê duyệt
              </Button>
              <Button style={{ color: '#DC3B0E' }} onClick={handleReject}>
                Từ chối
              </Button>
            </span>
          </Flex>
        );
      },
    },
  ];

  return (
    <>
      <Table
        columns={columns}
        dataSource={teams?.data?.filter((team) => team.trangThai === type)}
      />

      <ScoreModal
        open={openScore}
        setOpen={setOpenScore}
        selectedTeam={selectedTeam}
      />

      <GoalModal
        open={openGoal}
        setOpen={setOpenGoal}
        selectedTeam={selectedTeam}
      />
    </>
  );
};
export default StudentTeamRequestScientific;
