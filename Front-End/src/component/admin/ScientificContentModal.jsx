import React, { useState } from 'react';
import { Button, Modal } from 'antd';
import AddCompetitionForm from './AddScientificForm';
import AddScientificForm from './AddScientificForm';
import ReactQuill from 'react-quill';

const ScientificContentModal = ({ open, setOpen, selectedScientific }) => {
  return (
    <>
      <Modal
        title='Nội dung đề tài'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <ReactQuill value={selectedScientific.moTa} readOnly />
      </Modal>
    </>
  );
};
export default ScientificContentModal;
