import React, { useState } from 'react';
import CompetitionDetail from '../../component/student/CompetitionDetail';
import HistoryCompetitionNCKH from '../../component/student/HistoryCompetitionNCKH';

function HistoryCompetitionNCKHPage(props) {
  const [openDetail, setOpenDetail] = useState(false);

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  return (
    <>
      <HistoryCompetitionNCKH showDetail={showDetail} />
      <CompetitionDetail open={openDetail} onClose={onCloseDetail} />
    </>
  );
}

export default HistoryCompetitionNCKHPage;
