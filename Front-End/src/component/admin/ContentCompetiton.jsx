import { Typography } from 'antd';
import React from 'react';
import { formatDate } from '../../utils/formatDay';

const { Paragraph, Title, Text } = Typography;

const ContentCompetition = ({ open, onClose, selectedCompetition }) => {
  console.log('admin check seletecd', selectedCompetition);

  return (
    <>
      <Title style={{ textAlign: 'center' }}>
        Chủ đề: {selectedCompetition.chuDe}
      </Title>
      <Title italic level={2} style={{ textAlign: 'center', marginBottom: 50 }}>
        Tên cuộc thi: {selectedCompetition.tenCuocThi}
      </Title>
      <Title level={5} mark>
        Loại cuộc thi: {selectedCompetition.loaiCuocThi}
      </Title>
      <div>
        <div dangerouslySetInnerHTML={{ __html: selectedCompetition.moTa }} />
      </div>
      <Title level={5} style={{ marginTop: 20 }}>
        Thời gian diễn ra từ{' '}
        <Text style={{ color: 'red' }}>
          {formatDate(selectedCompetition.ngayBatDau)}
        </Text>{' '}
        đến{' '}
        <Text style={{ color: 'red' }}>
          {formatDate(selectedCompetition.ngayKetThuc)}
        </Text>
      </Title>
      <Title level={5}>
        Số lượng nhóm tối đa: {selectedCompetition.soLuongNhom}{' '}
      </Title>
    </>
  );
};
export default ContentCompetition;
