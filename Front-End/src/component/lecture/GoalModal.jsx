import React, { useState } from 'react';
import { Button, Modal } from 'antd';
import useGetRounds from '../../hooks/competition/useGetRounds';
import ScoreForm from './ScoreForm';
import GoalSelect from './GoalSelect';
const GoalModal = ({ open, setOpen, selectedTeam }) => {
  return (
    <>
      <Modal
        title='Trao giải'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={500}
      >
        <GoalSelect selectedTeam={selectedTeam} />
      </Modal>
    </>
  );
};
export default GoalModal;
