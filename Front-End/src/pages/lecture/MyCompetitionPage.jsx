import React, { useEffect, useState } from 'react';
import CompetitionList from '../../component/lecture/CompetitionList';
import CompetitionDetail from '../../component/lecture/CompetitionDetail';
import RejectModal from '../../component/lecture/RejectModal';

function MyCompetitionPage(props) {
  const [openDetail, setOpenDetail] = useState(false);
  const [openReject, setOpenReject] = useState(false);

  const [selectedCompetition, setSelectedCompetition] = useState({});
  const [rejectData, setRejectData] = useState({});

  const showDetail = () => {
    setOpenDetail(true);
  };
  const onCloseDetail = () => {
    setOpenDetail(false);
  };

  useEffect(() => {
    if (!openReject) setRejectData(null);
  }, [openReject]);

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
        openReject={openReject}
        setOpenReject={setOpenReject}
        setRejectData={setRejectData}
      />
      <RejectModal
        open={openReject}
        setOpen={setOpenReject}
        rejectData={rejectData}
      />
    </>
  );
}

export default MyCompetitionPage;
