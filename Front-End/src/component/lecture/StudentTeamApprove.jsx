import React, { useState } from 'react';
import { Button, Flex, Table } from 'antd';
import useGetLectureTeams from '../../hooks/lecture/useGetLectureTeams';
import { useNavigate } from 'react-router-dom';
import ScoreModal from './ScoreModal';
import GoalModal from './GoalModal';

const StudentTeamApprove = ({ selectedCompetition }) => {
  const { data: teams } = useGetLectureTeams(selectedCompetition?.id);
  const navigate = useNavigate();
  const [openScore, setOpenScore] = useState(false);
  const [openGoal, setOpenGoal] = useState(false);
  const [selectedTeam, setSelectedTeam] = useState({});

  const columns = [
    {
      title: 'Tên nhóm',
      dataIndex: 'tenNhom',
      key: 'tenNhom',
    },
    {
      title: 'Ngày duyệt',
      dataIndex: 'ngayDuyet',
      key: 'ngayDuyet',
    },
    {
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: (_, record) => {
        const handleViewTeam = () => {
          navigate(`/lecture/team/${record.maNhom}`);
        };

        const handleScore = () => {
          setSelectedTeam(record);
          setOpenScore(true);
        };

        const handleGoal = () => {
          setSelectedTeam(record);
          setOpenGoal(true);
        };

        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }} onClick={handleViewTeam}>
              Chi tiết nhóm
            </Button>
            <Button style={{ color: '#585705' }} onClick={handleScore}>
              Chấm điểm
            </Button>
            <Button style={{ color: '#0b9912' }} onClick={handleGoal}>
              Trao giải
            </Button>
          </Flex>
        );
      },
    },
  ];

  return (
    <>
      <Table
        columns={columns}
        dataSource={teams?.data?.filter(
          (team) => team.trangThai === 'DA_DUYET'
        )}
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
export default StudentTeamApprove;
