import { Button, Space, Table, Tag } from 'antd';

const data = [
  {
    key: '1',
    name: 'NCKH Lần 1',
    department: 'Công nghệ thông tin',
    address: 'Trường ĐH Công thương',
    tags: ['Đang diễn ra'],
  },
  {
    key: '2',
    name: 'NCKH Lần 2',
    department: 'Công nghệ thông tin',
    address: 'Trường ĐH Công thương',
    tags: ['Đang diễn ra'],
  },
  {
    key: '3',
    name: 'NCKH Lần 3',
    department: 'Công nghệ thông tin',
    address: 'Trường ĐH Công thương',
    tags: ['Đã kết thúc'],
  },
];
const ProposeScientificList = ({ showDetail }) => {
  const columns = [
    {
      title: 'Tên',
      dataIndex: 'name',
      key: 'name',
      render: (text) => <a>{text}</a>,
    },
    {
      title: 'Khoa',
      dataIndex: 'department',
      key: 'department',
    },
    {
      title: 'Địa chỉ',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: 'Trạng thái',
      key: 'status',
      dataIndex: 'status',
      render: (_, { trangThai }) => {
        let color =
          trangThai === 'Kết thúc'
            ? 'volcano'
            : trangThai === 'Chưa diễn ra'
            ? 'blue'
            : 'green';

        return (
          <>
            <Tag color={color} key={trangThai}>
              {trangThai.toUpperCase()}
            </Tag>
          </>
        );
      },
    },
    {
      title: 'Hành động',
      key: 'Hành động',
      render: (_, record) => {
        const handleDetail = () => {
          showDetail();
          console.log('check recrod', record);
        };

        return (
          <Space size='middle'>
            <Button onClick={handleDetail} style={{ color: '#458BEE' }}>
              Chi tiết
            </Button>
            <Button onClick={showDetail} style={{ color: '#EEAB00' }}>
              Cập nhật
            </Button>
            <Button onClick={showDetail} style={{ color: '#ED2D13' }}>
              Xóa
            </Button>
          </Space>
        );
      },
    },
  ];

  return <Table columns={columns} dataSource={data} />;
};
export default ProposeScientificList;
