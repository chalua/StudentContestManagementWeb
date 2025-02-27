import React from 'react';
import { 
    Table, 
    Select, 
    Button
} from 'antd';
import { Option } from 'antd/es/mentions';

const ScoreManagementTable = ({ scores, btnOnclick }) => {
  const columns = [
    {
      title: 'Tên nhóm',
      dataIndex: 'tenNhom',
      key: 'groupName',
    },
    {
      title: 'Kết quả',
      dataIndex: 'ketQuaNhom',
      key: 'result',
    },
    {
      title: 'Cập nhật kết quả',
      key: 'updateResult',
      render: (_, record) => (
        <Button 
            type='primary'
            onClick={() => btnOnclick(record)}
        >
            Cập nhật kết quả
        </Button>
      ),
    },
  ];

  return (
    <Table dataSource={scores} columns={columns} />
  );
}

export default ScoreManagementTable;