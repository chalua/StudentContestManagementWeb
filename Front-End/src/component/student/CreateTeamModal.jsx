import React, { useState } from 'react';
import { Button, Modal } from 'antd';
import CreateTeamForm from './CreateTeamForm';

const CreateTeamModal = ({
  isModalOpen,
  showModal,
  handleOk,
  handleCancel,
}) => {
  return (
    <>
      <Button type='primary' onClick={showModal}>
        Tạo nhóm
      </Button>
      <Modal
        title='Nhóm'
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <CreateTeamForm handleCancel={handleCancel} />
      </Modal>
    </>
  );
};
export default CreateTeamModal;
