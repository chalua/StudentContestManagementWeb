import { Button, Space, Table, Tag } from 'antd';
import useGetLectureCompetitions from '../../hooks/lecture/useGetLectureCompetitions';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';

const CompetitionList = ({ showDetail, setSelectedCompetition }) => {
  const { data: lecture } = useGetCurrentLecture();

  const { data: competitions } = useGetLectureCompetitions(
    lecture?.data?.maGiangVien || 'GV001'
  );

  const columns = [
    {
      title: 'Tên',
      dataIndex: 'tenCuocThi',
      key: 'tenCuocThi',
      render: (text) => <a>{text}</a>,
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
          showDetail();
          setSelectedCompetition(record);
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

  return <Table columns={columns} dataSource={competitions?.data} />;
};
export default CompetitionList;
