import React, { useState } from 'react';
import { Button, Space, Table, Tag } from 'antd';
import useAnalysis from '../../hooks/analysis/useAnalysis';

const AnalysisTable = ({ setOpenDrawer, setSelectedCompetition }) => {
  const { data: analysis } = useAnalysis();

  const columns = [
    {
      title: 'Tên cuộc thi',
      dataIndex: 'tenCuocThi',
      key: 'tenCuocThi',
      render: (text) => <a>{text}</a>,
    },
    {
      title: 'Số lượng nhóm đăng ký',
      dataIndex: 'soLuongNhomDangKy',
      key: 'soLuongNhomDangKy',
    },
    {
      title: 'Số lượng nhóm đã duyệt',
      dataIndex: 'soLuongNhomDaDuyet',
      key: 'soLuongNhomDaDuyet',
    },
    {
      title: 'Loại cuộc thi',
      dataIndex: 'loaiCuocThi',
      key: 'loaiCuocThi',
    },

    {
      title: 'Hành động',
      key: 'Hành động',
      render: (_, record) => {
        const handleResult = () => {
          setOpenDrawer(true);
          setSelectedCompetition(record);
        };

        return (
          <Space size='middle'>
            <Button color='primary' onClick={handleResult}>
              Kết quả
            </Button>
          </Space>
        );
      },
    },
  ];

  const dataSource = Array.isArray(analysis?.data)
    ? Array.from(
        new Map(analysis.data.map((item) => [item?.cuocThi?.id, item])).values()
      ).map((item) => ({
        maCuocThi: item?.cuocThi?.id,
        tenCuocThi: item?.cuocThi?.tenCuocThi,
        soLuongNhomDangKy: item?.cuocThi?.soLuongNhomDangKy,
        soLuongNhomDaDuyet: item?.cuocThi?.soLuongNhomDaDuyet,
        loaiCuocThi: item?.cuocThi?.loaiCuocThi,
      }))
    : [];

  return <Table columns={columns} dataSource={dataSource || []} />;
};
export default AnalysisTable;
