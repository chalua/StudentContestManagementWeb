import React, { useState } from 'react';
import { Button, Modal } from 'antd';
import useGetRounds from '../../hooks/competition/useGetRounds';
import ScoreForm from './ScoreForm';
const ScoreModal = ({ open, setOpen, selectedTeam }) => {
  const { data: rounds } = useGetRounds(selectedTeam?.maCuocThi);

  return (
    <>
      <Modal
        title='Chấm điểm'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        {rounds?.data?.map((round) => (
          <>
            <ScoreForm
              round={round}
              allRounds={rounds?.data || []}
              selectedTeam={selectedTeam}
            />
          </>
        ))}
      </Modal>
    </>
  );
};
export default ScoreModal;
