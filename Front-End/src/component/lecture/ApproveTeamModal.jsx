import React, { useState } from 'react';
import { Button, Drawer, Modal, Tabs } from 'antd';
import StudentTeamRequestScientific from './StudentTeamRequestScientific';

import { AndroidOutlined, AppleOutlined } from '@ant-design/icons';

const ApproveTeamModal = ({
  open,
  setOpen,
  selectedDeTai,
  setOpenReject,
  setRejectData,
}) => {
  const data = [
    {
      key: '1',
      label: `Nhóm đăng ký`,
      children: (
        <>
          <StudentTeamRequestScientific
            selectedDeTai={selectedDeTai}
            setOpenReject={setOpenReject}
            setRejectData={setRejectData}
            type='DANG_CHO_DUYET'
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '2',
      label: `Nhóm đã phê duyệt`,
      children: (
        <>
          <StudentTeamRequestScientific
            selectedDeTai={selectedDeTai}
            setOpenReject={setOpenReject}
            setRejectData={setRejectData}
            type='DA_DUYET'
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '3',
      label: `Nhóm đã từ chối`,
      children: (
        <>
          <StudentTeamRequestScientific
            selectedDeTai={selectedDeTai}
            setOpenReject={setOpenReject}
            setRejectData={setRejectData}
            type='TU_CHOI'
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
  ];

  return (
    <>
      <Modal
        title='Duyệt nhóm'
        centered
        open={open}
        onOk={() => setOpen(false)}
        onCancel={() => setOpen(false)}
        width={1000}
      >
        <Tabs defaultActiveKey='2' items={data} />
      </Modal>
    </>
  );
};
export default ApproveTeamModal;
