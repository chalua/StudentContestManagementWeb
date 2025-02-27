import React, { useState } from 'react';
import { Button, Table } from 'antd';
import useAnalysisByCompetition from '../../hooks/analysis/useAnalysisCompetition';
import { CSVLink } from 'react-csv';

const ResultTable = ({ selectedCompetition }) => {
  const { data: analysis } = useAnalysisByCompetition(
    selectedCompetition?.maCuocThi
  );

  const uniqueGroups = [
    ...new Set(analysis?.data?.map((item) => item.tenNhom)),
  ].map((group) => ({
    text: group,
    value: group,
  }));

  const uniqueRounds = [
    ...new Set(analysis?.data?.map((item) => item.soThuTuVongThi)),
  ].map((round) => ({
    text: `Vòng ${round}`,
    value: `Vòng ${round}`,
  }));

  const columns = [
    {
      title: 'Vòng',
      dataIndex: 'soThuTuVongThi',
      key: 'soThuTuVongThi',
      render: (text) => <a>{text}</a>,
      filters: uniqueRounds,
      defaultFilteredValue: ['Vòng 1'],
      onFilter: (value, record) => `Vòng ${record.soThuTuVongThi}` === value,
    },
    {
      title: 'Tên nhóm',
      dataIndex: 'tenNhom',
      key: 'tenNhom',
      filters: uniqueGroups,
      onFilter: (value, record) => record.tenNhom === value,
      render: (_, record) => {
        console.log('check record', record.nhomResponse.thanhVien);

        return (
          <ul>
            <b>{record.tenNhom}</b>
            {record.nhomResponse.thanhVien.map((tv) => {
              return (
                <li key={tv.maSinhVien}>
                  {tv.tenSinhVien} - {tv.maSinhVien}
                </li>
              );
            })}
          </ul>
        );
      },
    },
    {
      title: 'Điểm',
      dataIndex: 'ketQua',
      key: 'ketQua',
    },
    {
      title: 'Đậu / Rớt',
      dataIndex: 'dau',
      key: 'dau',
      render: (text) => (
        <span style={{ color: text ? 'green' : 'red' }}>
          {text ? 'Đậu' : 'Rớt'}
        </span>
      ),
      filters: [
        {
          text: 'Đậu',
          value: true,
        },
        {
          text: 'Rớt',
          value: false,
        },
      ],
      onFilter: (value, record) => record.dau === value,
    },
    {
      title: 'Giải thưởng',
      dataIndex: 'tenGiaiThuong',
      key: 'tenGiaiThuong',
    },
  ];

  // const csvData = [
  //   ['firstname', 'lastname', 'email'],
  //   ['Ahmed', 'Tomi', 'ah@smthing.co.com'],
  //   ['Raed', 'Labes', 'rl@smthing.co.com'],
  //   ['Yezzi', 'Min l3b', 'ymin@cocococo.com'],
  // ];

  const csvMapped = analysis?.data
    ?.map((item) => {
      const thanhVien = item.nhomResponse.thanhVien;
      console.log('check tv', thanhVien);
      const mappedTv = thanhVien?.map((tv) => {
        return `${tv.maSinhVien} - ${tv.tenSinhVien}`;
      });

      return [
        item.soThuTuVongThi || '', // Vòng
        mappedTv || '', // Tên nhóm
        item.ketQua || 0, // Điểm
        item.dau ? 'Đậu' : 'Rớt', // Đậu / Rớt
        item.tenGiaiThuong || '', // Giải thưởng
      ];
    })
    ?.sort((a, b) => {
      // Sắp xếp theo vòng trước (số tăng dần)
      // if (a[0] !== b[0]) return a[0] - b[0];
      return a[0] - b[0];
      // Nếu vòng giống nhau, sắp xếp theo tên nhóm (tăng dần, không phân biệt hoa thường)
      // return a[1].localeCompare(b[1], 'vi', { sensitivity: 'base' });
    });

  const csvData = [
    ['Vòng', 'Tên nhóm', 'Điểm', 'Đậu / Rớt', 'Giải thưởng'],
    ...(csvMapped || []),
  ];
  console.log('check analysis', analysis?.data);

  return (
    <>
      <Button type='primary'>
        <CSVLink data={csvData}>Xuất file Excel</CSVLink>
      </Button>
      <Table
        columns={columns}
        dataSource={analysis?.data || []}
        rowKey={(record) => record.id || record.key}
      />
    </>
  );
};

export default ResultTable;
