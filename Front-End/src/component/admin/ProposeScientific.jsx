import React, { useState } from 'react';
import ProposeScientificList from './ProposeScientificList';
import ProposeScientificDetail from './ProposeScientificDetail';
import ScientificModal from './ScientificModal';
import RejectModal from './RejectModal';

function ProposeScientific(props) {
  const [open, setOpen] = useState(false);
  const [openDetail, setOpenDetail] = useState(false);

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  return (
    <>
      <ScientificModal open={open} setOpen={setOpen} />
      <ProposeScientificList showDetail={showDetail} />
      <ProposeScientificDetail open={openDetail} onClose={onCloseDetail} />
    </>
  );
}

export default ProposeScientific;
