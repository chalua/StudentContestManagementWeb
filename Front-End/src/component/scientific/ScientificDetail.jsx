import { AppleOutlined } from '@ant-design/icons';
import { Drawer, Tabs } from 'antd';
import React from 'react';
import ProposeScientificApprove from './ProposeScientificApprove';

const ScientificDetail = ({ open, onClose, selectedScientific }) => {
  const data = [
    {
      key: '1',
      label: `Đề tài đã được phê duyệt`,
      children: (
        <>
          <ProposeScientificApprove
            selectedScientific={selectedScientific}
            onClose={onClose}
          />
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
export default ScientificDetail;
