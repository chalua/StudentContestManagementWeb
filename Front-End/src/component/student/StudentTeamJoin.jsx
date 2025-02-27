import React from 'react';
import { Button, Flex, Table } from 'antd';
const columns = [
  {
    title: 'Tên nhóm',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Giải thưởng',
    dataIndex: 'prize',
    key: 'prize',
  },
  {
    title: 'Hành động',
    dataIndex: '',
    key: 'x',
    render: () => {
      return (
        <>
          <Button style={{ color: '#037cb4' }}>Chi tiết nhóm</Button>
        </>
      );
    },
  },
];
const data = [
  {
    key: 1,
    name: 'The life is too short to code',
    prize: 'Giải nhất',
  },
  {
    key: 2,
    name: 'Rồng bay phượng múa',
    prize: 'Giải khuyến khích',
  },
];
const StudentTeamApprove = () => <Table columns={columns} dataSource={data} />;
export default StudentTeamApprove;
