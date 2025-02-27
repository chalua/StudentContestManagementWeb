import { Avatar, Flex } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import React from 'react';
import { Typography } from 'antd';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import { formatDate } from '../../utils/formatDay';
const { Title } = Typography;

function StudentInfo() {
  const { data: student } = useGetCurrentStudent();

  return (
    <Flex vertical align='center' gap={25}>
      <Title level={2} style={{ color: '#043F92' }}>
        Thông tin sinh viên
      </Title>
      <Avatar size={164} icon={<UserOutlined />} />

      <Flex style={{ columnGap: 40 }}>
        <Flex vertical>
          <Title level={4}>MSSV: {student?.data?.maSinhVien}</Title>
          <Title level={4}>Họ tên: {student?.data?.hoTen}</Title>
          <Title level={4}>
            Giới tính: {`${student?.data?.gioiTinh == 1 ? 'Nam' : 'Nữ'}`}
          </Title>
          <Title level={4}>
            Ngày sinh: {formatDate(student?.data?.ngaySinh)}
          </Title>
          <Title level={4}>Nơi sinh: {student?.data?.queQuan}</Title>
        </Flex>
        <Flex vertical>
          <Title level={4}>Lớp học: {student?.data?.lop?.maLop}</Title>
          <Title level={4}>Khóa học: {student?.data?.khoaHoc}</Title>
          <Title level={4}>Bậc đào tạo: {student?.data?.bacDaoTao}</Title>
          <Title level={4}>
            Loại hình đào tạo: {student?.data?.loaiHinhDaoTao}
          </Title>
          <Title level={4}>Ngành: Công nghệ thông tin</Title>
        </Flex>
      </Flex>
    </Flex>
  );
}

export default StudentInfo;
