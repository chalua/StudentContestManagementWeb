import React, { useState } from 'react';
import HistoryCompetition from '../../component/student/HistoryCompetition';
import CompetitionDetail from '../../component/student/CompetitionDetail';

function HistoryCompetitionPage(props) {
  const [openDetail, setOpenDetail] = useState(false);
  const [selectedCompetition, setSelectedCompetition] = useState({});

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  return (
    <>
      <HistoryCompetition
        showDetail={showDetail}
        setSelectedCompetition={setSelectedCompetition}
      />
      <CompetitionDetail
        open={openDetail}
        onClose={onCloseDetail}
        selectedCompetition={selectedCompetition}
      />
    </>
  );
}

export default HistoryCompetitionPage;
