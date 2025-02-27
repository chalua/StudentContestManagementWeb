import { Button, Space, Table, Tag } from 'antd';
import { useLocation, useNavigate } from 'react-router-dom';
import useCompetitionByTypeQuery from '../../hooks/competition/useCompetitionByTypeQuery';

const ManageCompetitionList = ({
  showDetail,
  setSelectedCompetition,
  setOpen,
  setOpenDelete,
}) => {
  const navigate = useNavigate();
  const location = useLocation();

  const isCompetition = location.pathname == '/admin/manage-competition';
  const isCompetitionProject = location.pathname == '/admin/manage-project';
  const isScientific = location.pathname == '/admin/manage-scientific';

  const type = {
    '/admin/manage-competition': 1,
    '/admin/manage-competition-project': 2,
    '/admin/manage-scientific': 3,
  };

  const { data: competitions } = useCompetitionByTypeQuery(
    type[location.pathname]
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
            : trangThai === 'Đang diễn ra'
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

        const handleUpdate = () => {
          setSelectedCompetition(record);
          setOpen(true);
        };

        const handleDelete = () => {
          setSelectedCompetition(record);
          setOpenDelete(true);
        };

        console.log('check record', record.trangThai);

        const isDisabled = record.trangThai !== 'Chưa diễn ra';

        return (
          <Space size='middle'>
            <Button onClick={handleClick} style={{ color: '#458BEE' }}>
              Chi tiết
            </Button>
            <Button
              onClick={handleUpdate}
              style={{ color: '#EEAB00' }}
              disabled={isDisabled}
            >
              Cập nhật
            </Button>
            <Button
              onClick={handleDelete}
              style={{ color: '#ED2D13' }}
              disabled={isDisabled}
            >
              Xóa
            </Button>
            {isCompetition && (
              <Button
                onClick={() =>
                  navigate(`/admin/manage-competition/${record.id}/round`)
                }
                style={{ color: '#05a139' }}
              >
                Vòng
              </Button>
            )}
          </Space>
        );
      },
    },
  ];

  return (
    <Table
      columns={columns}
      pagination={{
        pageSize: 7,
      }}
      dataSource={competitions?.data?.map((competition) => ({
        key: competition.id,
        ...competition,
      }))}
    />
  );
};
export default ManageCompetitionList;
