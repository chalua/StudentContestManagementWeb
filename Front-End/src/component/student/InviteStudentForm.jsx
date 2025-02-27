import React, { useState } from 'react';
import {
  Button,
  Cascader,
  DatePicker,
  Form,
  Input,
  InputNumber,
  Mentions,
  Select,
  TreeSelect,
  Segmented,
} from 'antd';
import useTeamCreate from '../../hooks/team/useTeamCreate';
import { toast } from 'react-toastify';
import useInviteStudent from '../../hooks/team/useInviteStudent';
import useGetCurrentStudent from '../../hooks/student/useGetCurrentStudent';
import useGetCurrentTeam from '../../hooks/student/useGetCurrentTeam';
const { RangePicker } = DatePicker;
const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 6,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 14,
    },
  },
};
const InviteStudentForm = ({ setIsModalInviteOpen }) => {
  const inviteStudent = useInviteStudent();

  const { data: currentUser } = useGetCurrentStudent();
  const { data } = useGetCurrentTeam(currentUser?.data?.maSinhVien || 'SV001');

  const team = data?.data;

  const onFinish = async (values) => {
    await inviteStudent.mutateAsync({
      maNhom: team?.maNhom,
      ...values,
    });
    toast.success('Mời thành viên thành công');
    setIsModalInviteOpen(false);
  };
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <Form
      {...formItemLayout}
      variant={'filled'}
      style={{
        maxWidth: '100%',
      }}
      initialValues={{
        variant: 'filled',
      }}
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
    >
      <Form.Item
        label='Mã sinh viên'
        name='maSinhVien'
        rules={[
          {
            required: true,
            message: 'Vui lòng nhập dữ liệu',
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        wrapperCol={{
          offset: 6,
          span: 16,
        }}
      >
        <Button type='primary' htmlType='submit'>
          Xác nhận
        </Button>
      </Form.Item>
    </Form>
  );
};
export default InviteStudentForm;
