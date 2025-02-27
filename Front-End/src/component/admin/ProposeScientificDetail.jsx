import { AndroidOutlined, AppleOutlined } from '@ant-design/icons';
import { Drawer, Tabs } from 'antd';
import React from 'react';
import LectureProposeScientific from './LectureProposeScientific';
import ProposeScientificApprove from './ProposeScientificApprove';

const ProposeScientificDetail = ({
  open,
  onClose,
  selectedScientific,
  setOpenReject,
}) => {
  console.log({ setOpenReject });

  const data = [
    {
      key: '2',
      label: `Đề xuất của giảng viên`,
      children: (
        <>
          <LectureProposeScientific
            selectedScientific={selectedScientific}
            setOpenReject={setOpenReject}
          />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '3',
      label: `Đề tài đã được phê duyệt`,
      children: (
        <>
          <ProposeScientificApprove />
        </>
      ),
      icon: <AppleOutlined />,
    },
    {
      key: '4',
      label: `Đề tài đã từ chối`,
      children: (
        <>
          <ProposeScientificApprove />
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
    </>
  );
};
export default ProposeScientificDetail;
