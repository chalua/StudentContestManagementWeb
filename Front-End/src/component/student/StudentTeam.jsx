import React, { useState, useEffect } from 'react';
import { Button, Divider, Typography } from 'antd';
import CreateTeamModal from './CreateTeamModal';
import StudentTeamTable from './StudentTeamTable';
import InviteStudentModal from './InviteStudentModal';
import useGetCurrentTeam from '../../hooks/student/useGetCurrentTeam';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import axios from 'axios';
import CancelTeamModal from './CancelTeamModal';

const { Title } = Typography;

function StudentTeam(props) {
  const { data: currentUser } = useGetCurrentStudent();
  const { data } = useGetCurrentTeam(currentUser?.data?.maSinhVien || 'SV001');

  const team = data?.data;

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isModalInviteOpen, setIsModalInviteOpen] = useState(false);
  const [isCancelTeamModal, setIsCancelTeamModal] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };

  return (
    <div>
      <CreateTeamModal
        isModalOpen={isModalOpen}
        showModal={showModal}
        handleOk={handleOk}
        handleCancel={handleCancel}
      />

      <React.Fragment key={team?.maNhom}>
        <Title level={3} style={{ textAlign: 'center', marginBottom: 15 }}>
          Tên nhóm: {team?.tenNhom}
        </Title>
        <Button
          color='primary'
          onClick={() => setIsModalInviteOpen(true)}
          style={{ marginRight: '20px' }}
        >
          Mời thành viên
        </Button>
        <Button
          type='primary'
          style={{ background: '#bc2b2b' }}
          onClick={() => setIsCancelTeamModal(true)}
        >
          Giải tán
        </Button>

        <StudentTeamTable team={team} />
        <Divider />
      </React.Fragment>

      <InviteStudentModal
        isModalOpen={isModalInviteOpen}
        setIsModalInviteOpen={setIsModalInviteOpen}
      />

      <CancelTeamModal
        isModalOpen={isCancelTeamModal}
        setIsModalInviteOpen={setIsCancelTeamModal}
        team={team}
      />
    </div>
  );
}

export default StudentTeam;
