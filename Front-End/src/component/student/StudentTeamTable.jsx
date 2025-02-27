import React from 'react';
import { Table } from 'antd';
import { Typography } from 'antd';
const { Paragraph, Title, Text } = Typography;

const columns = [
  {
    title: 'Tên sinh viên',
    dataIndex: 'tenSinhVien',
    key: 'tenSinhVien',
  },
  {
    title: 'MSSV',
    dataIndex: 'maSinhVien',
    key: 'maSinhVien',
  },
  {
    title: 'Chức vụ',
    dataIndex: 'chucVu',
    key: 'chucVu',
    render: (_, { nhomTruong }) => {
      return nhomTruong ? (
        <Paragraph mark>Nhóm trưởng</Paragraph>
      ) : (
        <Paragraph>Thành viên</Paragraph>
      );
    },
  },
];

const StudentTeamTable = ({ team }) => (
  <Table
    columns={columns}
    expandable={{
      expandedRowRender: (record) => (
        <p
          style={{
            margin: 0,
          }}
        >
          {record.description}
        </p>
      ),
      rowExpandable: (record) => record.name !== 'Not Expandable',
    }}
    dataSource={team?.thanhVien?.map((tv) => ({ key: tv.maSinhVien, ...tv }))}
  />
);
export default StudentTeamTable;
