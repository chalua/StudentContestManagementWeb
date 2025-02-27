import { Modal } from 'antd';
import React from 'react';
import InviteStudentForm from './InviteStudentForm';

const InviteStudentModal = ({ isModalOpen, setIsModalInviteOpen }) => {
  return (
    <>
      <Modal
        title='Mời thành viên'
        open={isModalOpen}
        onOk={() => setIsModalInviteOpen(false)}
        onCancel={() => setIsModalInviteOpen(false)}
      >
        <InviteStudentForm setIsModalInviteOpen={setIsModalInviteOpen} />
      </Modal>
    </>
  );
};
export default InviteStudentModal;
