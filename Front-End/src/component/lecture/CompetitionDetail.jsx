import {
  AndroidOutlined,
  AppleOutlined,
  QuestionCircleOutlined,
} from '@ant-design/icons';
import { Button, Drawer, Space, Tabs } from 'antd';
import React from 'react';
import StudentTeamRequest from './StudentTeamRequest';
import StudentTeamApprove from './StudentTeamApprove';
import ContentCompetition from './ContentCompetiton';
import { useNavigate } from 'react-router-dom';

const CompetitionDetail = ({
  open,
  onClose,
  selectedCompetition,
  openReject,
  setOpenReject,
  setRejectData,
}) => {
  const navigate = useNavigate();

  const data = [
    {
      key: '1',
      label: `Chi tiết cuộc thi`,
      children: (
        <>
          <ContentCompetition selectedCompetition={selectedCompetition} />
        </>
      ),
      icon: <AndroidOutlined />,
    },
    {
      key: '2',
      label: `Phê duyệt nhóm`,
      children: (
        <>
          <StudentTeamRequest
            selectedCompetition={selectedCompetition}
            setOpenReject={setOpenReject}
            setRejectData={setRejectData}
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '3',
      label: `Nhóm đã phê duyệt`,
      children: (
        <>
          <StudentTeamApprove selectedCompetition={selectedCompetition} />
        </>
      ),
      icon: <AppleOutlined />,
    },
  ];
  return (
    <>
      <Drawer
        title='Chi tiết cuộc thi'
        placement={'right'}
        width={1100}
        onClose={onClose}
        open={open}
      >
        <Tabs defaultActiveKey='2' items={data} />
      </Drawer>
    </>
  );
};
export default CompetitionDetail;
