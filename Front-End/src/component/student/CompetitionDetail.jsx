import { AndroidOutlined, AppleOutlined } from '@ant-design/icons';
import { Button, Drawer, Space, Tabs } from 'antd';
import React from 'react';
import StudentTeamJoin from './StudentTeamJoin';
import useGetGoal from '../../hooks/lecture/useGetGoal';

const CompetitionDetail = ({ open, onClose, selectedCompetition }) => {
  const { data: goalData } = useGetGoal(selectedCompetition?.maNhom);

  const data = [
    {
      key: '1',
      label: `Giải thưởng`,
      children: (
        <>
          {goalData?.data?.tenGiaiThuong ? (
            <h2>Nhóm của bạn đạt: {goalData?.data?.tenGiaiThuong}</h2>
          ) : (
            <h2>Chưa có giải</h2>
          )}
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
        <Tabs defaultActiveKey='1' items={data} />
      </Drawer>
    </>
  );
};
export default CompetitionDetail;
