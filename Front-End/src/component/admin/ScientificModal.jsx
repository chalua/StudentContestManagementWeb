import React, { useState } from 'react';
import { Button, Modal } from 'antd';
import AddCompetitionForm from './AddScientificForm';
import AddScientificForm from './AddScientificForm';

const ScientificModal = ({ open, setOpen }) => {
  return (
    <>
      <Button type='primary' onClick={() => setOpen(true)}>
        Thêm cuộc thi NCKH
      </Button>
      <Modal
        title='Thêm mới cuộc thi'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <AddScientificForm />
      </Modal>
    </>
  );
};
export default ScientificModal;
