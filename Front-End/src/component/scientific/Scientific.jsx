import React, { useState } from 'react';
import ScientificDetail from './ScientificDetail';
import ScientificTable from './ScientificTable';

function Scientific(props) {
  const [selectedScientific, setSelectedScientific] = useState({});
  const [openDetail, setOpenDetail] = useState(false);

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  return (
    <>
      <ScientificTable
        open={openDetail}
        onClose={onCloseDetail}
        showDetail={showDetail}
        setSelectedScientific={setSelectedScientific}
      />
      <ScientificDetail
        open={openDetail}
        onClose={onCloseDetail}
        selectedScientific={selectedScientific}
      />
    </>
  );
}

export default Scientific;
