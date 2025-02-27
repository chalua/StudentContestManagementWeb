import { Modal } from 'antd';
import React from 'react';
import InviteStudentForm from './InviteStudentForm';
import useCancelTeam from '../../hooks/team/useCancelTeam';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import { toast } from 'react-toastify';
import { useQueryClient } from '@tanstack/react-query';

const CancelTeamModal = ({ isModalOpen, setIsModalInviteOpen, team }) => {
  const cancelTeam = useCancelTeam();
  const { data: currentUser } = useGetCurrentStudent();
  const queryClient = useQueryClient();
  const handleOk = async () => {
    console.log('check team', team, currentUser);
    await cancelTeam.mutateAsync({
      maNhomSinhVien: team.maNhom,
      maSinhVien: currentUser.data.maSinhVien,  
    });

    toast.success('Giải tán nhóm thành công');
    setIsModalInviteOpen(false);
    queryClient.invalidateQueries();
  };

  return (
    <>
      <Modal
        title='Giải tán nhóm'
        open={isModalOpen}
        onOk={() => handleOk()}
        onCancel={() => setIsModalInviteOpen(false)}
      >
        Bạn có chắc chắn muốn giải tán nhóm không?
      </Modal>
    </>
  );
};
export default CancelTeamModal;
