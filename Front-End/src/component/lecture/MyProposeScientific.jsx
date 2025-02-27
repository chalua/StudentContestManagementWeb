import React from 'react';
import { Button, Flex, Table } from 'antd';
import useGetProposes from '../../hooks/lecture/useGetProposes';
import useGetCurrentLecture from '../../hooks/lecture/useGetCurrentLecture';

const MyProposeScientific = ({ selectedScientific }) => {
  const { data: user } = useGetCurrentLecture();
  const { data: proposes } = useGetProposes(user?.data?.maGiangVien || '');

  const myProposes = proposes?.data?.filter(
    (propose) => propose.cuocThiId === selectedScientific.id
  );

  const columns = [
    {
      title: 'Tên đề tài',
      dataIndex: 'tenDeTai',
      key: 'tenDeTai',
    },
    {
      title: 'Ngày đề xuất',
      dataIndex: 'ngayDeXuat',
      key: 'ngayDeXuat',
    },
    {
      title: 'Hành động',
      dataIndex: '',
      key: 'x',
      render: () => {
        return (
          <Flex gap={3}>
            <Button style={{ color: '#037cb4' }}>Chi tiết</Button>
          </Flex>
        );
      },
    },
  ];

  return <Table columns={columns} dataSource={myProposes || []} />;
};
export default MyProposeScientific;
