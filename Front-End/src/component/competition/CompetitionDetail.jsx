import { Button, Drawer, Space } from 'antd';
import React from 'react';
import { Typography } from 'antd';
import { formatDate } from '../../utils/formatDay';
import useJoinCompetition from '../../hooks/team/useJoinCompetition';
import { toast } from 'react-toastify';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import useGetCurrentTeam from '../../hooks/student/useGetCurrentTeam';
import { useQueryClient } from '@tanstack/react-query';
import useJoinCompetitionCorrect from '../../hooks/team/useJoinCompetitionCorrect';

const { Paragraph, Title, Text } = Typography;

const CompetitionDetail = ({ open, onClose, selectedCompetition }) => {
  // get current team
  const { data: currentUser } = useGetCurrentStudent();
  const { data } = useGetCurrentTeam(currentUser?.data?.maSinhVien || 'SV001');
  const team = data?.data;

  const queryClient = useQueryClient();

  const joinCompetition = useJoinCompetitionCorrect();

  const handleJoinCompetition = async () => {
    const data = {
      maCuocThi: selectedCompetition.id,
      maNhomSinhVien: team?.maNhom,
      maSinhVien: currentUser?.data?.maSinhVien,
    };

    await joinCompetition.mutateAsync(data);
    toast.success('Đăng ký tham gia cuộc thi thành công!');

    queryClient.invalidateQueries({ queryKey: ['competitionList'] });
    onClose();
  };

  return (
    <>
      <Drawer
        title='Chi tiết cuộc thi'
        placement={'right'}
        width={800}
        onClose={onClose}
        open={open}
        extra={
          <Space>
            <Button onClick={onClose}>Cancel</Button>
            <Button
              type='primary'
              onClick={handleJoinCompetition}
              disabled={
                selectedCompetition.soLuongNhomDangKy >=
                  selectedCompetition.soLuongNhom ||
                selectedCompetition.trangThai == 'Kết thúc' ||
                selectedCompetition.trangThai == 'Đã khóa' ||
                selectedCompetition.trangThai == 'Đang diễn ra'
              }
            >
              Đăng ký
            </Button>
          </Space>
        }
      >
        <Title style={{ textAlign: 'center' }}>
          Chủ đề: {selectedCompetition.chuDe}
        </Title>
        <Title
          italic
          level={2}
          style={{ textAlign: 'center', marginBottom: 50 }}
        >
          Tên cuộc thi: {selectedCompetition.tenCuocThi}
        </Title>
        <Title level={5} mark>
          Loại cuộc thi: {selectedCompetition.loaiCuocThi}
        </Title>
        <div dangerouslySetInnerHTML={{ __html: selectedCompetition.moTa }} />
        <Title level={5}>
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
        <Title level={5}>
          Số lượng nhóm đã đăng ký: {selectedCompetition.soLuongNhomDangKy}{' '}
        </Title>
      </Drawer>
    </>
  );
};
export default CompetitionDetail;
