import React, { useState } from 'react';
import CompetitionList from '../../component/competition/CompetitionList';
import CompetitionDetail from '../../component/competition/CompetitionDetail';

function CompetitionPage() {
  const [selectedCompetition, setSelectedCompetition] = useState({});

  const [openDetail, setOpenDetail] = useState(false);

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  return (
    <>
      <CompetitionList
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

export default CompetitionPage;
