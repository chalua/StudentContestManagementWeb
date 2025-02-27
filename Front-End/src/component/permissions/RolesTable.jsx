import { Button, Space, Table, Tag } from 'antd';
import { Typography } from 'antd';
const { Title } = Typography;

const data = [
  {
    key: '0',
    name: 'User',
  },
  {
    key: '1',
    name: 'Sinh viên',
  },
  {
    key: '2',
    name: 'Giảng viên',
  },
  {
    key: '3',
    name: 'Admin',
  },
];

const RolesTable = ({ setOpen }) => {
  const columns = [
    {
      title: 'Tên vai trò',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'Hành động',
      key: 'Hành động',
      render: (_, record) => (
        <Space size='middle'>
          <Button onClick={() => setOpen(true)} style={{ color: '#1c51e3' }}>
            Chỉnh Sửa
          </Button>
        </Space>
      ),
    },
  ];

  const onChange = (pagination, filters, sorter, extra) => {
    console.log('params', pagination, filters, sorter, extra);
  };

  return <Table columns={columns} dataSource={data} onChange={onChange} />;
};
export default RolesTable;
