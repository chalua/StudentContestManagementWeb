import React, { useState } from 'react';
import { Button, Modal } from 'antd';
import AddCompetitionForm from './AddCompetitionForm';

const CompetitionModal = ({
  open,
  setOpen,
  selectedCompetition,
  setSelectedCompetition,
}) => {
  return (
    <>
      <Button
        type='primary'
        onClick={() => {
          setSelectedCompetition({});
          setOpen(true);
        }}
      >
        Thêm cuộc thi
      </Button>
      <Modal
        title='Thêm mới cuộc thi'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <AddCompetitionForm
          selectedCompetition={selectedCompetition}
          setOpen={setOpen}
        />
      </Modal>
    </>
  );
};
export default CompetitionModal;
