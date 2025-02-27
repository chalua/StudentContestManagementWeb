import React, { useState } from 'react';
import ManageCompetition from '../../component/competition/ManageCompetition';
import ManageCompetitionDetail from '../../component/competition/ManageCompetitionDetail';
import RejectModal from '../../component/admin/RejectModal';

function ManageCompetitionPage() {
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

  return (
    <>
      <ManageCompetition
        showDetail={showDetail}
        selectedCompetition={selectedCompetition}
        setSelectedCompetition={setSelectedCompetition}
      />
      <ManageCompetitionDetail
        open={openDetail}
        onClose={onCloseDetail}
        selectedCompetition={selectedCompetition}
        setOpenReject={setOpenReject}
        setRejectData={setRejectData}
        setSelectedCompetition={setSelectedCompetition}
      />
      <RejectModal
        open={openReject}
        setOpen={setOpenReject}
        rejectData={rejectData}
      />
    </>
  );
}

export default ManageCompetitionPage;
