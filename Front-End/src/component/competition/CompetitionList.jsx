import { Button, Input, Space, Table, Tag } from 'antd';
import { useState } from 'react';
import useCompetitionQuery from '../../hooks/competition/useCompetitionQuery';

const { Search } = Input;

const CompetitionList = ({ showDetail, setSelectedCompetition }) => {
  const { data } = useCompetitionQuery();
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
          trangThai === 'Kết thúc' || trangThai === 'Đã khóa'
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
        const handleClick = () => {
          setSelectedCompetition(record);
          showDetail();
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

  const filteredData = data?.data.filter((item) =>
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
      <Table
        columns={columns}
        dataSource={filteredData}
        pagination={{
          pageSize: 7,
        }}
      />
    </div>
  );
};

export default CompetitionList;
