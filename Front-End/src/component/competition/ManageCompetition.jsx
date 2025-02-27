import React, { useState } from 'react';
import CompetitionModal from './CompetitionModal';
import ManageCompetitionList from './ManageCompetitionList';
import ModalConfirmDelete from './ModalConfirmDelete';

function ManageCompetition({
  showDetail,
  setSelectedCompetition,
  selectedCompetition,
}) {
  const [open, setOpen] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);

  return (
    <>
      <CompetitionModal
        open={open}
        setOpen={setOpen}
        setSelectedCompetition={setSelectedCompetition}
        selectedCompetition={selectedCompetition}
      />
      <ModalConfirmDelete
        open={openDelete}
        setOpen={setOpenDelete}
        selectedCompetition={selectedCompetition}
      />
      <ManageCompetitionList
        setOpen={setOpen}
        showDetail={showDetail}
        setSelectedCompetition={setSelectedCompetition}
        setOpenDelete={setOpenDelete}
      />
    </>
  );
}

export default ManageCompetition;
