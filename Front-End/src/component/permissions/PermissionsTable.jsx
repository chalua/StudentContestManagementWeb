import { Button, Space, Table, Tag } from 'antd';
import { Typography } from 'antd';
const { Title } = Typography;

const data = [
  {
    key: '0',
    name: 'Get cuộc thi',
    api: '/api/v1/competitions',
    method: 'GET',
    module: 'Cuộc thi',
  },
  {
    key: '1',
    name: 'Tạo cuộc thi',
    api: '/api/v1/competitions',
    method: 'POST',
    module: 'Cuộc thi',
  },
  {
    key: '2',
    name: 'Cập nhật cuộc thi',
    api: '/api/v1/competitions/:id',
    method: 'PUT',
    module: 'Cuộc thi',
  },
  {
    key: '3',
    name: 'Xóa cuộc thi',
    api: '/api/v1/competitions/:id',
    method: 'DELETE',
    module: 'Cuộc thi',
  },
];
const PermissionsTable = ({ showDetail }) => {
  const columns = [
    {
      title: 'Tên quyền hạn',
      dataIndex: 'name',
      key: 'name',
      render: (text) => <a>{text}</a>,
    },
    {
      title: 'API',
      dataIndex: 'api',
      key: 'api',
    },
    {
      title: 'Method',
      dataIndex: 'method',
      key: 'method',
      render: (_, record) => {
        const methods = {
          GET: '#4da37b',
          POST: '#a48f2f',
          PUT: '#0941d9',
          DELETE: '#eb1111',
        };
        return (
          <Title level={5} style={{ color: methods[record.method] }}>
            {record.method}
          </Title>
        );
      },
      filters: [
        {
          text: 'GET',
          value: 'GET',
        },
        {
          text: 'POST',
          value: 'POST',
        },
        {
          text: 'PUT',
          value: 'PUT',
        },
        {
          text: 'DELETE',
          value: 'DELETE',
        },
      ],
      onFilter: (value, record) => record.method.startsWith(value),
      filterSearch: true,
    },
    {
      title: 'Nhóm chức năng',
      dataIndex: 'module',
      key: 'module',
      filters: [
        {
          text: 'Cuộc thi',
          value: 'Cuộc thi',
        },
        {
          text: 'Thông báo',
          value: 'notification',
        },
      ],
      onFilter: (value, record) => record.module.startsWith(value),
      filterSearch: true,
    },

    {
      title: 'Hành động',
      key: 'Hành động',
      render: (_, record) => (
        <Space size='middle'>
          <Button onClick={showDetail} style={{ color: '#458BEE' }}>
            Chi tiết
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
export default PermissionsTable;
