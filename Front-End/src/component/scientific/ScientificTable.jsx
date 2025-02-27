import { Button, Space, Table, Tag, Input } from 'antd';
import useSpecificQuery from '../../hooks/specific/useSpecificQuery';
import { formatDate } from '../../utils/formatDay';
import { useState } from 'react';

const { Search } = Input;

const ScientificTable = ({ showDetail, setSelectedScientific }) => {
  const { data: specifics } = useSpecificQuery();

  const [searchText, setSearchText] = useState('');

  const handleSearch = (value) => {
    setSearchText(value.toLowerCase());
  };

  const columns = [
    {
      title: 'Tên',
      dataIndex: 'tenCuocThi',
      key: 'tenCuocThi',
      render: (text) => <a>{text}</a>,
      onFilter: (value, record) =>
        record.tenCuocThi.toLowerCase().includes(searchText),
    },
    {
      title: 'Loại cuộc thi',
      dataIndex: 'loaiCuocThi',
      key: 'loaiCuocThi',
    },
    {
      title: 'Chủ đề',
      dataIndex: 'chuDe',
      key: 'chuDe',
    },
    {
      title: 'Ngày bắt đầu',
      dataIndex: 'ngayBatDau',
      key: 'ngayBatDau',
      render: (text) => <span>{formatDate(text)}</span>,
    },
    {
      title: 'Ngày kết thúc',
      dataIndex: 'ngayKetThuc',
      key: 'ngayKetThuc',
      render: (text) => <span>{formatDate(text)}</span>,
    },
    {
      title: 'Trạng thái',
      key: 'trangThai',
      dataIndex: 'trangThai',
      filters: [
        { text: 'Kết thúc', value: 'Kết thúc' },
        { text: 'Đã khóa', value: 'Đã khóa' },
        { text: 'Chưa diễn ra', value: 'Chưa diễn ra' },
        { text: 'Đang diễn ra', value: 'Đang diễn ra' },
      ],
      defaultFilteredValue: ['Chưa diễn ra'],
      onFilter: (value, record) => record.trangThai === value,
      render: (_, { trangThai }) => {
        let color =
          trangThai === 'Kết Thúc' || trangThai === 'Đã khóa'
            ? 'volcano'
            : trangThai === 'Ẩn'
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
        const handleClick = () => {
          showDetail();
          setSelectedScientific(record);
        };

        return (
          <Space size='middle'>
            <Button onClick={handleClick} style={{ color: '#458BEE' }}>
              Chi tiết
            </Button>
          </Space>
        );
      },
    },
  ];

  const filteredData = specifics?.data.filter((item) =>
    item.tenCuocThi.toLowerCase().includes(searchText)
  );

  return (
    <div>
      <Space direction='vertical' style={{ marginBottom: 16 }}>
        <Search
          placeholder='Tìm kiếm theo tên'
          enterButton='Tìm kiếm'
          onSearch={handleSearch}
          allowClear
        />
      </Space>
      <Table columns={columns} dataSource={filteredData} />
    </div>
  );
};
export default ScientificTable;
