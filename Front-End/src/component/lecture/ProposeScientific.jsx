import React, { useState } from 'react';
import ProposeScientificDetail from './ProposeScientificDetail';
import ProposeScientificList from './ProposeScientificList';

function ProposeScientific(props) {
  const [openDetail, setOpenDetail] = useState(false);

  const [selectedScientific, setSelectedScientific] = useState({});

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  return (
    <>
      <ProposeScientificList
        showDetail={showDetail}
        setSelectedScientific={setSelectedScientific}
      />
      <ProposeScientificDetail
        open={openDetail}
        onClose={onCloseDetail}
        selectedScientific={selectedScientific}
      />
    </>
  );
}

export default ProposeScientific;
