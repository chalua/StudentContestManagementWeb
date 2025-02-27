import {
  AndroidOutlined,
  AppleOutlined,
  QuestionCircleOutlined,
} from '@ant-design/icons';
import { Drawer, Tabs } from 'antd';
import React, { useEffect, useState } from 'react';
import AddCompetitionRound from './AddCompetitionRound';
import { useLocation } from 'react-router-dom';
import LectureAssign from '../admin/LectureAssign';
import ContentCompetition from '../admin/ContentCompetiton';
import LectureProposeScientific from '../admin/LectureProposeScientific';
import ProposeScientificApprove from '../admin/ProposeScientificApprove';
import ScientificContentModal from '../admin/ScientificContentModal';

const ManageCompetitionDetail = ({
  open,
  onClose,
  selectedCompetition,
  setOpenReject,
  setRejectData,
  setSelectedCompetition,
}) => {
  const location = useLocation();

  const isCompetition = location.pathname == '/admin/manage-competition';
  const isCompetitionProject = location.pathname == '/admin/manage-project';
  const isScientific = location.pathname == '/admin/manage-scientific';

  const [openContent, setOpenContent] = useState(false);
  const [selectedScientific, setSelectedScientific] = useState({});

  const data = [
    {
      key: '1',
      label: `Nội dung cuộc thi`,
      children: (
        <>
          <ContentCompetition selectedCompetition={selectedCompetition} />
        </>
      ),
      icon: <AndroidOutlined />,
    },
    {
      key: '2',
      label: isScientific ? null : `Cấp quyền cho giảng viên`,
      children: (
        <>
          <LectureAssign
            selectedCompetition={selectedCompetition}
            setSelectedCompetition={setSelectedCompetition}
            open={open}
          />
        </>
      ),
      icon: <AndroidOutlined />,
    },
    {
      key: '3',
      label: isCompetition ? `Tạo vòng thi` : null,
      children: (
        <>
          <AddCompetitionRound />
        </>
      ),
      icon: isCompetition ? <QuestionCircleOutlined /> : null,
    },
    {
      key: '4',
      label: isScientific ? `Đề xuất của giảng viên` : null,
      children: (
        <>
          <LectureProposeScientific
            selectedCompetition={selectedCompetition}
            type='Đang Chờ Duyệt'
            setOpenReject={setOpenReject}
            setRejectData={setRejectData}
            setOpenContent={setOpenContent}
            setSelectedScientific={setSelectedScientific}
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '5',
      label: isScientific ? `Đề tài đã được phê duyệt` : null,
      children: (
        <>
          <ProposeScientificApprove
            selectedCompetition={selectedCompetition}
            type='Đã Duyệt'
            setOpenContent={setOpenContent}
            setSelectedScientific={setSelectedScientific}
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '6',
      label: isScientific ? `Đề tài đã từ chối` : null,
      children: (
        <>
          <ProposeScientificApprove
            selectedCompetition={selectedCompetition}
            type='Từ Chối'
            setOpenContent={setOpenContent}
            setSelectedScientific={setSelectedScientific}
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
  ];

  const filteredData = data.filter((item) => {
    return item.label !== null;
  });

  return (
    <>
      <Drawer
        title='Chi tiết cuộc thi'
        placement={'right'}
        width={1100}
        onClose={onClose}
        open={open}
      >
        <Tabs defaultActiveKey='1' items={filteredData} />
      </Drawer>

      <ScientificContentModal
        open={openContent}
        setOpen={setOpenContent}
        selectedScientific={selectedCompetition}
      />
    </>
  );
};
export default ManageCompetitionDetail;
