import React, { useState } from 'react';
import { Button, Drawer, Space } from 'antd';
import ResultTable from './ResultTable';

const AnalysisDrawer = ({ open, setOpen, selectedCompetition }) => {
  const onClose = () => {
    setOpen(false);
  };
  return (
    <>
      <Drawer
        title={`Kết Quả`}
        placement='right'
        size={'large'}
        onClose={onClose}
        open={open}
        extra={
          <Space>
            <Button onClick={onClose}>Cancel</Button>
            <Button type='primary' onClick={onClose}>
              OK
            </Button>
          </Space>
        }
      >
        <ResultTable selectedCompetition={selectedCompetition} />
      </Drawer>
    </>
  );
};
export default AnalysisDrawer;
