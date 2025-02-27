import React, { useState } from 'react';
import AnalysisTable from './AnalysisTable';
import AnalysisDrawer from './AnalysisDrawer';

function Analysis() {
  const [openDrawer, setOpenDrawer] = useState(false);
  const [selectedCompetition, setSelectedCompetition] = useState({});

  return (
    <>
      <AnalysisTable
        setOpenDrawer={setOpenDrawer}
        setSelectedCompetition={setSelectedCompetition}
      />
      <AnalysisDrawer
        open={openDrawer}
        setOpen={setOpenDrawer}
        selectedCompetition={selectedCompetition}
      />
    </>
  );
}

export default Analysis;
