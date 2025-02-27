import { AndroidOutlined, AppleOutlined } from '@ant-design/icons';
import { Drawer, Tabs } from 'antd';
import React, { useState } from 'react';
import AddProposeScientific from './AddProposeScientific';
import ProposeScientificApprove from './ProposeScientificApprove';
import MyProposeScientific from './MyProposeScientific';
import StudentTeamRequestScientific from './StudentTeamRequestScientific';
import ApproveTeamModal from './ApproveTeamModal';
import RejectModalDeTai from './RejectModalDeTai';

const ProposeScientificDetail = ({ open, onClose, selectedScientific }) => {
  const [openApproveTeamModal, setOpenApproveTeamModal] = useState(false);

  const [selectedDeTai, setSelectedDeTai] = useState({});
  const [rejectData, setRejectData] = useState({});
  const [openReject, setOpenReject] = useState(false);

  const data = [
    {
      key: '2',
      label: `Đề xuất đề tài`,
      children: (
        <>
          <AddProposeScientific selectedScientific={selectedScientific} />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '3',
      label: `Đề tài đã được phê duyệt`,
      children: (
        <>
          <ProposeScientificApprove
            selectedScientific={selectedScientific}
            setOpenApproveTeamModal={setOpenApproveTeamModal}
            setSelectedDeTai={setSelectedDeTai}
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '4',
      label: `Đề xuất của tôi`,
      children: (
        <>
          <MyProposeScientific selectedScientific={selectedScientific} />
        </>
      ),
      icon: <AppleOutlined />,
    },
  ];

  return (
    <>
      <Drawer
        title='Chi tiết NCKH'
        placement={'right'}
        width={1100}
        onClose={onClose}
        open={open}
      >
        <Tabs defaultActiveKey='2' items={data} />
      </Drawer>

      <ApproveTeamModal
        open={openApproveTeamModal}
        setOpen={setOpenApproveTeamModal}
        selectedDeTai={selectedDeTai}
        setOpenReject={setOpenReject}
        setRejectData={setRejectData}
      />

      <RejectModalDeTai
        open={openReject}
        setOpen={setOpenReject}
        rejectData={rejectData}
      />
    </>
  );
};
export default ProposeScientificDetail;
